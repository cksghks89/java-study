package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in  );

		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };

		/* 코드 작성 */
		System.out.print("금액: ");
		int cost = Integer.parseInt(scanner.nextLine());
		System.out.println();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MONEYS.length; i++) {
			int count = cost / MONEYS[i];
			cost -= count * MONEYS[i];
			
			sb.append(MONEYS[i]).append("원 : ").append(count).append("개\n");
		}
		System.out.println(sb);
		
		scanner.close();
 	}
}