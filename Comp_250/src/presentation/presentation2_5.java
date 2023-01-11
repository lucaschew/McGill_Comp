package presentation;

import java.util.Arrays;

public class presentation2_5 {
	
/*

Suppose that you are given an array of n different numbers that strictly increase from index 0 to index
m, and strictly decrease from index m to index n − 1, where n is known but m is unknown. Note that
there is a unique largest number in such a list, and it is at index m. Write a recursive method that
returns the index m of the largest number in the array, in time proportional to log n. Your method
should not use any additional data structure

For Monkey Brain:

You have n different numbers
In array you have {...m...(n-1)}
From 0 -> m: Numbers increase
From m -> (n-1): Numbers decrease
Find m (aka, the largest number)
Do it in log(n) time

*/
	
	public static void main(String[] args) {
		
		int num = 10;
		
		Integer[][] arr = {{1,3,5,7,9,8,6,4,2,0},	//m = 4
						   {1,3,5,7,8,9,6,4,2,0},	//m = 5
						   {1,3,4,5,7,8,9,6,2,0},	//m = 6
						   {1,3,2},					//m = 1
						   {1},						//m = 0
						   {1,3},					//m = 1
						   {1,3,5,7,9},				//m = 4
						   {1,98,7,6,3},			//m = 1
						   {3,2,1},					//m = 0
						   {}
						   };
		
		
//		for (Integer[] temp: arr) {
//			if (temp.length == 0) {
//				System.out.println("Length is 0 \n------------------------------------------------");
//				continue;
//			}
//			int result = largestIndex(temp);
//			System.out.println(Arrays.toString(temp));
//			System.out.println("Index: " + result + " Value: " + temp[result]);
//			System.out.println("------------------------------------------------");
//		}

		//Single Check
		int result = largestIndex(arr[0]);
		System.out.println(arr[0]);
		System.out.println("Index: " + result + " Value: " + arr[0][result]);
		System.out.println("------------------------------------------------");
		
	}
	
	public static int largestIndex(Integer[] arr) {
		
		System.out.println(Arrays.toString(arr) + " " + arr[(arr.length+1)/2 - 1]);
		
		if (arr.length == 2) {
			if (arr[0] > arr[1])
				return 0;
			else
				return 1;
		} else if (arr.length == 1) {
			return 0;
		} else {
			
			int halfPoint = (arr.length)/2;
			
			if (arr[halfPoint] > arr[halfPoint + 1] && arr[halfPoint] > arr[halfPoint-1]) {
				return halfPoint;
			}
			else if (arr[halfPoint] < arr[halfPoint+1]) {
				//System.out.println("Top Half");
				return largestIndex(Arrays.copyOfRange(arr, halfPoint, arr.length)) + halfPoint;
			} else {
				//System.out.println("Bottom Half");
				return largestIndex(Arrays.copyOfRange(arr, 0, halfPoint));
			}
		}
		
		/*
		Runtime Explained
		
			If we look at the size of the array, every iteration we either return a value or split the array.
			If we split the array, then let x be iteration and y be the size:
			
			x=1		y = size/2
			x=2		y = (size/2)/2 = size/4
			x=3		y = (size/4)/2 = size/8
			x=k		y = size/2^k
			
			2^k = size
			k = log base 2 (size)
			k = log(size)
			O(n) = log(n)
		 
		*/
		
	}

}
