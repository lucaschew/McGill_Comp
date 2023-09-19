package A5;
import java.util.*;

public class Watering_Grass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int numOfSprinklers = sc.nextInt();
			int length = sc.nextInt();
			int width = sc.nextInt();
			
			HashMap<Double, PriorityQueue<Double>> hm = new HashMap<>();
			
			for (int i = 0; i < numOfSprinklers; i++) {
				int pos = sc.nextInt(), rad = sc.nextInt();
				
//				System.out.println(i);
				
				double new_rad = Math.sqrt((rad*rad - (width/2.0) * (width/2.0)));
				//System.out.println(rad + " " + new_rad);
				
				if (hm.get(Math.max(0, pos-new_rad)) == null) {
					hm.put(Math.max(0, pos-new_rad), new PriorityQueue<Double>(Comparator.reverseOrder()));
				}
				hm.get(Math.max(0, pos-new_rad)).add(pos + new_rad);
			}
			
			
			int counter = 0, hsIndex = 0;
			double currentPos = 0, previousPos = 0;
			
			Double[] keyArr = hm.keySet().toArray(new Double[hm.keySet().size()]);
			Arrays.sort(keyArr);
			
//			System.out.println(hm);
//			for (double key : keyArr) {
//				System.out.print(key + " ");
//			}
//			System.out.println();
			
			
			while (hsIndex < keyArr.length && currentPos < length) {
				
				previousPos = currentPos;
				boolean breaker = false;
				
				for (; hsIndex < keyArr.length; hsIndex++) {
					
					for (double value: hm.get(keyArr[hsIndex])) {
						
						if (keyArr[hsIndex] <= previousPos) {
							
							currentPos = Math.max(value, currentPos);
							breaker = true;
						} else {
							breaker = false;
							break;
							
						}
					}
					
					if (!breaker) break;
					
				}
				
//				System.out.println(currentPos + " " + previousPos);
				
				if (previousPos == currentPos) {
					counter = -1;
					break;
				} else {
					counter++;
				}
				
				//System.out.println(counter + " " + currentPos + " " + hsIndex);
				
			}
			
			if (counter == 0) counter = -1;
			System.out.println(counter);
			//System.exit(0);
			
		}
		

	}
	
//	public static void DFS(int currentPos, int previousPos, int counter, int length, int width, HashMap<Integer, ArrayList<Integer>> hm) {
//		
//		//System.out.println(currentPos + " " + previousPos + " " + counter);
//		if (counter > lowestResult) return;
//		
//		if (currentPos >= length + width/2) {
//			lowestResult = Math.min(counter, lowestResult);
//		}
//		
//		for (int i = currentPos; i > previousPos; i--) {
//			if (hm.get(i) == null) continue;
//			
//			for (int temp: hm.get(i)) {
//				DFS(temp-(width/2), i, counter +1, length, width, hm);
//			}
//		}
//	}

}
