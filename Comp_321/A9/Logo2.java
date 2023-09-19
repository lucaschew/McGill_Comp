package A9;
import java.io.*;

//class Coordinate {
//	
//	public double x, y;
//	public int degree;
//	
//	public Coordinate() {}
//	
//	public Coordinate(double x, double y, int degree) {
//		this.x = x;
//		this.y = y;
//		this.degree = degree;
//	}
//	
//	public Coordinate copy() {
//		return new Coordinate(this.x, this.y, this.degree);
//	}
//	
//	public void move(int distance) {
//		
////		System.out.println("pos: " + this.x + " " + this.y + " " + this.degree);
//		double rads = Math.toRadians(degree);
////		System.out.println(rads + " " + Math.cos(rads) + " " + Math.sin(rads));
//		
//		
//		this.x += distance * Math.cos(rads);
//		this.y += distance * Math.sin(rads);
//		
////		System.out.println("pos: " + this.x + " " + this.y + " " + this.degree);
//	}
//	
//	public void rotate(int amount) {
//		
//		this.degree += amount;
//		
//		if (this.degree >= 360) {
//			this.degree %= 360;
//		}
//		
//		if (this.degree < 0) {
//			this.degree += 360;
//		}
//		
//	}
//	
//	public int getDistanceFromStart() {
//		
//		// x^2 + y^2
//		return (int) Math.round(Math.hypot(this.x, this.y));
//		
//	}
//	
//	public static int getMissingDegree(Coordinate end, Coordinate missing, boolean isLeft) {
//		
//		//System.out.println(end);
//		//System.out.println(missing);
//		
//		double xDiff = end.x - missing.x;
//		double yDiff = end.y - missing.y;
//		
////		System.out.println(missing);
////		System.out.println(xDiff + " " + yDiff);
//		
//		double dotProduct = missing.x * xDiff + missing.y * yDiff;
//		double determinant = missing.x * yDiff - missing.y * xDiff;
//		
//		int angle = (int) Math.round(Math.toDegrees(Math.atan2(determinant, dotProduct)) + 180);
//		
//		//System.out.println(angle);
//		
//		angle %= 360;
//		
//		if (!isLeft) {
//			angle = (angle - 360) * -1;
//		}
//		
//		return angle % 360;
//		
//	}
//	
//	public String toString() {
//		return (this.x + " " + this.y + " " + this.degree);
//	}
//	
//}

public class Logo2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int repeats = Integer.parseInt(br.readLine());
		
		for (int r = 0; r < repeats; r++) {
			
			int commands = Integer.parseInt(br.readLine());
			
			Coordinate cord = new Coordinate(0,0,0);
			Coordinate missingLocation = new Coordinate();
			String missingCommand = "";
			
			for (int c = 0; c < commands; c++) {
				
				String[] input = br.readLine().split(" ");
				
				if (input[1].equals("?")) {
					
					missingCommand = input[0];
					missingLocation = cord.copy();
					
				} else {
					
					if (input[0].equals("fd")) {
						
						cord.move(Integer.parseInt(input[1]));
						
					} else if (input[0].equals("bk")) {
						
						cord.move(-1 * Integer.parseInt(input[1]));
						
					} else if (input[0].equals("lt")) {
						
						cord.rotate(-1 * Integer.parseInt(input[1]));
						
					} else if (input[0].equals("rt")) {
						
						cord.rotate(Integer.parseInt(input[1]));
						
					}
					
					
				}
			}
			
			if (missingCommand.equals("fd") || missingCommand.equals("bk")) {
				
				System.out.println(cord.getDistanceFromStart());
				
			} else if (missingCommand.equals("lt")) {
				
				System.out.println(Coordinate.getMissingDegree(cord, missingLocation, true));
				
			} else {
				
				System.out.println(Coordinate.getMissingDegree(cord, missingLocation, false));
				
			}
			
		}

	}

}
