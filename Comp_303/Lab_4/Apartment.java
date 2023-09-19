package Lab_4;

public class Apartment extends Property {
	
	private int roomNum;

	public Apartment(String location, double price, int roomNum) {
		super(location, price);
		this.roomNum = roomNum;
		// TODO Auto-generated constructor stub
	}
	
	public int getRoomNumber() {
		return this.roomNum;
	}
	
	@Override
	public void getInfo() {
		super.getInfo();
		System.out.println(roomNum);
	}

}
