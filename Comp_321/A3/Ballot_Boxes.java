package A3;
import java.util.*;
import java.io.*;

class Ballots implements Comparable<Ballots> {
	
	public int population, boxes, maxValue;
	
	public Ballots(int population, int boxes) {
		this.population = population;
		this.boxes = boxes;
		this.maxValue = (int) Math.ceil(population/ (double) boxes);
	}

	@Override
	public int compareTo(Ballots o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.maxValue, o.maxValue);
	}
	
}

public class Ballot_Boxes {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int cities = Integer.parseInt(input[0]), boxes = Integer.parseInt(input[1]);
		
		while (cities != -1 && boxes != -1) {
			PriorityQueue<Ballots> queue = new PriorityQueue<>(Comparator.reverseOrder());

			for (int i = 0; i < cities; i++) {
				String numStr = br.readLine();
				int amount = Integer.parseInt(numStr);
				queue.add(new Ballots(amount, 1));
			}
			
			for (int i = cities; i < boxes; i++) {
				Ballots b = queue.poll();
				queue.add(new Ballots(b.population, b.boxes+1));
			}
			
//			for (Ballots x: queue)
//				System.out.println(x.population + " " + x.boxes + " " + x.maxValue);
			System.out.println(queue.poll().maxValue);
			
			br.readLine();
			input = br.readLine().split(" ");
				
			cities = Integer.parseInt(input[0]);
			boxes = Integer.parseInt(input[1]);
		}
		

	}

}
