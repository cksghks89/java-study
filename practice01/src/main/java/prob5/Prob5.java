package prob5;

public class Prob5 {

	public static void main(String[] args) {
		StringBuilder answer = new StringBuilder();
		
		for (int i = 1; i < 100; i++) {
			String currentNumber = String.valueOf(i);
			currentNumber = currentNumber.replaceAll("(3|6|9)", "짝").replaceAll("[0-9]", "");
			
			if (currentNumber.length() != 0) {
				answer.append(i).append(' ').append(currentNumber).append('\n');
			}
		}
		
		System.out.println(answer);
	}
}
