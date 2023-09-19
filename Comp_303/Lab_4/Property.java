package Lab_4;

public class Property {

    private final String location;
    private final double price;

    public Property(String location, double price){
        this.location= location;
        this.price= price;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }
    
    public void getInfo() {
    	System.out.println(location);
    	
    	System.out.println(price);
    }
}
