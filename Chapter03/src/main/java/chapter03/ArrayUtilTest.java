package chapter03;

import java.util.Arrays;

public class ArrayUtilTest {
	public static void main(String[] args) {
		int[] a = {10, 20, 30, 40};
		
		double[] d = ArrayUtils.intToDouble(a);
		System.out.println(Arrays.toString(d));
	}
}
