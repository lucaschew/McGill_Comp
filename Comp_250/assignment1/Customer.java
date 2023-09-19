package assignment1;

public class Customer {

	private String name;
	private int balance;
	private Basket basket;
	
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		basket = new Basket();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public Basket getBasket() {
		return this.basket;
	}
	
	public int addFunds(int add) {
		if (add < 0) {
			throw new IllegalArgumentException();
		}
		
		balance += add;
		return balance;
	}
	
	public void addToBasket(MarketProduct product) {
		this.basket.add(product);
	}
	
	public boolean removeFromBasket(MarketProduct product) {
		return this.basket.remove(product);
	}
	
	public String checkOut() {
		
		if (basket.getTotalCost() > this.balance) {
			throw new IllegalStateException();
		}
		
		this.balance -= basket.getTotalCost();
		String reciept = basket.toString();
		basket.clear();
		return reciept;
		
	}
	
}
