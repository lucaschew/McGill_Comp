package Contest_4;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cases = sc.nextInt();
		
		for (int c = 0; c < cases; c++) {
			
			sc.nextInt();
			
			HashMap<Integer, Set<Integer>> hm = new HashMap<>();
			System.out.println("Case #" + c+1 + ":");
			for (int i = 0; i < 20; i++) {
				
				int val = sc.nextInt();
				
				HashSet<Integer> keys = new HashSet(hm.keySet());
				
				for (int key: keys) {
					
					if (hm.containsKey(key + val)) {
						System.out.println(hm.get(key+val).toString().replace("[", "").replace("]", "").replace(",", ""));
						Set<Integer> temp = hm.get(key);
						temp.add(val);
						System.out.println(temp.toString().replace("[", "").replace("]", "").replace(",", ""));
						System.out.println();
						System.exit(0);
					} else {
						Set<Integer> temp = new HashSet(hm.get(key));
						temp.add(val);
						hm.put(key + val, temp);
					}
					
				}
				
				if (hm.containsKey(val)) {
					System.out.println(hm.get(val).toString().replace("[", "").replace("]", "").replace(",", ""));
					System.out.println(val);
					System.out.println();
					System.exit(0);
					break;
				} else {
					hm.put(val, new HashSet<>());
					hm.get(val).add(val);
				}
				
			}
			
		}
		
	}
}
