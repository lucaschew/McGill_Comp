package assignment2;


public class SolitaireCipher {
	public Deck key;
	
	public SolitaireCipher (Deck key) {
		this.key = new Deck(key); // deep copy of the deck
	}
	
	
	public int[] getKeystream(int size) {
		
		int[] keyStorage = new int[size];
		
		for (int i = 0; i < size; i++) {
			keyStorage[i] = key.generateNextKeystreamValue();
			//System.out.println(keyStorage[i]);
		}
		
		return keyStorage;
	}
		
	/* 
	 * TODO: Encodes the input message using the algorithm described in the pdf.
	 */
	public String encode(String msg) {
		
		msg = msg.replaceAll("[^A-Za-z]", "").toUpperCase();
		
		int[] keyStream = getKeystream(msg.length());
		
		String newMessage = "";
		
		for (int i = 0; i < msg.length(); i++) {
			newMessage += (char) (((msg.charAt(i)-65+keyStream[i])%26)+65);
		}
		
		return newMessage;
		
	}
	
	/* 
	 * TODO: Decodes the input message using the algorithm described in the pdf.
	 */
	public String decode(String msg) {
		
		//Assuming that Input is valid (aka, only letters)
		int[] keyStream = getKeystream(msg.length());
		
		String newMessage = "";
		
		for (int i = 0; i < msg.length(); i++) {
			int temp =  (msg.charAt(i)-65-keyStream[i]);
			if (temp < 0) {
				temp = 26 + temp;
			}
			newMessage += (char) (temp+65);
		}
		
		return newMessage;
	}
	
}
