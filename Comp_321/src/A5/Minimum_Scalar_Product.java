package A5;
import java.util.*;

public class Minimum_Scalar_Product {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int totalCase = sc.nextInt();
		
		for (int c = 0; c < totalCase; c++) {
			
			int num = sc.nextInt();
			long[] a = new long[num];
			long[] b = new long[num];
			
			for (int i = 0; i < num; i++) {
				a[i] = sc.nextInt();
			}
			for (int i = 0; i < num; i++) {
				b[i] = sc.nextInt();
			}
			
			Arrays.sort(a);
			Arrays.sort(b);
			
			long sum = 0;
			
			for (int i = 0; i < num; i++) {
				sum += a[i] * b[num-1-i];
			}
			
			System.out.println("Case #" + (c+1) + ": " + sum);
		}

	}

}
