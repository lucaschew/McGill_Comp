package A5;
import java.util.*;

public class Planting_Trees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int numTrees = sc.nextInt();
		
		int daysOver = 0;
		int highest = 0;
		
		int[] trees = new int[numTrees];
		
		for (int i = 0; i < numTrees; i++) {
			trees[i] = sc.nextInt();
			
			if (trees[i] > highest)
				highest = trees[i];
		}
		
		Arrays.sort(trees);
//		System.out.println();
//		for (int a: trees) {
//			System.out.print(a + " ");
//		}
//		System.out.println();
		
		for (int i = numTrees-1; i >= 0; i--) {
			
			if (trees[i] > daysOver) {
				daysOver = trees[i];
			}
			
			//System.out.println(daysOver + numTrees + 1);
			
			daysOver--;
		}
		
		System.out.println(daysOver + numTrees + 2);
		
	}

}
