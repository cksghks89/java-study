package chapter04;

public class StringTest03 {
	public static void main(String[] args) {
		// s1과 s2는 그 연산 방식이 완전히 똑같다.
		String s1 = "Hello" + "World" + " java " + 17;
		String s2 = new StringBuffer("Hello").append("World").append(" java ").append(17).toString();
		
		System.out.println(s1);
		System.out.println(s2);
	}
}
