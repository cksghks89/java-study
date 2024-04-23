package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */
		System.out.print("숫자를 입력하세요: ");
		int num = Integer.parseInt(scanner.nextLine());
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			if (num % 2 == 0 && i % 2 == 0) {
				sum += i;
			} else if (num % 2 == 1 && i % 2 == 1) {
				sum += i;
			}
		}
		System.out.println("결과 값 : " + sum);
		
		scanner.close();
	}
}
