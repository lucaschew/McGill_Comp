// Created by Itai Zilberstein for COMP 251 Winter 2022

public class Vertex {
    int d;
    Vertex pi;
    Vertex() {
        // vertex initialization to be used with Bellman-Ford
        this.d = Integer.MAX_VALUE;
        this.pi = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex v = (Vertex) o;
            return this == v;
        }
        return false; 
    }    
}
