package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":"
					+ inetSocketAddress.getPort());

			String request = null;
			while (true) {
				String line = br.readLine();

				// 브라우저에서 연결을 끊으면...
				if (line == null) {
					consoleLog("line is null");
					break;
				}

				// SimpleHttpServer는 HTTP Header만 처리
				if ("".equals(line)) {
					consoleLog("line is empty");
					break;
				}

				// request header의 첫 줄만 처리
				if (request == null) {
					request = line;
				}
			}

			// 요청 처리
			consoleLog(request);

			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				responseStaticResource(outputStream, tokens[1], tokens[2]);
			} else {
				/* methods: POST, PUT, PATCH, DELETE */
				// SimpleHttpServer 에서는 무시(400 Bad Request)
				response400BadRequest(outputStream, tokens[2]);
			}
		} catch (Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

			} catch (IOException ex) {
				consoleLog("error:" + ex);
			}
		}
	}

	private void responseStaticResource(OutputStream outputStream, String url, String protocol) throws IOException {
		// default(welcome) file set
		if ("/".equals(url)) {
			url = "/index.html";
		}

		File file = new File(DOCUMENT_ROOT + url);
		if (!file.exists()) {
			response404NotFound(outputStream, protocol);
			return;
		}

		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());

		outputStream.write((protocol + " 200 OK\n").getBytes("UTF-8"));
		outputStream.write(("Content-Type: " + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		outputStream.write("\n".getBytes());
		outputStream.write(body);
	}

	private void response400BadRequest(OutputStream outputStream, String protocol) throws IOException {
		responseTemplate(outputStream, protocol, "/error/400.html", 400, "Bad Request");
	}

	private void response404NotFound(OutputStream outputStream, String protocol) throws IOException {
		responseTemplate(outputStream, protocol, "/error/404.html", 404, "File Not Found");
	}

	private void responseTemplate(OutputStream outputStream, String protocol, String filePath, int statusCode,
			String statusMessage) throws IOException {
		File file = new File(DOCUMENT_ROOT + filePath);
		if (!file.exists()) {
			return;
		}

		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());

		outputStream.write((protocol + " " + statusCode + " " + statusMessage + "\n").getBytes("utf-8"));
		outputStream.write(("Content-Type: " + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		outputStream.write("\n".getBytes());
		outputStream.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
