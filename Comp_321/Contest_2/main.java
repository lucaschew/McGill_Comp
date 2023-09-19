package Contest_2;
import java.util.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNextLine()) {
		
			int numSets = sc.nextInt();
			int commands = sc.nextInt();
			
			int[] arr = new int[numSets];
			
			for (int i = 0; i < numSets; i++) {
				arr[i] = i;
			}
			
			for (int com = 0; com < commands; com++) {
			
				int a = sc.nextInt();
				
				if (a == 1) {
					int b = sc.nextInt();
					int c = sc.nextInt();
					
					int setA = arr[b-1];
					int setB = arr[c-1];
					
					for (int i = 0; i < numSets; i++) {
						if (arr[i] == setB)
							arr[i] = setA;
					}
					
				}
				
				if (a == 2) {
					int b = sc.nextInt();
					int c = sc.nextInt();
					
					arr[c-1] = arr[b-1];
				}
				
				if (a == 3) {
					int b = sc.nextInt();
					
					int temp = arr[b];
					
					int sum = 0;
					for (int i = 0 ; i < numSets; i++) {
						if (arr[i] == temp) {
							sum += i+1;
						}
					}
					
//					for( int lol: arr) {
//						System.out.println(lol);
//					}
					
					System.out.println(b + " " + sum);
				}
			}
			
			sc.nextLine();
			
		}
		
	}

}
