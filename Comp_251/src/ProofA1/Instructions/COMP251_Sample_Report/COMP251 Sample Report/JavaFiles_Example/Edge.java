// Created by Itai Zilberstein for COMP 251 Winter 2022

public class Edge {
    // represents an edge from u to v
    Vertex u;
    Vertex v;
    Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge e = (Edge) o;
            return this.u.equals(e.u) && this.v.equals(e.v);
        }
        return false; 
    } 
}


