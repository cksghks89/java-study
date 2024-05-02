package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private final Socket socket;

	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			while (true) {
				String data = br.readLine(); // blocking
				if (data == null) {
					EchoServer.log("closed by client");
					break;
				}

				EchoServer.log("[server] received: " + data);
				pw.println(data);
			}
		} catch (SocketException e) {
			EchoServer.log("[server] Socket Exception" + e);
		} catch (IOException e) {
			EchoServer.log("[server] error : " + e);
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

}
