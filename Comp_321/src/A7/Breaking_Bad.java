package A7;
import java.util.*;
import java.io.*;

public class Breaking_Bad {
    
    private static HashSet<String> arr1 = new HashSet<>();
    private static HashSet<String> arr2 = new HashSet<>();
    private static HashMap<String, HashSet<String>> hm;
    private static boolean result = true;
    private static HashMap<String, Boolean> visited;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int nMats = Integer.parseInt(br.readLine());
        String[] mats = new String[nMats];
        visited = new HashMap<>();
        hm = new HashMap<>();
        
        for (int i = 0; i < nMats; i++) {
            mats[i] = br.readLine();
            visited.put(mats[i], false);
            hm.put(mats[i], new HashSet<>());
        }
        
        int nComb = Integer.parseInt(br.readLine());
        for (int i = 0; i < nComb; i++) {
            String[] comb = br.readLine().split(" ");
            hm.get(comb[0]).add(comb[1]);
            hm.get(comb[1]).add(comb[0]);
        }
        
        //System.out.println(hm);
        for (String s: mats) 
            if (!visited.get(s))
                DFS(s);
        
        
        if (result) {
            System.out.println(arr1.toString().replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(arr2.toString().replace("[", "").replace("]", "").replace(",", ""));
        } else {
            System.out.println("impossible");
        }
        
    }
    
    private static void DFS(String mat) {
        
        if (!arr1.contains(mat) && !arr2.contains(mat)) {
            arr1.add(mat);
        }
        
        for (String banned: hm.get(mat)) {
            
            if (arr1.contains(mat) && arr1.contains(banned)) {
                result = false;
            } else if (arr2.contains(mat) && arr2.contains(banned)) {
                result = false;
            } else if (arr1.contains(mat) && !arr1.contains(banned) && !arr2.contains(banned)) {
                arr2.add(banned);
                if (!visited.get(mat))
                    DFS(banned);
            } else if (arr2.contains(mat) && !arr2.contains(banned) && !arr1.contains(banned)) {
                arr1.add(banned);
                if (!visited.get(mat))
                    DFS(banned);
            }
            
        }
        
        visited.put(mat, true);
        
    }

}
