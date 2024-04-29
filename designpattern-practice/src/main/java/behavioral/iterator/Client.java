package behavioral.iterator;

public class Client {
	public static void main(String[] args) {
		Aggregate<String> fruits = new AggregateImpl<>(new String[] {"Mango", "Banana", "Apple"});
		Iterator<String> iter = fruits.createIterator();

		while (iter.hasNext()) {
			String fruit = iter.next();
			System.out.println(fruit);
		}
	}
}
