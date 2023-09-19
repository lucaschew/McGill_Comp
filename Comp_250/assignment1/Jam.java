package assignment1;

public class Jam extends MarketProduct{

	private int numberOfJars; 
	private int price; // price (cents) per jar
	
	public Jam(String name, int numberOfJars, int price) {
		super(name);
		this.numberOfJars = numberOfJars;
		this.price = price;
	}


	public int getCost() {

		return (int) (this.price*this.numberOfJars);
	}


	public boolean equals(Object o) {

		if (o.getClass() != this.getClass())
			return false;
		
		Jam tempJam = (Jam) o;
		
		if (tempJam.getName().equals(this.getName()) &&
				tempJam.getCost() == this.getCost())
			return true;
		
		return false;
	}
	
}
