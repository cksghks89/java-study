package prob5;

public class MyStackException extends RuntimeException {

	@Override
	public String toString() {
		return this.getClass().toString().split(" ")[1] + ": stack is empty";
	}
	
}
