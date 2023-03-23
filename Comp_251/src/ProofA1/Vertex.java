package ProofA1;

public class Vertex implements Comparable<Vertex> {
	
	public int index;
	public int distance;
	
	public Vertex(int index) {
		this.index = index;
		this.distance = Integer.MAX_VALUE;
	}
	
	public void updateDistance(int distance) {
		this.distance = distance;
	}
	
	public int compareTo(Vertex v) {	
		return v.distance - this.distance;
	}

}
