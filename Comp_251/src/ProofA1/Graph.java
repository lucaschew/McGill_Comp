package ProofA1;

import java.util.*;

public class Graph {
	
	public HashMap<Integer, Vertex> vertices;
	public HashMap<Integer, List<Edge>> adjList;
	public Set<Edge> edges;
	
	public Graph(Set<Vertex> vertices, Set<Edge> edges ) {
		
		this.vertices = new HashMap<>();
		adjList = new HashMap<>();
		this.edges = edges;
		
		for (Vertex v: vertices) {
			
			this.vertices.put(v.index, v);
			adjList.put(v.index, new ArrayList<>());
			
		}
		
		for (Edge e: this.edges) {

			adjList.get(e.fromIndex).add(e);
			
		}
		
	}

}
