package A5;
import java.util.*;

class Socks implements Comparable<Socks> {
	
	public int numberOfSocks;
	public int color;
	
	
	public int compareTo(Socks o) {
		// TODO Auto-generated method stub
		return this.numberOfSocks - o.numberOfSocks;
	}
	
	public Socks(int color) {
		this.color = color;
		this.numberOfSocks = 1;
	}
	
	public String toString() {
		
		return (numberOfSocks + " " + color);
	}
	
}

public class Colouring_Socks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int numSocks = sc.nextInt();
		int cap = sc.nextInt();
		int diffColor = sc.nextInt();
		
		HashMap<Integer, Socks> li = new HashMap<>();
		
		for (int i = 0; i < numSocks; i++) {
			int temp = sc.nextInt();
			if (li.containsKey(temp)) {
				li.get(temp).numberOfSocks++;
			} else {
				li.put(temp, new Socks(temp));
			}
		}
		
		int machines = 0;
		ArrayList<Socks> leftover = new ArrayList<Socks>();
		
		for (int key: li.keySet()) {
			Socks s = li.get(key);
			
			machines += s.numberOfSocks / cap;
			s.numberOfSocks = s.numberOfSocks % cap;
			if (s.numberOfSocks > 0) {
				leftover.add(s);
			}
		}
		
		Collections.sort(leftover);
		
//		System.out.println(machines);
//		System.out.println(leftover);
		
		while (leftover.size() > 0) {
			
			int current = leftover.get(leftover.size()-1).numberOfSocks;
			int currentDiff = 0;
			leftover.remove(leftover.size()-1);
			
			while (current < cap && currentDiff < diffColor && leftover.size() > 0) {
				
				if (leftover.get(0).numberOfSocks + current > cap) {
					leftover.get(0).numberOfSocks -= cap - current;
					current = cap;
					currentDiff++;
				} else {
					current += leftover.get(0).numberOfSocks;
					leftover.remove(0);
					currentDiff++;
				}
				
			}
			
			if (current > 0) {
				current = 0;
				currentDiff = 0;
				machines++;
			}
			
		}
		
		System.out.println(machines);
		
		
	}

}
