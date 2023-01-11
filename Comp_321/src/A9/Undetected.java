package A9;
import java.util.*;
import java.io.*;

class Circle {
	public int x, y, rad;
	
	public Circle(int x, int y, int rad) {
		this.x = x;
		this.y = y;
		this.rad = rad;
	}
	
	public String toString() {
		return (x + " " + y + " " + rad);
	}
	
}

class Point {
	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return (x + " " + y);
	}
}

public class Undetected {
	
	/*
	 * DFS with 2d Array
	 * Array would contain the boundaries of the circle ceilinged
	 * start from y = 0, x = 0 -> 300
	 * Check if intersection[value] = true
	 * push off from point
	 * if point can get to y = 300
	 * dfs with the next sensor in a linear list
	 */

	private static int maxTrackers = 0;
	private static int numCircle;
	private static Circle[] arrCircle;
	private static boolean reached = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		numCircle = sc.nextInt();
		arrCircle = new Circle[numCircle];
		
		for (int i = 0; i < numCircle; i++) {
			arrCircle[i] = new Circle(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		
		for (Circle c: arrCircle) {
			System.out.println(c);
		}
		
		DFS(0, new boolean[numCircle], 1);
		
		System.out.println(maxTrackers);
	}
	
	private static void DFS(int newCircle, boolean[] currentLi, int currentTrackerCount) {
		
		currentLi[newCircle] = true;
		
		reached = false;
		int[][] visited = new int[300][200];

		for (int i = 0; i < 200; i++) {
			if (visited[0][i] == 0) {
				floodFill(currentLi, visited, 0, i);
				if (reached) {
					break;
				}
			}
		}
		
		maxTrackers = Math.max(maxTrackers, currentTrackerCount);
		
		for (int i = newCircle+1; i < numCircle; i++) {
			boolean[] newLi = null;
			System.arraycopy(currentLi, 0, newLi, 0, currentLi.length);
			DFS(i, newLi, currentTrackerCount + 1);
		}
		
	}
	
	private static boolean checkBoundaries(int x, int y, boolean[] currentCircleLi) {
		
		//d = sqrt( (x1 - x2)^2 + (y1 - y2)^2 )
		
		for (int i = 0; i < currentCircleLi.length; i++) {
			if (currentCircleLi[i]) {
				
				int val = (int) Math.ceil( Math.sqrt(  Math.pow(x - arrCircle[i].x, 2) + Math.pow(y - arrCircle[i].y, 2) ) );
				if (val <= arrCircle[i].rad) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	private static void floodFill(boolean[] currentCircleLi, int[][] visited, int x, int y) {
		
		if (reached == true) {
			return;
		}
		
		if (checkBoundaries(x, y, currentCircleLi)) {
			
			if (y == 299) {
				reached = true;
				return;
			}
			
			visited[y][x] = 1;
			//System.out.println(x + " " + y);
			try {
			if (y > 0 && visited[y-1][x] == 0) {
				floodFill(currentCircleLi, visited, x, y-1);
			}
			if (visited[y+1][x] == 0) {
				floodFill(currentCircleLi, visited, x, y+1);
			}
			if (x > 0 && visited[y][x-1] == 0) {
				floodFill(currentCircleLi, visited, x-1, y);
			}
			if (x < 199 && visited[y][x+1] == 0) {
				floodFill(currentCircleLi, visited, x+1, y);
			}
			} catch (Exception e) {
				System.out.println();
				System.exit(0);
			}
			
		} else {
			
			visited[y][x] = -1;
			
		}

		
	}

}
