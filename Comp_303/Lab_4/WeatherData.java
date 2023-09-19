package Lab_4;

import java.util.ArrayList;

public class WeatherData {

	private double temperature;
	private double humidity;
	private double pressure;
	
	private final ArrayList<Display> observerList;
	
	public WeatherData() {
		observerList = new ArrayList<>();
		temperature = 0;
		humidity = 0;
		pressure = 0;
	}
	
	public void addObserver(Display d) {
		observerList.add(d);
	}
	
	public void setMeasurements(double temp, double hum, double pres) {
		this.temperature = temp;
		this.humidity = hum;
		this.pressure = pres;
		
		for (Display d: observerList) {
			//System.out.println(d);
			d.update(temp, hum, pres);
		}
	}
	
	public double getTemperature() {
		return this.temperature;
	}

	public double getHumidity() {
		return this.humidity;
	}
	
	public double getPressure() {
		return this.pressure;
	}
	
	public ArrayList<Display> getList() {
		return new ArrayList<Display>(observerList);
	}
	
	
}
