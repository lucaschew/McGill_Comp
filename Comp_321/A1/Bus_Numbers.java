package A1;
import java.util.*;

public class Bus_Numbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int inputTimes = sc.nextInt();
		
		List<Integer> busStops = new ArrayList<>();
		for (int i = 0; i < inputTimes; i++) {
			busStops.add(sc.nextInt());
		}
		
		Collections.sort(busStops);
		String output = "";
		
		for (int i = 0; i < inputTimes; i++) {
			
			if (i != 0)
				output += " ";
			
			if (i+2 < inputTimes && busStops.get(i) == busStops.get(i+1) - 1 && busStops.get(i) == busStops.get(i+2) - 2) {
				
				output += "" + busStops.get(i);
				i += 2;
				while (i+1 < inputTimes && busStops.get(i) == busStops.get(i+1) - 1) {
					i++;
				}
				
				output += "-" + busStops.get(i);
			} else {
				output += "" + busStops.get(i);
			}
		}
		
		System.out.println(output);
	}

}
