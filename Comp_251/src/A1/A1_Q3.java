package A1;
import java.util.*;

public class A1_Q3 {

	public static int elements(int[] sizes) {
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		int maxLength = 0;
		int startPoint = 0;
		
		for (int i = 0; i < sizes.length; i++) {
			
			if (hm.get(sizes[i]) != null) {
				startPoint = Math.max(startPoint, hm.get(sizes[i]) + 1);
			}
				
			maxLength = Math.max(maxLength, i - startPoint + 1);
			hm.put(sizes[i], i);

		}
		
		return maxLength;
	}
	
	public static void main(String[] args) {
		
		int[] temp = new int[] {1, 1, 1, 1, 2};
				
				
		System.out.println(elements(temp));
		
	}
	
}
