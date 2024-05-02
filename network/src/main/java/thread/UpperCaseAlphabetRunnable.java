package thread;

// 상위 클래스를 건들지 않고 해당 클래스의 메소드를 멀티 스레드로 돌리고 싶을 때 이렇게 사용한다.
public class UpperCaseAlphabetRunnable extends UpperCaseAlphabet implements Runnable {

	@Override
	public void run() {
		print();
	}
}
