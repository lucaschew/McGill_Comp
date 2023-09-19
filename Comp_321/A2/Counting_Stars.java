package A2;
import java.util.*;

public class Counting_Stars {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int caseNum = 1;
		
		while (sc.hasNextLine()) {
		
			int y = sc.nextInt(), x = sc.nextInt();
			sc.nextLine();
			
			String[][] arr = new String[y][x];
			int count = 0;
			
			for (int i = 0; i < y; i++) {
				arr[i] = sc.nextLine().split("");
			}
			
			for (int i = 0; i < y; i++) {
				for (int xell = 0; xell < x; xell++) {
					
					if (arr[i][xell].equals("-")) {
						arr = DFS(arr, xell, i);
						count++;
					}
					
				}
			}
			
			System.out.println("Case " + caseNum + ": " + count);
			caseNum++;
		}
		
	}
	
	public static String[][] DFS(String[][] arr, int x, int y) {
		
		if (arr[y][x].equals("-")) {
			
			arr[y][x] = "#";
		
			if (x + 1 < arr[0].length)
				arr = DFS(arr, x+1, y);
			
			if (x - 1 >= 0)
				arr = DFS(arr, x-1, y);
			
			if (y + 1 < arr.length)
				arr = DFS(arr, x, y+1);
			
			if (y - 1 >= 0)
				arr = DFS(arr, x, y-1);
		}
		
		return arr;
	}

}
