package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ChatClientApp {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		// 닉네임 입력 검증 ------ start
		String name = null;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();

			if (name != null && !name.isEmpty()) {
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		// 닉네임 입력 검증 ------ end

		// 채팅방 입장 검증 & GUI ----- start
		Socket socket = null;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			String request = "join:" + name;
			pw.println(request);

			while (true) {
				String response = br.readLine();
				if (response == null) {
					log("서버와의 연결이 끊어졌습니다.");
					break;
				}

				if ("join:ok".equals(response)) {
					log(name + ": 채팅방에 입장하였습니다.");
					new ChatWindow(name, socket, br, pw).show();
					break;
				}

				if ("join:duplicate".equals(response)) {
					log(name + ": 은 중복된 닉네임 입니다. 다시 입력해주세요.");
					System.out.print(">>");
					name = scanner.nextLine(); // blocking
					pw.println("join:" + name);
				}
			}
		} catch (SocketException e) {
//			log("SocketException : " + e);
			log("서버와의 연결이 불안정합니다. 잠시 후 다시 시도해주세요.");
		} catch (IOException e) {
			log("IOException : " + e);
		} finally {
			scanner.close();
		}
		// 채팅방 입장 검증 & GUI ----- end
	}

	public static void log(String message) {
		System.out.println("[ChatClientApp] " + message);
	}
}
