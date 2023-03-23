package Lab_1;

public class Food {
	
	private final String name;
	private final String barcode;
	private double price;
	
	private final FoodLabel label;

	//Task 1
//	public Food(String name, String barcode, double price) {
//		
//		this.name = name;
//		this.barcode = barcode;
//		this.price = price;
//		this.label = null;
//	}
	
	//Task 2
	public Food(String name, String barcode, double price, FoodLabel label) {
		
		this.name = name;
		this.barcode = barcode;
		this.price = price;
		this.label = label;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getBarcode() {
		return this.barcode;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public FoodLabel getLabel() {
		return this.label;
	}
	
	public String toString() {

		return this.name + " | " + this.label +  " | $" + this.price + " | " + this.barcode;
		
	}
	
	

}
