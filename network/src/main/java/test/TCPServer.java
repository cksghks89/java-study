package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			// Socket에(IPAddress + Port)를 바인딩한다.
			// 바인딩하는 IP주소를 0.0.0.0으로 설정하여 특정 호스트 IP를 바인딩 하지 않는다.
			// InetSocketAddress 의 2번째 매개변수인 backlog 는 accept 처리 중간에 또 다른 연결 요청을 저장하기 위한 큐의
			// 개수이다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5001), 10);

			// 3. accept
			Socket socket = serverSocket.accept(); // blocking

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

				// 4. IO Stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking

					if (readByteCount == -1) {
						// 클라이언트가 정상적으로 종료(close() 호출)
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received : " + data);
					
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
			} catch (SocketException e) {
				System.out.println("[server] suddenly closed by client");
			} catch (IOException e) {
				System.out.println("[server] error : " + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("[server] error: " + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
