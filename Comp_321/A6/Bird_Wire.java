package A6;
import java.util.*;

public class Bird_Wire {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int wire = sc.nextInt(), gap = sc.nextInt(), numBirds = sc.nextInt();
		
		if (wire - 12 <= 0) {
			System.out.print(0);
		}
		
		if (numBirds == 0) {	
			System.out.println((wire-12)/(gap) + 1);
		} else {
			
			int[] birds = new int[numBirds];
			int counter = 0;
			
			for (int i = 0; i < numBirds; i++) {
				birds[i] = sc.nextInt();
			}
			
			Arrays.sort(birds);
			
			// Left Side
			counter += (birds[0] - 6)/gap;
			// Right Side
			counter += ((wire - 6) - birds[numBirds-1])/gap;
			
			//Middle
			for (int i = 0; i < numBirds-1; i++) {
				int distance = birds[i+1] - birds[i];
				counter += (distance/gap) - 1;
			}
			
			System.out.println(counter);
			
		
		}
	}

}
