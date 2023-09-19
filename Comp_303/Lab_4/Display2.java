package Lab_4;

public class Display2 implements Display {
	
	private double humidity;
	private double pressure;
	
	public Display2() {
		humidity = 0;
		pressure = 0;
	}

	@Override
	public void update(double temp, double hum, double pres) {
		// TODO Auto-generated method stub
		this.humidity = hum;
		this.pressure = pres;
		//System.out.println("D2 update");
		
	}
	
	public double getPressure() {
		return this.pressure;
	}
	
	public double getHumidity() {
		return this.humidity;
	}

}
