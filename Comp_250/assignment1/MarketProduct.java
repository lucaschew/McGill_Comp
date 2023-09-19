package assignment1;

public abstract class MarketProduct {

	private String name;
	
	public MarketProduct(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return this.name;
	}
	
	abstract public int getCost();
	abstract public boolean equals(Object o);
	
}
