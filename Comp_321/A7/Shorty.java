package A7;
import java.util.*;

class Path {
	public int toPath;
	public double weight;
	
	public Path(int p, double w) {
		this.toPath = p;
		this.weight = w;
	}
}

public class Shorty {
	
	static HashMap<Integer, List<Path>> hm;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(), m = sc.nextInt();

		
		while (n != 0 && m != 0) {
			hm = new HashMap<>();
			double[] weight = new double[n];
			weight[0] = 1;
			
			for (int i = 0; i < m; i++) {
				int a = sc.nextInt(), b = sc.nextInt();
				double c = sc.nextDouble();
				
				if (!hm.containsKey(a)) {
					hm.put(a, new ArrayList<>());
				}
				if (!hm.containsKey(b)) {
					hm.put(b, new ArrayList<>());
				}
				
				hm.get(a).add(new Path(b, c));
				hm.get(b).add(new Path(a, c));
				
			}
			
			Queue<Integer> q = new LinkedList<>();
			q.add(0);
			
			while (!q.isEmpty()) {
				int current = q.poll();
				
				for (Path p: hm.get(current)) {
					if (weight[current] * p.weight > weight[p.toPath]) {
						weight[p.toPath] = weight[current] * p.weight;
						q.add(p.toPath);
					}
				}
				
			}
			
			System.out.println(String.format("%.4f", weight[n-1]));
			
			n = sc.nextInt(); 
			m = sc.nextInt();
		}

	}

}
