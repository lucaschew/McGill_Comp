package A6;
import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class Internet {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
		
		String[] nums = br.readLine().split(" ");
		int numOfHouses = Integer.parseInt(nums[0]);
		int numOfConnections = Integer.parseInt(nums[1]);
		
		HashSet<Integer> visited = new HashSet<Integer>();
		
		for (int i = 0; i < numOfConnections; i++) {
			String[] input = br.readLine().split(" ");
			int temp = Integer.parseInt(input[0]);
			int value = Integer.parseInt(input[1]);
			
			if (!hm.containsKey(temp)) {
				hm.put(temp, new HashSet<>());
			}
			if (!hm.containsKey(value)) {
				hm.put(value, new HashSet<>());
			}
			
			hm.get(temp).add(value);
			hm.get(value).add(temp);
		}
		
		//System.out.println(hm);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		
		
		while (!q.isEmpty()) {
			//System.out.println(q);
			int num = q.poll();
			
			visited.add(num);
			
			if (hm.containsKey(num))
				for (int temp: hm.get(num))
					if (!visited.contains(temp))
						q.add(temp);
			
		}
		
		if (visited.size() == numOfHouses) {
			System.out.println("Connected");
		} else {
			for (int i = 1; i <= numOfHouses; i++) {
				if (!visited.contains(i)) {
					System.out.println(i);
				}
			}
		}
	}	

}
