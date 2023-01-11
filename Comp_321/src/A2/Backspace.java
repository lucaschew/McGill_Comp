package A2;

import java.util.*;

public class Backspace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		int backCounter = 0;
		
		for (int i = line.length() - 1; i >= 0; i--) {
			//System.out.println(line.charAt(i));
			if (line.charAt(i) == '<')
				backCounter++;
			else if (backCounter > 0) {
			    i -= backCounter - 1;
				backCounter = 0;
			} else {
			    sb.append(line.charAt(i));
			}
		}
		
		System.out.println(sb.reverse().toString());
	}

}
