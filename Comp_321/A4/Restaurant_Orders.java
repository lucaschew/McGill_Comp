package A4;
import java.util.*;

public class Restaurant_Orders {
	
	public static int[] items;
	public static int highestOrder = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int numOfItems = sc.nextInt();
		
		items = new int[numOfItems];
		for (int i = 0; i < numOfItems; i++) {
			items[i] = sc.nextInt();
		}
		
		int numOfOrders = sc.nextInt();
		
		int[] orderArr = new int[numOfOrders];
		for (int i = 0; i < numOfOrders; i++) {
			orderArr[i] = sc.nextInt();
			if (orderArr[i] > highestOrder) {
				highestOrder = orderArr[i];
			}
		}
		
        int[] counter = new int[highestOrder + 1];
        int[] pathways = new int[highestOrder + 1];
    	int index = 0;
        
        for (int itemPrice: items) {
        	for (int i = itemPrice; i < counter.length; i++) {
        		if (counter[i - itemPrice] > 0) {
        			counter[i]++;
        			
        			// If there are multiple ways to reach the value before, add to counter
        			if (counter[i - itemPrice] > 1) {
        				counter[i]++;
        			}
        			
        			// If new, add to pathway
        			if (counter[i] == 1) {
        				pathways[i] = index;
        			}
        		} else if (i % itemPrice == 0) {
        			// Add to counter if mod is 0 (aka, is equal to the number)
            			counter[i]++;
            			pathways[i] = index;
            		}
        	}
        	index++;
        }
		
//        for (int a: pathways) {
//        	System.out.print(a + " ");
//        }
//        System.out.println();
//        
//        for (int a: counter) {
//        	System.out.print(a + " ");
//        }
//        System.out.println();
        
		for (int order: orderArr) {
			if (counter[order] == 1) {
				
				ArrayList<Integer> li = new ArrayList<>();
				
                while(order>0){
                    li.add(pathways[order]);
                    order-=items[pathways[order]];
                }
                
                Collections.sort(li);
                for (int i = 0; i < li.size(); i++) {
                	if (i != 0) {
                		System.out.print(" ");
                	}
                	System.out.print(li.get(i) + 1);
                }
                
                System.out.println();
                
			} else if (counter[order] > 1) {
				System.out.println("Ambiguous");
			} else { 
				System.out.println("Impossible");
			}
		}
		
	}
}
