package A2;
import java.util.*;

public class Doorman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int absDiff = sc.nextInt();
		sc.nextLine();
		int mCounter = 0, wCounter = 0;
		
		String line = sc.nextLine();
		
		while (line.length() > 0) {
			//System.out.println(line);
			
			if (line.charAt(0) == 'M') {
				if ((int) Math.abs((mCounter + 1) - wCounter) > absDiff) {
					if (line.length() > 1 && line.charAt(1) != line.charAt(0)) {
						wCounter++;
						line = line.substring(0, 1) + line.substring(2, line.length());
					} else {
						break;
					}
				} else {
					mCounter++;
					line = line.substring(1, line.length());
				}
			}
			
			else if (line.charAt(0) == 'W') {
				if ((int) Math.abs(mCounter - (wCounter + 1)) > absDiff) {
					if (line.length() > 1 && line.charAt(1) != line.charAt(0)) {
						mCounter++;
						line = line.substring(0, 1) + line.substring(2, line.length());
					} else {
						break;
					}
				} else {
					wCounter++;
					line = line.substring(1, line.length());
				}
				
			}
		}
		
		System.out.println(wCounter + mCounter);

	}

}
