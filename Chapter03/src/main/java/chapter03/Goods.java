package chapter03;

public class Goods {
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public static int countOfGoods;
	
	public Goods() {
		// 클래스이름 생략 가능
		countOfGoods = 10;
	}

	@Override
	public String toString() {
		return this.name + " " + this.price + " " + this.countStock + " " + this.countSold;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCountStock() {
		return countStock;
	}

	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}

	public int getCountSold() {
		return countSold;
	}

	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	public int calcDiscountPrice(float percentage) {
		int result = (int) (price * percentage);
		return result;
	}

}
