package A4;
import java.util.*;

public class Radio_Commercials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int numOfCommercials, costPerCommercial, highestSum = 0, sum = 0;

		numOfCommercials = sc.nextInt();
		costPerCommercial = sc.nextInt();
		
		for (int i = 0; i < numOfCommercials; i++) {
			int input = sc.nextInt();
			if (sum == 0 && input < costPerCommercial) {
				continue;
			}
			
			if (sum + input - costPerCommercial > 0) {
				sum += input - costPerCommercial;
				highestSum = Math.max(highestSum, sum);
			} else {
				highestSum = Math.max(highestSum, sum);
				sum = 0;
			}
			
		}
		
		System.out.println(highestSum);
		
	}

}
