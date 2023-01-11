package A2;
import java.util.*;

/*
 * 
1
3
Fred Barney
Barney Betty
Betty Wilma

 */

public class Virtual_Friends {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int repeats = sc.nextInt();
		
		for (int r = 0; r < repeats; r++) {
			
			int commands = sc.nextInt();
			sc.nextLine();
			HashMap<String, HashSet<String>> hm = new HashMap<>();
			int counter = 0;
			
			for (int c = 0; c < commands; c++) {
				//Adding to hashmap
				String[] people = sc.nextLine().split(" ");
				 
				if (!hm.containsKey(people[0])) {
					hm.put(people[0], new HashSet<String>());
					counter++;
				} 
				hm.get(people[0]).add(people[1]);
				 
				if (!hm.containsKey(people[1])) {
					hm.put(people[1], new HashSet<String>());
					counter++;
				} 
				hm.get(people[1]).add(people[0]);
				
				System.out.println(counter);
				 
			}
			
		}

	}

}
