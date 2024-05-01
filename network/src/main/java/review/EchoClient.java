package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			while (true) {
				System.out.print(">>");
				String input = scanner.nextLine();

				if ("exit".equals(input)) {
					log("closed by client");
					break;
				}

				pw.println(input);
				String serverInput = br.readLine();
				if (serverInput == null) {
					log("server end");
					break;
				}

				System.out.println(">>" + serverInput);
			}
		} catch (IOException e) {
			log("error : " + e);
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

	private static void log(String message) {
		System.out.println("[EchoClient] " + message);
	}
}
