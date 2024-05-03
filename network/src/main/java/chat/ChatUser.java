package chat;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

public class ChatUser {
	private String nickname;
	private Socket socket;
	private Writer writer;
	private boolean admin;

	public ChatUser(String nickname, Socket socket, Writer writer, boolean admin) {
		this.nickname = nickname;
		this.socket = socket;
		this.writer = writer;
		this.admin = admin;
	}

	public void sendMessage(String message) {
		PrintWriter pw = (PrintWriter) this.writer;
		pw.println(message);
	}

	public String getNickname() {
		return nickname;
	}

	public boolean isAdmin() {
		return admin;
	}

	public Writer getWriter() {
		return writer;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
