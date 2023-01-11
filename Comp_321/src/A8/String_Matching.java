package A8;
import java.util.*;
import java.io.*;

public class String_Matching {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String pattern;
		
		while ((pattern = br.readLine()) != null) {
			
			String text = br.readLine();
			int arr[] = new int[pattern.length()];
			ArrayList<Integer> result = new ArrayList<>();
			
			buildLPS(arr, pattern);
			
			int patternPointer = 0, textPointer = 0;
			
			while ((text.length() - textPointer) >= (pattern.length() - patternPointer)) {
				
				if (text.charAt(textPointer) == pattern.charAt(patternPointer)) {
					textPointer++;
					patternPointer++;
				} else if (textPointer < text.length() && text.charAt(textPointer) != pattern.charAt(patternPointer)) {
					
					if (patternPointer != 0) {
						patternPointer = arr[patternPointer - 1];
					} else {
						textPointer++;
					}
					
				}
				
				if (patternPointer == pattern.length()) {
					result.add(textPointer - patternPointer);
					patternPointer = arr[patternPointer -1];
				} 
				
				//System.out.println(patternPointer + " " + textPointer + " " + result);
				
			}
			
//			System.out.println(pattern + " " + text);
			System.out.println(result.toString().replace("[", "").replace("]", "").replace(",", ""));
//			System.exit(0);
		}
	}
	
	private static void buildLPS(int[] arr, String pattern) {
		
		int len = 0;
		
		for (int i = 1; i < pattern.length(); ) {
			
			if (pattern.charAt(i) == pattern.charAt(len)) {
				
				len++;
				arr[i] = len;
				i++;
				
			} else {
				
				if (len != 0) {
					len = arr[len - 1];
				} else {
					arr[i] = 0;
					i++;
				}
				
			}

			
		}
		
//		for (int a: arr) {
//			System.out.println(a);
//		}
	}

}
