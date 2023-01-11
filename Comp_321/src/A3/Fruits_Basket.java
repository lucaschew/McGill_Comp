package A3;
import java.util.*;

public class Fruits_Basket {

	private static int count = 0;
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[] arr = new int[size];
		
		for (int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
		}
		
		DFS(arr, 0, 0);
		
		System.out.println(count);

	}
	
	private static void DFS(int[] arr, int currentWeight, int lastIndex) {
		
		if (currentWeight >= 200) {
			count += currentWeight;
		}
		
		if (lastIndex == arr.length) return;
		
		for (int i = lastIndex; i < arr.length; i++) {
			DFS(arr, currentWeight+arr[i], i+1);
		}
		
	}

}
