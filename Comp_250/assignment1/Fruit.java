package assignment1;

public class Fruit extends MarketProduct {

	private double weight; //in kg
	private int price; // price (cents) per kg
	
	public Fruit(String name, double weight, int price) {
		super(name);
		this.weight = weight;
		this.price = price;
	}


	public int getCost() {

		return (int) (this.price*this.weight);
	}


	public boolean equals(Object o) {

		if (o.getClass() != this.getClass())
			return false;
		
		Fruit tempFruit = (Fruit) o;
		
		if (tempFruit.getName().equals(this.getName()) &&
				tempFruit.getCost() == tempFruit.getCost())
			return true;
		
		return false;
	}

}
