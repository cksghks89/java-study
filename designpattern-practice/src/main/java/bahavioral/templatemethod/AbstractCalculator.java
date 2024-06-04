package bahavioral.templatemethod;

import java.util.Scanner;

public abstract class AbstractCalculator {
	
	// 여러 클래스에서 공통으로 사용하는 메서드
	public void templateMethod() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("(val1, val2) > ");
		int val1 = scanner.nextInt();
		int val2 = scanner.nextInt();

		int result = calculate(val1, val2);

		System.out.println(result);
	}

	// 공통 메서드에서 세부적인 부분을 하위 클래스마다 다르게 구현하는 부분
	public abstract int calculate(int val1, int val2);
}
