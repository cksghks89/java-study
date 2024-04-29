package prob01;

public class Printer {
//	public void println(int value) {
//		System.out.println(value);
//	}
//
//	public void println(boolean value) {
//		System.out.println(value);
//	}
//
//	public void println(double value) {
//		System.out.println(value);
//	}
//
//	public void println(String value) {
//		System.out.println(value);
//	}

	// 오버로딩을 할 수 있으면 오버로딩을 하는 것이 더 낫다.
	// 그게 안되는 경우 제네릭을 사용하자.
	public <T> void println(T t) {
		System.out.println(t);
	}

	public <T, S> S println(T t, S s) {
		System.out.println(t);
		return s;
	}

	public int sum(Integer... nums) {
		int s = 0;
		for (Integer cur : nums)
			s += cur;
		return s;
	}

	public <T> void println(T... ts) {
		for (T t : ts) {
			System.out.println(t);
		}
	}
}
