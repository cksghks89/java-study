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
	private final List<ChatUser> users;
	private String nickname;
	private ChatUser me;

	public ChatServerThread(Socket socket, List<Writer> listWriters, List<ChatUser> users) {
		this.socket = socket;
		this.listWriters = listWriters;
		this.users = users;
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

				boolean isEnd = false;
				switch (tokens[0]) {
				case "join":
					doJoin(tokens[1], pw);
					break;
				case "message":
					doMessage(tokens[1]);
					break;
				case "quit":
					doQuit(pw);
					isEnd = true;
					break;
				case "passadmin":
					if (me.isAdmin()) {
						doPassAdmin(tokens[1]);
					}
					break;
				default:
					throw new UnknownError("UnknownException (" + tokens[0] + ")");
				}
				if (isEnd)
					break;
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

	private void doPassAdmin(String username) {
		if (me.getNickname().equals(username)) {
			me.sendMessage("message:본인에게는 방장을 넘길 수 없습니다.");
			return;
		}

		synchronized (users) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getNickname().equals(username)) {
					me.setAdmin(false);
					users.get(i).setAdmin(true);

					broadcastInfo("message", "====== [" + username + " 님으로 방장이 변경되었습니다.] =====");
					doSendUserList();
					break;
				}
			}
		}
	}

	private void doQuit(Writer writer) {
//		removeWriter(writer);
		removeMe();

		if (me.isAdmin()) {
			synchronized (users) {
				if (users != null && users.size() > 0) {
					users.get(0).setAdmin(true);
				}
			}
		}

		String data = nickname + "님이 퇴장 하였습니다.";
		ChatServer.log(data);
//		broadcastMessage(data);
		broadcastInfo("message", data);
		doSendUserList();
	}

	private void removeMe() {
		synchronized (users) {
			users.remove(me);
		}
	}

	private void doMessage(String message) {
		String data = nickname + ":" + message;
		ChatServer.log(data);
//		broadcastMessage(data);
		broadcastInfo("message", data);
	}

	private void doJoin(String nickname, Writer writer) {
		PrintWriter printWriter = (PrintWriter) writer;
		this.nickname = nickname;

		if (isDuplicateName(nickname)) {
			printWriter.println("join:duplicate");
			return;
		}

		String data = nickname + "님이 참여하였습니다.";
		ChatServer.log(data);
//		broadcastMessage(data);
		broadcastInfo("message", data);

		addChatUser(nickname, writer);
//		addWriter(writer);

		printWriter.println("join:ok");
		doSendUserList();
	}

	private boolean isDuplicateName(String nickname) {
		synchronized (users) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getNickname().equals(nickname)) {
					return true;
				}
			}
		}
		return false;
	}

	private void addChatUser(String nickname, Writer writer) {
		synchronized (users) {
			me = new ChatUser(nickname, socket, writer, users.size() == 0);
			users.add(me);
		}
	}

	private void doSendUserList() {
		// 유저리스트 정보
		StringBuilder listInfo = new StringBuilder();
		synchronized (users) {
			users.forEach((user) -> {
				listInfo.append(user.getNickname()).append('|');
				listInfo.append(user.isAdmin() ? "1" : "0");
				listInfo.append(" ");
			});
		}
		broadcastInfo("participant", listInfo.toString());
	}

	private void broadcastInfo(String protocol, String data) {
		synchronized (users) {
			users.forEach((user) -> user.sendMessage(protocol + ":" + data));
		}
	}

//	private void removeWriter(Writer writer) {
//		synchronized (listWriters) {
//			listWriters.remove(writer);
//		}
//	}

//	private void addWriter(Writer writer) {
//		synchronized (listWriters) {
//			listWriters.add(writer);
//		}
//	}

//	private void broadcastMessage(String data) {
//		synchronized (listWriters) {
//			for (Writer writer : listWriters) {
//				PrintWriter printWriter = (PrintWriter) writer;
//				printWriter.println("message:" + data);
//			}
//		}
//	}
}
