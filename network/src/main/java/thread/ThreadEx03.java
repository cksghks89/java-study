package thread;

public class ThreadEx03 {
	public static void main(String[] args) {
		// Thread 클래스로 만든 객체
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();

		// Runnable 로 구현한 객체
		Thread thread3 = new Thread(new UpperCaseAlphabetRunnable());

		thread1.start();
		thread2.start();
		thread3.start();

	}
}
