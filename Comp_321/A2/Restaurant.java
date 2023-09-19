package A2;
import java.io.*;

public class Restaurant {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int lines = Integer.parseInt(br.readLine());
		
		while (lines != 0) {
			int first = 0, second = 0;
			for (int i = 0; i < lines; i++) {
				String[] command = br.readLine().split(" ");
				
				if (command[0].equals("DROP")) {
					second += Integer.parseInt(command[1]);
					System.out.println(command[0] + " 2 " + command[1]);
				}
				
				if (command[0].equals("TAKE")) {
					if (Integer.parseInt(command[1]) <= first) {
						System.out.println(command[0] + " 1 " + command[1]);
						first -= Integer.parseInt(command[1]);
					} else {
						int diff = Integer.parseInt(command[1]);
						if (first != 0) {
							System.out.println(command[0] + " 1 " + first);
							diff -= first;
						}
						System.out.println("MOVE 2->1 " + second);
						first = second;
						second = 0;
						System.out.println(command[0] + " 1 " + diff);
						first -= diff;
						
					}
				}
			}
			
			lines = Integer.parseInt(br.readLine());
			if (lines != 0) System.out.println();
			
		}

	}

}
