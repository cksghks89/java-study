package udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner scanner = null;
		DatagramSocket socket = null;

		try {
			// 1. 스캐너 생성
			scanner = new Scanner(System.in);

			// 2. 소켓 생성
			socket = new DatagramSocket();

			while (true) {
				System.out.print(">");
				String message = scanner.nextLine();

				if ("quit".equals(message)) {
					break;
				}

				// 3. 보내기
				byte[] sendData = message.getBytes("utf-8");

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT));

				socket.send(sendPacket);

				// 4. 수신
				DatagramPacket rcvPacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE],
						UDPEchoServer.BUFFER_SIZE);
				socket.receive(rcvPacket); // blocking

				byte[] rcvData = rcvPacket.getData();
				int offset = rcvPacket.getLength();
				String receiveMessage = new String(rcvData, 0, offset, "utf-8");

				System.out.println("<" + receiveMessage);
			}
		} catch (SocketException e) {
			System.out.println("[UDP Echo Client] error : " + e);
		} catch (IOException e) {
			System.out.println("[UDP Echo Client] error : " + e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}

			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		}

	}
}
