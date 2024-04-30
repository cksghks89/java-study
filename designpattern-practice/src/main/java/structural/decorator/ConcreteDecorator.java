package structural.decorator;

public class ConcreteDecorator extends Decorator {
	
	public ConcreteDecorator(Component component) {
		super(component);
	}

	@Override
	public String operation() {
		return "{" + super.component.operation() + "}";
	}
}
