package A3;
//Lucas Chew
//260971542
//No Collaborators
import java.util.*;

// Pair class with built in weight, so I don't need to make my visited a int[][]
class Pair {
	
	int x, y, weight;
	
	public Pair(int a, int b, int weight) {
		this.x = a;
		this.y = b;
		this.weight = weight;
	}
	
	public String toString() {
		return x + " " + y + " " + weight;
	}
	
}


public class A3Q1 {
	
	public static int find_exit(int time, String[][] board) {
		
		// Dimensions of Array
		int x = board[0].length;
		int y = board.length;
		
		// Visited list
		boolean[][] visited = new boolean[y][x];
		
		// Queue
		Queue<Pair> queue = new LinkedList<>();
		
		// Find starting point and add to Queue
		for (int i = 0; i < y && queue.size() == 0; i++) {
			for (int z = 0; z < x; z++) {
				
				if (board[i][z].equals("S")) {
					queue.add(new Pair(z, i, 0));
					break;
				}
				
			}
		}
		
		
		// While queue isn't empty
		while (!queue.isEmpty()) {
			
			// Get first pair from queue
			Pair p = queue.poll();
			
			//System.out.println(p);
			
			// If I already visited, ignore
			if (visited[p.y][p.x])
				continue;
			
			// Set visited to true
			visited[p.y][p.x] = true; 
			
			// If the weight is greater than time, then all proceeding ones are greater than time
			if (p.weight > time)
				return -1;
			
			// If it is touching border, return weight
			if (p.x == 0 || p.x == x-1 || p.y == 0 || p.y == y-1) {
				return p.weight;
			}
			
			// If the neighbouring pieces are touching a 0 or a special, check if it can, and add it queue
			if (board[p.y+1][p.x].equals("0") || board[p.y+1][p.x].equals("U")) {
				queue.add(new Pair(p.x, p.y+1, p.weight+1));
			}
			
			if (board[p.y-1][p.x].equals("0") || board[p.y-1][p.x].equals("D")) {
				queue.add(new Pair(p.x, p.y-1, p.weight+1));
			}
			
			if (board[p.y][p.x-1].equals("0") || board[p.y][p.x-1].equals("R")) {
				queue.add(new Pair(p.x-1, p.y, p.weight+1));
			}
			
			if (board[p.y][p.x+1].equals("0") || board[p.y][p.x+1].equals("L")) {
				queue.add(new Pair(p.x+1, p.y, p.weight+1));
			}
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		String[][] arr = new String[][] 
			{{"1", "1", "1", "1"},
			{"1", "S", "0", "1"},
			{"1", "0", "1", "1"},
			{"0", "L", "1", "1"},
		};
		
		int result = find_exit(3, arr);
		
		System.out.println(result);
	}
}
