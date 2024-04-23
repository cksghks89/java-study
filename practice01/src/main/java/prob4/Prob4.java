package prob4;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("문자열을 입력하세요 : ");
		String text = scanner.nextLine();

		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			sb.append(text.charAt(i));
			answer.append(sb).append('\n');
		}
		System.out.println(answer);
		
		scanner.close();
	}

}
