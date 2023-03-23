package A3;

import java.util.*;

class Instruction {
	
	int piece;
	int numberOfPieces;
	int pieceBuilt;
	
	public Instruction(int piece, int num, int built) {
		this.piece = piece;
		this.numberOfPieces = num;
		this.pieceBuilt = built;
	}
	
	public String toString() {
		return "It takes " + numberOfPieces + " of piece " + piece + " to build " + pieceBuilt;
	}
	
}

public class A3Q2 {

	public static long[] num_pieces(long[] pieces, int[][] instructions) {

		HashMap<Integer, List<Instruction>> hm = new HashMap<>();
		boolean[] dependent = new boolean[pieces.length];
		int numOfDep = 0;
		
		for (int[] ins: instructions) {
			if (!hm.containsKey(ins[1])) {
				hm.put(ins[1], new ArrayList<>());
				dependent[ins[1]] = true;
				numOfDep++;
			}
			
			hm.get(ins[1]).add(new Instruction(ins[0], ins[2], ins[1]));
		}
		
		// for (int key: hm.keySet())
		// 	for (Instruction i: hm.get(key))
		// 		System.out.println(i);
		
		while (numOfDep > 0)
		
			for (int i = pieces.length-1; i >= 0; i--) {
				
				boolean skip = false;
				
				if (!hm.containsKey(i))
					continue;
				
				//System.out.println(i);
				
				for (Instruction ins: hm.get(i)) {
					
					if (dependent[ins.piece]) {
						skip = true;
						break;
					}
					
				}
				
				if (skip)
					continue;
				
				for (Instruction ins: hm.get(i)) {
					
					pieces[ins.piece] += pieces[ins.pieceBuilt] * ins.numberOfPieces;
					//System.out.println("add");
				}
				
				dependent[i] = false;
				numOfDep--;
				
			}
		
		return pieces;
	}
	
	 public static void main(String[] args) {
		
	 	long[] pieces = new long[] {0, 0, 0, 0, 3};
		
	 	int[][] ins = new int[][] 
	 			{
	 				{0, 1, 3},
	 				{1, 4, 1},
	 				{2, 4, 1},
	 				{3, 4, 2},
	 			};
				
	 	pieces = num_pieces(pieces, ins);
		
	 	for (long l: pieces) {
	 		System.out.print(l + " ");
	 	}
	 	System.out.println();
		
	 }

}
