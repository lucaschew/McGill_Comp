package ProofA1;

import java.util.*;

public class Dijkstra {
	
	public static void findShortestPath(Graph g, int source) {
		
		// Create empty set
		Set<Vertex> visited = new HashSet<>();
		// Create queue
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		
		// Add source node to queue
		g.vertices.get(source).updateDistance(0);
		queue.add(g.vertices.get(source));
		
		while (!queue.isEmpty()) {
			
			// Get and remove lowest distance node from queue
			Vertex v = queue.poll();
			// Add queue to visited set
			visited.add(v);
			
			for (Edge e: g.adjList.get(v.index)) {
				
				// Relax each edge connecting to vertex
				if (g.vertices.get(e.toIndex).distance > g.vertices.get(e.fromIndex).distance + e.weight) {
					
					g.vertices.get(e.toIndex).updateDistance(g.vertices.get(e.fromIndex).distance + e.weight);
					queue.add(g.vertices.get(e.toIndex));
					
				}
				
			}
			
			
		}
		
	}
	
	public static void createGeneralCase() {
		
		HashSet<Vertex> vertices = new HashSet<>();
		vertices.add(new Vertex(1));
		vertices.add(new Vertex(2));
		vertices.add(new Vertex(3));
		vertices.add(new Vertex(4));
		
		HashSet<Edge> edges = new HashSet<>();
		edges.add(new Edge(1, 2, 3));
		edges.add(new Edge(1, 3, 5));
		edges.add(new Edge(2, 3, 1));
		edges.add(new Edge(2, 4, 6));
		edges.add(new Edge(3, 4, 1));
		
		Graph g = new Graph(vertices, edges);
		
		findShortestPath(g, 1);
		
		System.out.println("General Case (Index, Distance)");
		for (int key: g.vertices.keySet()) {
			System.out.println(g.vertices.get(key).index + ": " + g.vertices.get(key).distance);
		}
		
	}
	
	public static void createEmptyCase() {
		
		HashSet<Vertex> vertices = new HashSet<>();
		vertices.add(new Vertex(1));
		
		HashSet<Edge> edges = new HashSet<>();
		
		Graph g = new Graph(vertices, edges);
		
		findShortestPath(g, 1);
		
		System.out.println("Empty Set Edge Case (Index, Distance)");
		for (int key: g.vertices.keySet()) {
			System.out.println(g.vertices.get(key).index + ": " + g.vertices.get(key).distance);
		}
		
	}
	
	public static void createNoPathCase() {
		
		HashSet<Vertex> vertices = new HashSet<>();
		vertices.add(new Vertex(1));
		vertices.add(new Vertex(2));
		vertices.add(new Vertex(3));
		vertices.add(new Vertex(4));
		
		HashSet<Edge> edges = new HashSet<>();
		edges.add(new Edge(1, 2, 3));
		edges.add(new Edge(1, 3, 5));
		edges.add(new Edge(2, 3, 1));
		
		Graph g = new Graph(vertices, edges);
		
		findShortestPath(g, 1);
		
		System.out.println("No Path Edge Case (Index, Distance)");
		for (int key: g.vertices.keySet()) {
			System.out.println(g.vertices.get(key).index + ": " + g.vertices.get(key).distance);
		}
		
	}

	public static void main(String[] args) {
		
		createGeneralCase();
		createEmptyCase();
		createNoPathCase();

	}

}
