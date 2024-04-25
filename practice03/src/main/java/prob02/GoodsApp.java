package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for (int i = 0; i < COUNT_GOODS; i++) {
			String[] input = scanner.nextLine().split(" ");
			goods[i] = new Goods(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]));
		}

		// 상품 출력
		for (int i = 0; i < COUNT_GOODS; i++) {
			System.out.println(goods[i]);
		}
		
		// 자원정리
		scanner.close();
	}
}
