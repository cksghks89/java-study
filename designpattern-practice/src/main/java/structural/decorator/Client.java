package structural.decorator;

public class Client {
	public static void main(String[] args) {
		Component component = new ConcreteComponent("Hello");
		System.out.println(component.operation());

		Decorator decorator = new ConcreteDecorator(component);
		System.out.println(decorator.operation());

		Decorator decorator2 = new ConcreteDecorator(decorator);
		System.out.println(decorator2.operation());

		Decorator bracesDecorator = new BracesDecorator(component);
		System.out.println(bracesDecorator.operation());

		System.out.println(new ParenthesesDecorator(bracesDecorator).operation());
		System.out.println(new BracesDecorator(new ParenthesesDecorator(component)).operation());
	}
}
