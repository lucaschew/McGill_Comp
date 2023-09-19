package Lab_4;

public class Display1 implements Display {
	
	private double temperature;
	private double humidity;

	public Display1() {
		temperature = 0;
		humidity = 0;
	}
	
	@Override
	public void update(double temp, double hum, double pres) {
		// TODO Auto-generated method stub
		this.temperature = temp;
		this.humidity = hum;
		//System.out.println("D1 update");
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public double getHumidity() {
		return this.humidity;
	}

}
