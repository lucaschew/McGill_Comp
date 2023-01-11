package A11;
import java.util.*;

public class Goldback2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int repeats = sc.nextInt();
		
		for (int i = 0; i < repeats; i++) {
			
			int val = sc.nextInt();
			LinkedList<String> li = new LinkedList<>();
			
			for (int p = 2; p <= val/2; p++) {
				
				if (isPrime(p) && isPrime(val-p)) {
					li.addLast(Integer.toString(p) + "+" + Integer.toString(val-p));
				}
				
			}
			
			System.out.println(val + " has " + li.size() + " representation(s)");
			for (String s: li) {
				System.out.println(s);
			}
			System.out.println();
			
		}
		
	}
	
	public static boolean isPrime(int n) {
		
		if(n == 2)
			return true; 
		
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			
			if (n % i == 0) 
				return false;
		}
		
		return true;
	}
	
	public static ArrayList<Integer> getPrimes(int n) {
		
	    boolean primes[] = new boolean[n + 1];
	    
	    Arrays.fill(primes, true);
	    
	    for (int p = 2; p * p <= n; p++) {
	    	
	        if (primes[p]) {
	        	
	            for (int i = p * 2; i <= n; i += p) {
	            	primes[i] = false;
	            }
	        }
	    }
	    
	    ArrayList<Integer> li = new ArrayList<>();
	    for (int i = 2; i <= n; i++) {
	        if (primes[i]) {
	            li.add(i);
	        }
	    }
	    return li;
	}

}
