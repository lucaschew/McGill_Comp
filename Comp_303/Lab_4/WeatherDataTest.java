package Lab_4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WeatherDataTest {
	
	private static final double temp = 10;
	private static final double hum = 12.2;
	private static final double pres = 6.3;
	
	public static void main(String[] args) {
		
		WeatherDataSetMeasurementsTest();
		
		System.out.println();
		
		WeatherDataObserverSettingTest();
		
		
		
	}
	
	public static void WeatherDataSetMeasurementsTest() {
		
		WeatherData wd = new WeatherData();
		wd.setMeasurements(temp, hum, pres);
		
		System.out.println(temp + " " + wd.getTemperature());
		System.out.println(hum + " " + wd.getHumidity());
		System.out.println(pres + " " + wd.getPressure());
	}
	
	public static void WeatherDataObserverSettingTest() {
		
		WeatherData wd = new WeatherData();
		Display1 d1 = new Display1();
		Display2 d2 = new Display2();
		
		wd.addObserver(d1);
		wd.addObserver(d2);
		
		wd.setMeasurements(temp, hum, pres);

		
		System.out.println("Display 1");
		System.out.println(temp + " " + d1.getTemperature());
		System.out.println(hum + " " + d1.getHumidity());
		
		System.out.println("Display 2");
		System.out.println(hum + " " + d2.getHumidity());
		System.out.println(pres + " " + d2.getPressure());
		
	}
	
	
	

}
