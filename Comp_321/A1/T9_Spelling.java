package A1;
import java.util.*;

public class T9_Spelling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int times = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < times; i++) {
			
			String str = sc.nextLine().toLowerCase();
			String out = "Case #" + (i+1) + ": ";
			
			for (int z = 0; z < str.length(); z++) {
				int number, repetition;
				int difference = str.charAt(z) - 'a';
				if (str.charAt(z) > 's') difference--;
				
				if (str.charAt(z) == 'z') {
					number = 9;
					repetition = 4;
				} else if (str.charAt(z) == 's') {
					number = 7;
					repetition = 4;
				} else if (str.charAt(z) == ' ') {
					number = 0;
					repetition = 1;
				} else {
					number = (int) Math.floor(difference / 3) + 2;
					repetition = (difference % 3) + 1;
				}
				
				if (out.substring(out.length()-1).equals(Integer.toString(number))) out += " ";
				for (int x = 0; x < repetition; x++) out += number;
			}
			
			System.out.println(out);
			
		}

	}

}
