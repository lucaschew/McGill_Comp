package ProofA1;

public class Node {
	
	public int value;
	public Node left;
	public Node right;
	public int balance;
	
	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
		balance = 0;
	}
	
	public Node(int value, Node parent) {
		this.value = value;
		this.left = null;
		this.right = null;
		balance = 0;
	}
	
	// Get the balance of a current node
	// If null, return -1, else return balance
	public static int getBalance(Node n) {

		if (n != null) {
			return n.balance;
		} else {
			return -1;
		}
		
	}
	
	// Update balance/height of the node
	public int updateBalance() {
		
		this.balance = Math.max(getBalance(this.right), getBalance(this.left)) + 1;
		
		return this.balance;
	}
	
}
