package paint;

public class Main {
	public static void main(String[] args) {
		Point point = new Point(10, 20);
//		point.setX(10);
//		point.setY(20);
//		drawPoint(point);
		draw(point);

//		point.disapear();
		point.show(true);

		ColorPoint point2 = new ColorPoint(100, 200, "red");
//		drawPoint(point2);
		draw(point2);

//		drawTriangle(new Triangle());
//		drawRectangle(new Rectangle());
		
//		drawShape(new Triangle());
//		drawShape(new Rectangle());
//		drawShape(new Circle());
		
		draw(new Triangle());
		draw(new Rectangle());
		draw(new Circle());
		
		draw(new GraphicText("Hello World"));
		
		// instanceof 연산자
		Circle c = new Circle();
		
		System.out.println(c instanceof Circle);
		System.out.println(c instanceof Shape);
		System.out.println(c instanceof Object);
		
		// 오류: 연산자 우측항이 클래스인 경우, 
		// 		레퍼런스 하고 있는 class 타입의 hierarchy 상에서 상위, 하위만 instanceof 연산자를 사용할 수 있다. 
//		System.out.println(c instanceof Point);
		
		Object o = new Circle();
		System.out.println(o instanceof String);
		
		// 연산자 우측항이 인터페이스인 경우
		// Hierachy 상관없이 instanceof 연산자를 사용할 수 있다.
	}
	
	private static void draw(Drawable drawable) {
		drawable.draw();
	}

	private static void drawPoint(Point point) {
		point.show();
	}

//	private static void drawColorPoint(ColorPoint point) {
//		point.show();
//	}

	private static void drawShape(Drawable drawable) {
		drawable.draw();
	}

//	private static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	
//	private static void drawRectangle(Rectangle rectangle) {
//		rectangle.draw();
//	}
}
