package exception;

public class ExceptionTest {
	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;

		System.out.println(" Some Code1...");
		try {
			System.out.println(" Some Code2...");
			System.out.println(" Some Code3...");

			int result = (1 + 2 + 3) / b;

			System.out.println(" Some Code4...");
			System.out.println(" Some Code5...");
		} catch (ArithmeticException e) {
			/* 예외 처리 */
			// 절대 비워두지 말자.
			e.printStackTrace();

			// 1. 로깅 - 파일로 남기기
			System.out.println("error: " + e.getMessage());

			// 2. 사과
			System.out.println("미안합니다 ^^;");

			// 3. 정상종료
//			System.exit(0);
			return;
		} finally {
			System.out.println("자원 정리: ex) close file, socket, db connection");
		}

		System.out.println(" Some Code6...");
		System.out.println(" Some Code7...");
	}
}
