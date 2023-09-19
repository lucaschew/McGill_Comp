package A7;
import java.util.*;
import java.io.*;

public class Horror {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int numHouses = sc.nextInt();
		int numHorrorList = sc.nextInt();
		int numComp = sc.nextInt();
		
		int[] value = new int[numHouses];
		Arrays.fill(value, Integer.MAX_VALUE);
		HashMap<Integer, ArrayList<Integer>> con = new HashMap<>();
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < numHorrorList; i++) {
			int t = sc.nextInt();
			value[t] = 0;
			q.add(t);
		}
		
		for (int i = 0; i < numComp; i++) {
			
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if (!con.containsKey(a)) {
				con.put(a, new ArrayList<>());
			}
			if (!con.containsKey(b)) {
				con.put(b, new ArrayList<>());
			}
			
			con.get(a).add(b);
			con.get(b).add(a);
		}
		

		while (!q.isEmpty()) {
			int v = q.poll();
			
			if (con.containsKey(v)) {
				for (int ele: con.get(v)) {
					if (value[v] + 1 < value[ele]) {
						value[ele] = value[v] + 1;
						q.add(ele);
					}
				}
			}
		}
		
		int index = 0;
		
		for (int i = 1; i < numHouses; i++) {
			if (value[index] < value[i]) {
				index = i;
			}
		}
		
		System.out.println(index);
		
		
	}

}
