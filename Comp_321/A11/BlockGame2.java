package A11;
import java.util.*;

public class BlockGame2 {
	
	private static boolean canWin = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong(), b = sc.nextLong();
		
		boolean player = true;
		long max = Math.max(a, b);
		long min = Math.min(a, b);
		
		while(true) {
			
			if (max % min == 0 || max / min > 1) {
				
				if (player)
					canWin = true;
				break;
			}
			
			if (max-min < min) {
				long temp = max-min;
				max = min;
				min = temp;
			}
			player = !player;
			
		}
		
		
		if (canWin) {
			System.out.println("win");
		} else {
			System.out.println("lose");	
		}
	}
}
