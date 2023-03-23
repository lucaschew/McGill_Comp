// Created by Itai Zilberstein for COMP 251 Winter 2022

import java.util.Set;

public class Matroid {
    
	Set<Integer> s;
	Set<Set<Integer>> i;
	
	// class constructor
	public Matroid(Set<Integer> s, Set<Set<Integer>> i) {
		this.s = s;
		this.i = i;	
	}	
}
