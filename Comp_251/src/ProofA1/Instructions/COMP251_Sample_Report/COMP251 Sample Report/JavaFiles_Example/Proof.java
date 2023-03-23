// Created by Itai Zilberstein for COMP 251 Winter 2022

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Proof {

    public static Set<Integer> findMaxWeightIndependentSet(Matroid m, Function<Integer, Integer> w) {
        // a will be our final independent set
		Set<Integer> a = new HashSet<Integer>();
		// copy the set S of M into an array list
		ArrayList<Integer> s = new ArrayList<Integer>();
        s.addAll(m.s);
        Comparator<Integer> compareByWeight = 
            (Integer i, Integer j) ->  w.apply(i).compareTo(w.apply(j));
		Collections.sort(s, compareByWeight.reversed());
		// loop through M.get_S() in decreasing order and add elements to A when appropriate
		for (Integer x : s) {
            Set<Integer> a_union = new HashSet<Integer>();
            a_union.addAll(a);
            a_union.add(x);
            // this line uses the proof (property 3 of matroids in the textbook)
            if (m.i.contains(a_union)) { 
                a = a_union;
            }
        }
        return a;
	}

    // returns a set with the inputted list of integers
    public static Set<Integer> makeSet(int[] val) {
        Set<Integer> s = new HashSet<>();
        for (int i : val) {s.add(i);};
        return s;
    }

    // general case Matroid with S = {1,2,3}
    // I = {{1}, {2}, {3}, {1,2}, {2,3}, {1,3}}
    public static Matroid createGeneralCase() {
        Set<Integer> s = makeSet(new int[] {1,2,3});
        Set<Set<Integer>> i = new HashSet<>();
        i.add(makeSet(new int[] {1}));
        i.add(makeSet(new int[] {2}));
        i.add(makeSet(new int[] {3}));
        i.add(makeSet(new int[] {1,2}));
        i.add(makeSet(new int[] {1,3}));
        i.add(makeSet(new int[] {2,3}));
        return new Matroid(s, i);
    }
    // edge case Matroid with S = {1,2,3}
    // I = {}
    public static Matroid createEmptyCase() {
        Set<Integer> s = makeSet(new int[] {1,2,3});
        Set<Set<Integer>> i = new HashSet<>();
        return new Matroid(s, i);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("GENERAL CASE");
        // create general test case with weight function = Id
		Matroid m1 = createGeneralCase();
        Function<Integer, Integer> w1 = (x) -> x;
        // output results
        Set<Integer> a1 = findMaxWeightIndependentSet(m1, w1);
        System.out.println(String.format("M = %s,%s", m1.s, m1.i));
        System.out.println(String.format("w(x) = x"));
        System.out.println(String.format("A = %s", a1));
        

        System.out.println("EDGE CASE : EMPTY SET");
        // create edge test case with weight function = Id
		Matroid m2 = createEmptyCase();
        Function<Integer, Integer> w2 = (x) -> x;
        Set<Integer> a2 = findMaxWeightIndependentSet(m2, w2);
        System.out.println(String.format("M = %s,%s", m2.s, m2.i));
        System.out.println(String.format("w(x) = x"));
        System.out.println(String.format("A = %s", a2));

        System.out.println("EDGE CASE : MULTIPLE MAXIMAL SETS");
        // create edge test case with weight function = Id
		Matroid m3 = createGeneralCase();
        Function<Integer, Integer> w3 = (x) -> 1;
        Set<Integer> a3 = findMaxWeightIndependentSet(m3, w3);
        System.out.println(String.format("M = %s,%s", m3.s, m3.i));
        System.out.println(String.format("w(x) = 1"));
        System.out.println(String.format("A = %s", a3));
    }
}
