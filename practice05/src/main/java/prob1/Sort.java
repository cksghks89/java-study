package prob1;

import java.util.Arrays;

public class Sort {

	public static void main(String[] arg) {

		int array[] = { 5, 9, 3, 8, 60, 20, 1 };
		int count = array.length;

		System.out.println("Before sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

		//
		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		//
		for (int i = 0; i < count; i++) {
			boolean isChange = false;
			for (int j = 1; j < count - i; j++) {
				if (array[j - 1] < array[j]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
					isChange = true;
				}
			}
			if (!isChange) break;
		}

		// 결과 출력
		System.out.println("\nAfter Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
	}
}