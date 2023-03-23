package A2;

import java.util.*;

public class A2_Q2 {
	
	private static int[] dpArray; 

	public static int change(int[] denominations) {
		
		// Create array to be twice the length of the highest denomination
		// Assuming that a number will crash somewhere between the constraints
		dpArray = new int[denominations[denominations.length-1] * 2];
		Arrays.fill(dpArray, Integer.MAX_VALUE);
		dpArray[0] = 0;
		
		// Loop for each index, getting value of greedy and DP approach
		// Compare both and if not equal, return
		for (int i = 1; i < denominations[denominations.length-1] * 2; i++) {
			
			if (greedy(denominations, i) != dp(denominations, i)) {
				return i;
			}
			
		}
		
		return -1;
		
	}
	
	// Iterate from the highest number first, then decrease
	// Greedy Approach
	private static int greedy(int[] den, int value) {
		
		int numCoins = 0;
		
		for (int i = den.length-1; i >= 0; i--) {
			
			numCoins += value / den[i];
			value %= den[i];
			
		}
		
		return numCoins;
	}
	
	// Using the previous numbers before it, either stick with current number or use previous number + 1
	private static int dp(int[] den, int value) {
		
		for (int i = 0; i < den.length; i++) {
			
			if (value - den[i] >= 0) {
				dpArray[value] = Math.min(dpArray[value], dpArray[value - den[i]] + 1);
			}
			
		}
		
		return dpArray[value];
		
	}
	
	public static void main (String[] args) {
		
		System.out.println(change(new int[] {1,2,4,8}));
		
	}

}
