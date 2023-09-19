package Lab_4;

public class House extends Property {
	
	private int backyardSpace;

	public House(String location, double price, int backyardSpace) {
		super(location, price);
		this.backyardSpace = backyardSpace;
		// TODO Auto-generated constructor stub
	}
	
	public int getBackyardSpace() {
		return backyardSpace;
	}
	
	@Override
	public void getInfo() {
		super.getInfo();
		System.out.println(backyardSpace);
	}

}
