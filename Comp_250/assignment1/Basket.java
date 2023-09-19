package assignment1;

public class Basket {

	private MarketProduct[] products;

	public Basket() {
		products = new MarketProduct[0];
	}
	
	public MarketProduct[] getProducts() {
		
		return products.clone();
	}
	
	public void add(MarketProduct product) {
		
		MarketProduct[] tempProducts = new MarketProduct[products.length+1];
		
		for (int i = 0; i < products.length; i++) {
			tempProducts[i] = products[i];
		}
		
		tempProducts[tempProducts.length-1] = product;
		products = tempProducts;
		
	}
	
	public boolean remove(MarketProduct product) {
		
		for (int i = 0; i < products.length; i++) {
		
			if (products[i].getClass() == product.getClass() &&  products[i].equals(product)) {
				products[i] = null;
				fillSpaces();
				return true;
			}
			
		}
		
		return false;
	}
	
	private void fillSpaces() {
		
		MarketProduct[] tempProducts = new MarketProduct[products.length-1];
		int counter = 0;
		for (int i = 0; i < products.length; i++) {
			
			if (products[i] != null) {
				tempProducts[counter++] = products[i];
			}
			
		}
		
		products = tempProducts;
		
	}
	
	public void clear() {
		//Very inefficient due to storing more memory
		products = new MarketProduct[0];
	}
	
	public int getNumOfProducts() {
		
		int counter = 0;
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null)
				counter++;
		}
		
		return counter;
		
	} 
	
	public int getSubTotal() {
		
		int sum = 0;
		
		for (MarketProduct product: products) 
			sum += product.getCost();
		
		return sum;
		
	}
	
	public int getTotalTax() {
		
		int tax = 0;
		
		for (MarketProduct product: products) {
			if (product instanceof Jam) {
				tax += (int) (product.getCost() * 0.15);
			}
		}
		
		return tax;
		
	}
	
	public int getTotalCost() {
		
		return (getSubTotal() + getTotalTax());
	}
	
	public String toString() {
		
		String str = "";
		
		for (MarketProduct product: products) {
			
			if (product != null) {
				str+=(product.getName() + "\t" + dollarConversion(product.getCost()) + "\n");
			}
		}
		
		str+="\n";
		str+="Subtotal\t" + dollarConversion(getSubTotal()) + "\n";
		str+="Total Tax\t" + dollarConversion(getTotalTax()) + "\n";
		str+="\n";
		str+="Total Cost\t" + dollarConversion(getTotalCost()) + "\n";
		
		return str;
		
	}
	
	private String dollarConversion(int value) { 
		
		if (value == 0 ) {
			return "-";
		} else {
			String dollar = Integer.toString((int) value/100);
			String cents = Integer.toString((int) value%100);
			if (cents.length() == 1) {
				cents = "0"+cents;
			}
			return dollar + "." + cents;
		}
		
	}
	
}
