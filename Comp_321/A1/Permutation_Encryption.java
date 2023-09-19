package A1;
import java.util.*;

public class Permutation_Encryption {
	
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int keyLength = sc.nextInt();
        
        while (keyLength != 0) {
        	int[] keys = new int[keyLength];
        	
        	for (int i = 0; i < keyLength; i++) keys[i] = sc.nextInt();
        	sc.nextLine();
        	String words = sc.nextLine();
        	
        	for (int i = words.length(); words.length() % keyLength != 0; i++) {
        		words += " ";
        	}
        	
        	String newString = "'";
        	for (int i = 0; i < words.length(); i++)
        	{
        		newString += words.charAt(((keys[i % keyLength] - 1) + (int) (keyLength * (Math.floor(i / keyLength)))));
        	}
        	
        	newString += "'";
        	System.out.println(newString);
        	
        	keyLength = sc.nextInt();
        }
        
    }

}
