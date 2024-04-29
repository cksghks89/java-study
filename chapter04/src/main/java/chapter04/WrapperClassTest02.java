package chapter04;

public class WrapperClassTest02 {
	public static void main(String[] args) {
		String s = "123456";
		int i = Integer.parseInt(s);
		
		// cf1. 반대로
		String s1 = String.valueOf(i);
		
		// cf2. 반대로
		String s2 = "" + i;
		
		System.out.println(s + ":" + s1 + ":" + s2);
	}
}
