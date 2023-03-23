// Created by Itai Zilberstein for COMP 251 Winter 2022

import java.util.Set;

public class Graph {
    Set<Vertex> vertexSet;
    Set<Edge> edgeSet;
    Graph(Set<Vertex> vertexSet, Set<Edge> edgeSet) {
        this.vertexSet = vertexSet;
        this.edgeSet = edgeSet;
    }
}
