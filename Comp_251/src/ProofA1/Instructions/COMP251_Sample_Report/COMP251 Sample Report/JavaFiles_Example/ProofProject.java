// Created by Itai Zilberstein for COMP 251 Winter 2022

import java.io.IOException;
import java.util.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class ProofProject {

    // relax method:
    // updates the shortest path to a vertex 
    // if taking edge e results in a better path
    public static void relax(Edge e, EdgeFunction w) {
        int wuv = w.apply(e);
        if (e.v.d > e.u.d + wuv) {
            e.v.d = e.u.d + wuv;
            e.v.pi = e.u;
        }
    }

    // Bellman-Ford algorithm:
    public static boolean bellmanFord(Graph g, EdgeFunction w, Vertex s) {
        // initialize single source, set distance from s to s as 0
        s.d = 0;

        // main for loop:
        // repeatedly relax edges, updating their shortest distance
        for(int i=1; i<g.vertexSet.size(); i++) {
            for (Edge e : g.edgeSet) {
                relax(e,w);
            }
        }
        for (Edge e : g.edgeSet) {
            // check if negative cycle found
            if (e.v.d > e.u.d + w.apply(e)) {
                return false;
            } 
        }
        return true;
    }

    // THE FOLLOWING ARE HELPER METHODS TO GENERATE RANDOM INSTANCES OF THE PROBLEM: 

    // randomly generates a new edge not already in the set of edges
    public static Edge generateRandomEdge(ArrayList<Vertex> vertexList, Set<Edge> edgeSet) {
        Random rand = new Random();
        // pick two random vertices
        int i = rand.nextInt(vertexList.size());
        int j = rand.nextInt(vertexList.size());
        // make sure vertices are distinct
        while (i == j) {j = rand.nextInt(vertexList.size());}
        // try to generate a new edge between them
        Edge e = new Edge(vertexList.get(i), vertexList.get(j));
        if (edgeSet.contains(e)) {
            // generate the first edge we can iteratively
            for (int u=0; u<vertexList.size()-1; u++) {
                for (int v=i+1; v<vertexList.size(); v++) {
                    e = new Edge(vertexList.get(u), vertexList.get(v));
                    if (!edgeSet.contains(e)) {
                        return e;
                    }
                }
            }
        }
        return e;
    }

    // generates a random graph with the respective number of vertices and edges
    public static Graph generateRandomGraph(int nVertices, int nEdges) {
        ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
        Set<Edge> edgeSet =  new HashSet<Edge>();
        // init the vertices 
        for (int i=0; i<nVertices; i++){
            Vertex v = new Vertex();
            vertexList.add(v);
        }
        // create a random path of length n-1
        Collections.shuffle(vertexList);
        for (int i=0; i< nVertices-1; i++) {
            edgeSet.add(new Edge(vertexList.get(i), vertexList.get(i+1)));
        }
        // randomly add the rest of the edges
        for (int k=(nEdges - nVertices - 1); k>0; k--) {
            Edge e = generateRandomEdge(vertexList, edgeSet);
            edgeSet.add(e);
        }
        return new Graph(new HashSet<Vertex>(vertexList), edgeSet);
    }

    // generates a random nonzero integer in range (-100, 100)
    public static Integer generateRandomWeight() {
        Random rand = new Random();
        Integer i = rand.nextInt(100) + 1;
        Integer sign = rand.nextInt(2);
        if (sign == 1) {
            i *= -1;
        }
        return i;
    }

    // generates a random weight function
    public static EdgeFunction generateRandomFunction(Set<Edge> edgeSet) {
        HashMap<Edge, Integer> weights = new HashMap<Edge, Integer>();
        for (Edge e : edgeSet) {
            // generates a random weight for each edge
            Integer weight = generateRandomWeight(); 
            weights.put(e, weight);
        }
        return new EdgeFunction(weights);
    }

    // runs the Bellman-Ford algoritm on a random graph
    // returns the time of execution in microseconds
    public static double runBellmanFord(int nVertices, int nEdges) {
        Graph g = generateRandomGraph(nVertices, nEdges); 
        Vertex s = (Vertex) g.vertexSet.toArray()[0];
        EdgeFunction w = generateRandomFunction(g.edgeSet);
        
        double start = System.nanoTime();
        bellmanFord(g, w, s);
        double end = System.nanoTime();
        
        // execution time in microseconds
        double duration = (end - start) / 1000;  
        return duration;
    }


    // runs the Bellman-Ford algorithm on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {
        // number of sample executions
        int samples = 100;
        double[] ns = new double[samples];
        double[] execution_times = new double[samples];
        int n = 10;
        for (int i=0; i<samples; i++) {
            // run bellman ford on a random graph with n vertices and 2n edges
            execution_times[i] = runBellmanFord(n, 2*n);
            ns[i] = n;
            n += 10;
        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of Bellman-Ford", "Number of Vertices", "Execution Time (us)", "Bellman-Ford Runtime", ns, execution_times);
        double[] n2s = new double[samples];
        // add reference quadratic
        for (int i=0; i<samples; i++) {
            n2s[i] = (Math.pow(ns[i], 2)/25 + 500);
        }
        chart.addSeries("n^2 / 25 + 500", ns, n2s).setMarker(SeriesMarkers.NONE);;
        // display chart
        //new SwingWrapper<>(chart).displayChart();
        
        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
