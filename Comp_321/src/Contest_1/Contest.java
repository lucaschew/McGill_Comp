package Contest_1;
import java.util.*;
import java.io.*;

public class Contest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q8();
	}
	
	public static void Q1() {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(), b = sc.nextInt();
		System.out.println(b);
		
		//System.out.println("hi");
	}
	
	public static void Q2() {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
		
		if (a < 1 || b < 1 || c < 1) {
			System.out.println("NO");
		} else if (d < 3) {
			System.out.println("NO");
		} else if (a + b + c < d) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
		
	}
	
	public static void Q3() {
		Scanner sc = new Scanner(System.in);
		
		String[] time1 = sc.nextLine().split(":");
		String[] time2 = sc.nextLine().split(":");
		
		int hours = (Integer.parseInt(time2[0]) - Integer.parseInt(time1[0]));
		int min = (Integer.parseInt(time2[1]) - Integer.parseInt(time1[1]));
		int sec = (Integer.parseInt(time2[2]) - Integer.parseInt(time1[2]));
		
		if (sec < 0) {
			sec += 60;
			min--;
		}
		if (min < 0) {
			min += 60;
			hours--;
		}
		if (hours < 0) {
			hours += 24;
		}
		
		if (hours == 0 && min == 0 && sec == 0) {
			hours = 24;
		}
		
		if (hours < 10) {
			System.out.print("0");
		}
		System.out.print(hours + ":");
		if (min < 10) {
			System.out.print("0");
		}
		System.out.print(min + ":");
		if (sec < 10) {
			System.out.print("0");
		}
		System.out.println(sec);
	}
	
	public static void Q4() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q5() {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		int[] track = new int[] { 1, 0, 0 };
		
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == 'A') {
				int temp = track[0];
				track[0] = track[1];
				track[1] = temp;
			}
			else if (line.charAt(i) == 'B') {
				int temp = track[1];
				track[1] = track[2];
				track[2] = temp;
			}
			else if (line.charAt(i) == 'C') {
				int temp = track[0];
				track[0] = track[2];
				track[2] = temp;
			}
		}
		
		for (int i = 0; i < 3; i++) {
			if (track[i] == 1) {
				System.out.println(i+1);
			}
		}
	}
	
	public static void Q6() {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		
		for (int i = 1; i <= a; i++) {
			System.out.println(i + " Abracadabra");
		}
	}
	
	public static void Q7() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q8() {
		Scanner sc = new Scanner(System.in);
		
		int times = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < times; i++) {
			String[] line = sc.nextLine().split(" ");
			if (line[0].equals("?")) {
				line[0] = Integer.toString(Integer.parseInt(line[1]) + Integer.parseInt(line[2]) + Integer.parseInt(line[3])); 
			}
			if (line[1].equals("?")) {
				line[1] = Integer.toString(Integer.parseInt(line[0]) - Integer.parseInt(line[2]) - Integer.parseInt(line[3])); 
			}
			if (line[2].equals("?")) {
				line[2] = Integer.toString(Integer.parseInt(line[0]) - Integer.parseInt(line[1]) + Integer.parseInt(line[3])); 
			}
			if (line[3].equals("?")) {
				line[3] = Integer.toString(Integer.parseInt(line[0]) - Integer.parseInt(line[1]) + Integer.parseInt(line[2])); 
			}
			if (line[4].equals("?")) {
				line[4] = Integer.toString((Integer.parseInt(line[1]) * 3) + Integer.parseInt(line[2])); 
			}
			
			System.out.println(line[0] + " " + line[1] + " " + line[2] + " " + line[3] + " " + line[4]);
			
		}
		
	}
	
	public static void Q9() {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		
		int total = a + b;
		int count = 0;
		while (total >= c) {
			//System.out.println(total);
			int temp = total / c;
			count += temp;
			total = (total % c) + temp;

		}
		
		System.out.println(count);
		
	}
	
	public static void Q10() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q11() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q12() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q13() {
		Scanner sc = new Scanner(System.in);
	}
	
	public static void Q14() {
		Scanner sc = new Scanner(System.in);
	}

}
