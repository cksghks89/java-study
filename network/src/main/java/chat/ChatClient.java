package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);

			new ChatClientThread(br).start();

			Thread.sleep(500);
			while (true) {
				System.out.print(">>");
				String input = scanner.nextLine();

				if ("quit".equals(input)) {
					System.out.println("채팅을 종료합니다.");
					break;
				} else {
					pw.println("message:" + input);
				}
			}
		} catch (IOException e) {
			log("errer : " + e);
		} catch (InterruptedException e) {
			log("error : " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(String message) {
		System.out.println("[ChatClient] " + message);
	}
}
