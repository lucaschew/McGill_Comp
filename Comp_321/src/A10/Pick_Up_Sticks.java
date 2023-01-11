package A10;

import java.util.*;
import java.io.*;

public class Pick_Up_Sticks  {
	
	static boolean[] visited;
	static boolean[] loop;
	static HashMap<Integer, HashSet<Integer>> hm;
	static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line = br.readLine().split(" ");
        
        int numSticks = Integer.parseInt(line[0]);
        int numCon = Integer.parseInt(line[1]);
        
        visited = new boolean[numSticks];
        loop = new boolean[numSticks];
        hm = new HashMap<>();
        result = new ArrayList<>(numSticks*2);
        
        int a;
        int b;
        
        for (int i = 0; i < numCon; i++) {
        	
        	line = br.readLine().split(" ");
            
            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);
            
            if (!hm.containsKey(a)) {
                hm.put(a, new HashSet<>());
            }
            
            hm.get(a).add(b);
        }
        
        for (int i = 0; i < numSticks; i++) {
        	
        	if (!visited[i]) {
        		DFS(i+1);
        	}
        }

        Collections.reverse(result);
        System.out.println(result.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "\n"));
        

        
    }
    
    private static void DFS(int num) {
    	
    	if (visited[num-1] && !loop[num-1]) {
    		return;
    	}
    	
		visited[num-1] = true;
    	
    	if (visited[num-1] && loop[num-1]) {
    		
			System.out.println("IMPOSSIBLE");
			System.exit(0);
		}
    	
		if (hm.containsKey(num)) {
			
			loop[num-1] = true;
			
			for (int val : hm.get(num)) {
				DFS(val);
			}
		}
		
		loop[num-1] = false;

		
		result.add(num);
    }
    
}
