package A6;
import java.util.*;

public class Union_Find {
	
	static List<List<Integer>> li;
	static int[] placement;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNextLine()) {
			
			int numSets = sc.nextInt();
			int commands = sc.nextInt();
			
			li = new ArrayList<>();
			placement = new int[numSets];
			
			for (int i = 0; i < numSets; i++) {
				li.add(new ArrayList<>());
				li.get(i).add(i);
				placement[i] = i;
			}

			for (int command = 0; command < commands; command++) {
				
				int function = sc.nextInt();
				
				if (function == 1) {
					union(sc.nextInt(), sc.nextInt());
				} else if (function == 2) {
					move(sc.nextInt(), sc.nextInt());
				} else if (function == 3) {
					
					int printNum = sc.nextInt();
					
					System.out.println(size(printNum) + " " + sum(printNum));
					
				}
				
			}	
			
			
			sc.nextLine();
		}
			

	}
	
	private static void union(int a, int b) {
		
		int placementA = placement[a-1];
		int placementB = placement[b-1];
		
		if (placementA == placementB) return;
		
		if (li.get(placementA).size() > li.get(placementB).size()) {
			
			for (int element: li.get(placementB)) {
				li.get(placementA).add(element);
				placement[element] = placementA;
			}
			li.get(placementB).clear();
			
		} else {
			
			for (int element: li.get(placementA)) {
				li.get(placementB).add(element);
				placement[element] = placementB;
			}
			li.get(placementA).clear();
			
		}
		
	}
	
	private static void move(int a, int b) {
		
		int placementA = placement[a-1];
		int placementB = placement[b-1];
		
		if (placementA == placementB) return;
		
		li.get(placementA).remove(Integer.valueOf(a-1));
		li.get(placementB).add(a-1);
		placement[a-1] = placement[b-1];
		
		
	}
	
	private static int sum(int a) {
		int sum = 0;
		
		for (int num: li.get(placement[a-1])) {
			sum += num+1;
		}
		
		return sum;
	}
	
	private static int size(int a) {
		return li.get(placement[a-1]).size();
	}

}
