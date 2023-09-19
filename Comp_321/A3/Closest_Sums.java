package A3;
import java.util.*;

public class Closest_Sums {
	
	public static List<Integer> sortedHs;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int caseCounter = 1;
		
		while (sc.hasNextInt()) {
			int sumListSize = sc.nextInt();
			int[] input = new int[sumListSize];
			
			for (int i = 0; i < sumListSize; i++) {
				input[i] = sc.nextInt();
			}
			
			HashSet<Integer> hs = new HashSet<>();
			for (int i = 0; i < sumListSize; i++) {
				for (int z = i+1; z < sumListSize; z++) {
					hs.add(input[i] + input[z]);
				}
			}
			
			sortedHs = new ArrayList<Integer>(hs);
			Collections.sort(sortedHs);
			
			int matchers = sc.nextInt();
			
			System.out.println("Case " + caseCounter + ":");
			//System.out.println(sortedHs.toString());
			
			for (int i = 0; i < matchers; i++) {
				int currentMatch = sc.nextInt();
				
				System.out.println("Closest sum to " + currentMatch + " is " + searchForClosest(currentMatch) + ".");
			}
			
			caseCounter++;
		}

	}
	
	public static int searchForClosest(int num) {
		
		//Edge Cases
		if (num <= sortedHs.get(0)) { return sortedHs.get(0); }
		if (num >= sortedHs.get(sortedHs.size()-1)) { return sortedHs.get(sortedHs.size()-1); }
		
		
		int low = 0, mid = 0, high = sortedHs.size();
		while (low < high) {
			mid = (low + high)/2;
			
			if (sortedHs.get(mid) == num) { return sortedHs.get(mid); }
			
			if (sortedHs.get(mid) > num) {
				if (mid != 0 && sortedHs.get(mid-1) < num) {
					if (sortedHs.get(mid) - num <= num - sortedHs.get(mid-1)) {
						return sortedHs.get(mid);
					} else {
						return sortedHs.get(mid-1);
					}
				}
				
				high = mid;
				
			} else if (sortedHs.get(mid) < num) {
				
				if (mid < sortedHs.size()-1 && sortedHs.get(mid+1) > num) {
					if (num - sortedHs.get(mid) <= sortedHs.get(mid+1) - num) {
						return sortedHs.get(mid);
					} else {
						return sortedHs.get(mid+1);
					}
				}
				
				low = mid+1;
			}
		}
		
		return 0;
	}

}
