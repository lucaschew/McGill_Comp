package A2;

import java.util.*;

public class A2_Q4 {
	
	private static int[] mergeArray;
	
	public static double swaps(int[] passengers) {
		
		// Create array to use for storage
		mergeArray = new int[passengers.length];
		
		return mergeSort(passengers, 0, passengers.length-1);
	}
	
	// Default implementation of mergeSort, but including doubles for inversion count
	// Inversions are the number of pairs of numbers that are not in the right order (in this case, ascending)
	public static double mergeSort(int[] arr, int left, int right) {
		
		double inversionCount = 0;
		int mid = (left + right) / 2;
		
		if (right > left) {
			
			inversionCount += mergeSort(arr, left, mid);
			
			inversionCount += mergeSort(arr, mid + 1, right);
			
			inversionCount += merge(arr, left, mid, right);
			
		}
		
		return inversionCount;
	}
	
	// Default implementation of mergeSort + inversions
	public static double merge(int[] arr, int left, int mid, int right) {
		
		double inversionCount = 0;
		int leftIndex = left, rightIndex = mid + 1;
		int arrayPointer = left;
		
		while (leftIndex <= mid && rightIndex <= right) {
			
			if (arr[leftIndex] <= arr[rightIndex]) {
				
				mergeArray[arrayPointer] = arr[leftIndex];
				leftIndex++;
				
			} else {
				
				mergeArray[arrayPointer] = arr[rightIndex];
				rightIndex++;
				
				/* 
				 	This is how to calculate the # of inversions within the left and right
				   	Since we can assume that the left array is sorted, reaching this path means that the value in the right array was
				   		smaller than all the values in the left array, which means that it would create an inversion for each remaining left value
				   	Therefore, we can calculate the number of inversions by the remaining number of left array indexes.
				*/
				inversionCount += (mid + 1) - leftIndex;
				
			}
			
			arrayPointer++;
			
		}
		
		while (leftIndex <= mid) {
			mergeArray[arrayPointer] = arr[leftIndex];
			arrayPointer++;
			leftIndex++;
		}

		while (rightIndex <= right) {
			mergeArray[arrayPointer] = arr[rightIndex];
			arrayPointer++;
			rightIndex++;
		}
		
		for (int i = left; i <= right; i++) {
			arr[i] = mergeArray[i];
		}
				
		return inversionCount;
	}
	
	
	
	public static void main (String[] args) {
		
		int[] arr = new int[] {3, 1, 2};
		System.out.println(swaps(arr));
		
		for (int i: arr)
			System.out.print(i + " ");
		

		
	}
	
}
