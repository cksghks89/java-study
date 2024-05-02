package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ChatClientThread extends Thread {
	private final Reader reader;

	public ChatClientThread(Reader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		BufferedReader br = (BufferedReader) reader;

		String response = null;
		try {
			while (true) {
				response = br.readLine();
				if (response == null) {
					// 서버에 의한 종료
					System.out.println("서버와의 연결이 끊어졌습니다.");
					break;
				}

				String[] tokens = response.split(":", 2);

				if (tokens == null || tokens.length == 0) {
					continue;
				}

				if ("message".equals(tokens[0])) {
					System.out.println(tokens[1]);
				} else if ("join".equals(tokens[0])) {
					System.out.println("입장하였습니다. 즐거운 채팅 되세요~!");
				}
			}
		} catch (IOException e) {
			// Socket closed
		}
	}
}
