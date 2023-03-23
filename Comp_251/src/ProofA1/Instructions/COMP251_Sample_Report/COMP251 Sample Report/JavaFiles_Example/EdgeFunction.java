// Created by Itai Zilberstein for COMP 251 Winter 2022

import java.util.HashMap;
import java.util.function.Function;

public class EdgeFunction implements Function<Edge, Integer> {
    HashMap<Edge, Integer> weights;
    
    public EdgeFunction(HashMap<Edge, Integer> weights) {
        this.weights = weights;
    }
    public Integer apply(Edge e) {
        return weights.get(e);
    }
}
