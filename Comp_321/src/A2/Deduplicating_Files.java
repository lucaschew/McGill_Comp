package A2;
import java.util.*;

public class Deduplicating_Files {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int numOfLines = sc.nextInt();
		
		while (numOfLines > 0) {
			sc.nextLine();
			List<String> arr = new ArrayList<>();
			
			for (int i = 0; i < numOfLines; i++)
				arr.add(sc.nextLine());
			
			Set<String> hs = new HashSet<>(arr);
			int pairs = 0;
			
			List<String[]> sorted = new ArrayList<>();
			
			for (String temp: arr) {
				String[] l = temp.split(" ");
				Arrays.sort(l);
				sorted.add(l);
			}
			
			for (int i = 0; i < sorted.size(); i++) {
				for (int x = i+1; x < sorted.size(); x++) {
					
					//System.out.println(i + " " + x);
					if (Arrays.equals(sorted.get(i), sorted.get(x)) && !arr.get(i).equals(arr.get(x))) {

//						for (String a: sorted.get(i)) {
//							System.out.print(a);
//						}
//						System.out.println();
//						for (String a: sorted.get(x)) {
//							System.out.print(a);
//						}
//						System.out.println();
						pairs++;
					}
				}
			}
			
			
			
			System.out.println(hs.size() + " " + pairs);
			
			numOfLines = sc.nextInt();
		}

	}

}
