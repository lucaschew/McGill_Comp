package A3;
import java.util.*;

public class Lucky_Numbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		char[] nums = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		char[] current = new char[input];
		int count = 0;
		
		for (int i = 0; i < input; i++) {
			for (int x = 0; x < nums.length; x++)
				for (int z = 0; z < nums.length; z+=i+1) {
					if (i == 0 && z == 0 ) { continue; }
					
					current[x] = nums[z];
					String temp = new String(current).trim();
					System.out.println(temp + " " + new String(current));
					if (isDivisible(Integer.parseInt(temp), 1, input)) { count++; }
					
				}
		}
		
		
		System.out.println(count);

	}
	
	public static boolean isDivisible(int num, int divisor, int maxDivisor) {
		
		if (divisor > maxDivisor) { return true; }
		
		if (num >= divisor && num % divisor == 0) { return isDivisible(num, divisor+1, maxDivisor); }
		else {
			return false;
		}
	}

}
