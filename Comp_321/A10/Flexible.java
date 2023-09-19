package A10;
import java.util.*;
import java.io.*;

public class Flexible {

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        TreeSet<Integer> ts = new TreeSet<Integer>();
        
        int num = sc.nextInt();
        int numDiv = sc.nextInt();
        sc.nextLine();
        String[] l = sc.nextLine().split(" ");
        
        int[] spaces = new int[numDiv+1];
        spaces[numDiv] = num;
        
        for (int i = 0; i < l.length; i++) {
            spaces[i] = Integer.parseInt(l[i]);
        }
        
        Arrays.sort(spaces);
        
        for (int i = 0; i < spaces.length; i++) {
            
            ts.add(spaces[i]);
            
            for (int k = i+1; k < spaces.length; k++) {
                
                ts.add(spaces[k] - spaces[i]);
                
            }
            
        }
        
        System.out.println(ts.toString().replace("[", "").replace("]", "").replace(",", ""));
        
    }

}