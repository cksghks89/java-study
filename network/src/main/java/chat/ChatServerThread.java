package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread {
	private final Socket socket;
	private final List<Writer> listWriters;

	private String nickname;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			ChatServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while (true) {
				String request = br.readLine();
				if (request == null) {
					ChatServer.log("Closed by Client");
					doQuit(pw);
					break;
				}

				// 프로토콜 분석
				String[] tokens = request.split(":", 2);

				switch (tokens[0]) {
				case "join":
					doJoin(tokens[1], pw);
					break;
				case "message":
					doMessage(tokens[1]);
					break;
				case "quit":
					doQuit(pw);
					break;
				default:
					throw new UnknownError("UnknownException (" + tokens[0] + ")");
				}
			}
		} catch (UnknownError e) {
			doQuit(pw);
			ChatServer.log(e.getMessage());
		} catch (SocketException e) {
			doQuit(pw);
			ChatServer.log("SocketException : " + e);
		} catch (IOException e) {
			doQuit(pw);
			ChatServer.log("IOException " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);

		String data = nickname + "님이 퇴장 하였습니다.";
		ChatServer.log(data);
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}

	private void doMessage(String message) {
		String data = nickname + ":" + message;
		ChatServer.log(data);
		broadcast(data);
	}

	private void doJoin(String nickname, Writer writer) {
		this.nickname = nickname;

		String data = nickname + "님이 참여하였습니다.";
		ChatServer.log(data);
		broadcast(data);

		addWriter(writer);

		// ack
		PrintWriter printWriter = (PrintWriter) writer;
		printWriter.println("join:ok");
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println("message:" + data);
			}
		}
	}
}
