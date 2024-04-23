package prob5;

public class Prob5 {

	public static void main(String[] args) {
		StringBuilder answer = new StringBuilder();
		
		for (int i = 1; i < 100; i++) {
			StringBuilder sb = new StringBuilder();
			int number = i;
			while (number != 0) {
				if ((number % 10) % 3 == 0 && (number % 10) != 0) {
					sb.append("짝");
				}
				
				number /= 10;
			}
			
			if (sb.length() != 0) {
				answer.append(i).append(" ").append(sb).append('\n');
			}
		}
		
		System.out.println(answer);
	}
}
