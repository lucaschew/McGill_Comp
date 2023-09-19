package assignment1;

public class Egg extends MarketProduct {

	private int numberOfEggs;
	private int priceOfDozenEggs; //in cents
	
	public Egg(String name, int number, int price) {
		super(name);
		this.numberOfEggs = number;
		this.priceOfDozenEggs = price;
	}

	public int getCost() {
		return (int) ((double) this.priceOfDozenEggs/12*this.numberOfEggs);
	}

	public boolean equals(Object o) {
		
		if (o.getClass() != this.getClass())
			return false;
		
		Egg tempEgg = (Egg) o;
		
		if (tempEgg.getName().equals(this.getName()) &&
				tempEgg.getCost() == this.getCost())
			return true;
		
		return false;
	}

	
}
