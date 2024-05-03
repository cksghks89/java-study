package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private Panel joinPannel;
	private TextArea joinArea;

	private final Socket socket;
	private final PrintWriter pw;
	private final BufferedReader br;

	public ChatWindow(String name, Socket socket, BufferedReader br, PrintWriter pw) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);

		joinPannel = new Panel();
		joinArea = new TextArea(30, 20);

		this.socket = socket;
		this.br = br;
		this.pw = pw;
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(100);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// join Pannel
		joinArea.setEditable(false);
		joinPannel.setBackground(Color.LIGHT_GRAY);
		joinPannel.add(joinArea);
		frame.add(BorderLayout.EAST, joinPannel);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		// ChatClientThread 생성
		new ChatClientThread().start();
	}

	private void sendMessage() {
		String message = textField.getText(); // 입력한 메시지 가져오기
		if (message == null || "".equals(message)) {
			return;
		}
		ChatClientApp.log("SEND : " + message);

		if ("quit".equals(message)) {
			finish();
			return;
		}

		if (message.startsWith("/passadmin")) {
			String[] input = message.split(" ");
			if (input != null && input.length >= 2) {
				pw.println("passadmin:" + input[1]);

				textField.setText(""); // 입력 창 지우기
				textField.requestFocus();
				return;
			}
		}

		pw.println("message:" + message);

		textField.setText(""); // 입력 창 지우기
		textField.requestFocus();
	}

	private void finish() {
		// exit java application & quit
		pw.println("quit");
		System.exit(0);
	}

	private class ChatClientThread extends Thread {
		public void run() {
			try {
				while (true) {
					String response = br.readLine();
					ChatClientApp.log("RESPONSE : " + response);
					if (response == null) {
						System.out.println("서버와의 연결이 끊어졌습니다.");
						break;
					}

					String[] tokens = response.split(":", 2);

					if (tokens == null || tokens.length == 0) {
						continue;
					}

					if ("message".equals(tokens[0]) && tokens.length >= 2) {
						updateTextArea(tokens[1]);
					} else if ("join:ok".equals(response)) {
						updateTextArea("입장하였습니다. 즐거운 채팅 되세요~!");
					} else if ("participant".equals(tokens[0])) {
						updateJoinList(tokens[1]);
					}
				}
			} catch (IOException e) {
				// Socket closed
			}
		}

		private void updateTextArea(String message) {
			textArea.append(message);
			textArea.append("\n");
		}

		private void updateJoinList(String rawJoinList) {
			joinArea.setText("");
			joinAreaHeadline();
			String[] participantList = rawJoinList.split(" ");
			for (int i = 0; i < participantList.length; i++) {
				String[] currentUser = participantList[i].split("\\|");
				joinArea.append(currentUser[0]);
				if ("1".equals(currentUser[1])) {
					joinArea.append(" (방장)");
				}
				joinArea.append("\n");
			}
		}

		private void joinAreaHeadline() {
			joinArea.append("==== [User List] ====\n\n");
		}
	}
}
