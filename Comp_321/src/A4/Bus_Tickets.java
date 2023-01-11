package A4;
import java.util.*;

public class Bus_Tickets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int price = sc.nextInt();
		int periodPrice = sc.nextInt();
		int periodLength = sc.nextInt();
		int numOfTrips = sc.nextInt();
		sc.nextLine();
		
		String[] tripString = sc.nextLine().split(" ");
		
		int[] activeDays = new int[Integer.parseInt(tripString[numOfTrips-1]) + 2];
		int[] tracker = new int[Integer.parseInt(tripString[numOfTrips-1]) + 2];
		
		for (int i = 0; i < numOfTrips; i++) { 
			activeDays[Integer.parseInt(tripString[i]) + 1]++;
		}
		
		for (int i = 1; i< tracker.length; i++) {
			if (activeDays[i] == 0) { 
				tracker[i] = tracker[i-1];
				continue; 
			};
			
			int previousDayValue = tracker[i-1];
			
			int periodPurchasePrice = Integer.MAX_VALUE;
			if (i - periodLength + 1 > 0) {
				periodPurchasePrice = tracker[i - periodLength + 1] + periodPrice;
			}
			
			//System.out.println(i - periodLength + 1);
			
			tracker[i] = Math.min(previousDayValue + price, periodPurchasePrice);
		}
		
		System.out.println(tracker[tracker.length-1]);

	}

}

