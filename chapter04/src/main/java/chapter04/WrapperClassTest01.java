package chapter04;

public class WrapperClassTest01 {
	public static void main(String[] args) {
		// 직접 생성하면 heap 상에 객체가 존재하게 된다.
		// 리터럴(literal)을 사용하면 JVM안의 Constant Pool 에서 관리하게 된다.
		Integer i = new Integer(10);
		Character c = new Character('a');
		Boolean b = new Boolean(true);

		// Auto Boxing
		Integer j1 = 10;
		Integer j2 = 20;

		System.out.println(j1 == j2);
		System.out.println(j1.equals(j2));
		
		// Auto Unboxing
		int m = j1 + 10;
//		int m2 = j1.intValue() + 10;
		
		long start = System.currentTimeMillis();
		System.out.println(sum());
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.0 + "초");
		
		start = System.currentTimeMillis();
		System.out.println(sum2());
		end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.0 + "초");
		
	}

	private static long sum() {
		Long sum = 0L;
		for (long i = 0; i <= 100_000_0000; i++)
			sum += 1;
		return sum;
	}
	
	private static long sum2() {
		long sum = 0L;
		for (long i = 0; i <= 100_000_0000; i++)
			sum += 1;
		return sum;
	}
}
