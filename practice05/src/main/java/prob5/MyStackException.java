package prob5;

public class MyStackException extends RuntimeException {
	public MyStackException() {
		super();
	}

	public MyStackException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return this.getClass().toString().split(" ")[1] + ": stack is empty";
	}

}
