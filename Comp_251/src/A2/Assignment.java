package A2;

import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		
		// Compare by weight desc, then by deadline asc
		if (a2.weight != a1.weight) {
			return a2.weight - a1.weight;
		} else {
			return a1.deadline - a2.deadline;
		}
	}
}
