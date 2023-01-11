package assignment2;

import java.util.Random;

public class Deck {
 public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
 public static Random gen = new Random();

 public int numOfCards; // contains the total number of cards in the deck
 public Card head; // contains a pointer to the card on the top of the deck


 public Deck(int numOfCardsPerSuit, int numOfSuits) {
	 
	 if (numOfCardsPerSuit < 1 || numOfCardsPerSuit > 13 || numOfSuits < 1 || numOfSuits > suitsInOrder.length)
		 throw new IllegalArgumentException("Number of Card Per a Suit: 1-13; Number of Suits: 1-"+suitsInOrder.length);
  
	 for (int i = 0; i < numOfCardsPerSuit; i++) {
		 
		 for (int z = 0; z < numOfSuits; z++) {
			 
			 //If head is null, create first instance of card and set prev and next
			 if (head == null) { 
				 head = new PlayingCard(suitsInOrder[z], i+1);
				 head.next = head.prev = head;
				 numOfCards=1;
			 } else {
				 
				 Card last = head.prev;
				 
				 //Creates card, with next pointing at head and prev pointing at last node
				 Card newCard = new PlayingCard(suitsInOrder[z], i+1);
				 newCard.next = head;
				 newCard.prev = last;
				 
				 //Adds the new card to the prev of start and next of last card
				 last.next = newCard;
				 head.prev = newCard;
				 numOfCards++;
			 }
			 
		 }
		 
	 }
	 
	 Card redJoker = new Joker("red");
	 Card blackJoker = new Joker("black");
	 
	 //Last Node
	 Card last = head.prev;
	 
	 //Red Joker Node
	 redJoker.next = blackJoker;
	 redJoker.prev = last;
	 last.next = redJoker;
	 
	 //Black Joker Node
	 blackJoker.next = head;
	 blackJoker.prev = redJoker;
	 head.prev = blackJoker;
	 
	 numOfCards += 2;
	 
 }


 public Deck(Deck d) {
  
	 Card tempHead = d.head;
	 
	 while (tempHead.next != d.head) {
		 this.addCard(tempHead.getCopy());
		 tempHead = tempHead.next;
	 }
	 
	 this.addCard(tempHead.getCopy());
	 
 }

 
 public Deck() {}


 public void addCard(Card c) {
  
	 
	 if (head == null) { 
		 head = c;
		 head.next = head.prev = head;
		 numOfCards=1;
	 } else {
		 
		 Card last = head.prev;
		 
		 //Creates card, with next pointing at head and prev pointing at last node
		 c.next = head;
		 c.prev = last;
		 
		 //Adds the new card to the prev of start and next of last card
		 last.next = c;
		 head.prev = c;
		 numOfCards++;
	 }
	 
 }

 public void shuffle() {

	 Card[] arr = new Card[numOfCards];
	 Card tempHead = head;
	 
	 //Setup Array
	 for (int i = 0; i< numOfCards; i++) {
		 arr[i] = tempHead;
		 tempHead = tempHead.next;
	 }
	 
	 //Swap cards
	 for (int i = numOfCards-1; i > 0; i--) {
		 int rng = gen.nextInt(i+1);
		 Card temp = arr[rng];
		 arr[rng] = arr[i];
		 arr[i] = temp;
	 }
	 
	 //Set Head
	 tempHead = head;
	 
	 for (int i = 0; i< numOfCards; i++) {
		 tempHead = arr[i];
		 tempHead.next = arr[(i+1)%(numOfCards)];
		 tempHead.prev = arr[(numOfCards-1+i)%(numOfCards)];
		 
		 //System.out.println(tempHead + " " + tempHead.next + " " + tempHead.prev + " " + (numOfCards-1+i)%(numOfCards) + " " + (i+1)%(numOfCards));
		 
		 tempHead = tempHead.next;
	 }
	 
	 head = tempHead;
	 //System.out.println();
	 
 }

 public Joker locateJoker(String color) {
  
	 Card tempHead = head;
	 
	 while (tempHead.next != head) {
		 if (tempHead instanceof Joker ) {
			 
			 Joker joker = (Joker) tempHead;
			 if (joker.getColor().equals(color))
				 return joker;
		 }
		 tempHead = tempHead.next;
	 }
	 
	 if (tempHead instanceof Joker ) {
		 
		 Joker joker = (Joker) tempHead;
		 if (joker.getColor().equals(color))
			 return joker;
	 }
	 
	 return null;
	 
 }

 public void moveCard(Card c, int p) {
	 
	 Card tempHead = head;
	 
	 for (int i = 0; i < numOfCards; i++) {
		 
		 //Makes card before and after reference each other
		 if (tempHead.equals(c)) {
			 tempHead.prev.next = tempHead.next;
			 tempHead.next.prev = tempHead.prev;
			 break;
		 }
		 
		 tempHead = tempHead.next;
	 }
	 
	 //Goes to card before placement
	 for (int i = 0; i < p; i++) {
		 tempHead = tempHead.next;
	 }
	 
	 Card before = tempHead;
	 Card after = tempHead.next;
	 
	 before.next = c;
	 after.prev = c;
	 c.next = after;
	 c.prev = before;
	 
	 //Cycle back to head
	 while (tempHead != head)
		 tempHead = tempHead.next;
	 
	 head = tempHead;
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(tempHead);
//		 tempHead = tempHead.next;
//	 }
	 
 }


 public void tripleCut(Card firstCard, Card secondCard) {
	 

	 secondCard.next.prev = firstCard.prev;
	 firstCard.prev.next = secondCard.next;
	 head.prev.next = firstCard;
	 firstCard.prev = head.prev;
	 head.prev = secondCard;
	 Card newHead = secondCard.next;
	 secondCard.next = head;
	 
	 head = newHead;

//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head=head.next;
//	 }
	 
 }


 public void countCut() {
  
	 Card tail = head.prev;
	 
	 if (tail.getValue()%numOfCards != 0) {
		 
		 this.moveCard(tail, tail.getValue());
		 
		 head = tail.next;
		 
//		 for (int i = 0; i < numOfCards; i++) {
//			 System.out.println(head);
//			 head=head.next;
//		 }
	 }

	 
 }


 public Card lookUpCard() {
  
	 Card tempHead = head;
	 
	 for (int i = 0; i < head.getValue(); i++) {
//		 System.out.println("--------" + tempHead);
		 tempHead = tempHead.next;
	 }

	 if (tempHead instanceof Joker) {
		 return null;
	 } else {
		 return tempHead;
	 }
	 
 }

 /*
  * TODO: Uses the Solitaire algorithm to generate one value for the keystream 
  * using this deck. This method runs in O(n).
  */
 public int generateNextKeystreamValue() {
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head = head.next;
//	 }
//	 System.out.println();
	 
	 //Step 1: Move Red Joker 1 space down
	 //If Red Joker is top of the deck, move it 1 more space down
	 Card redJoker = this.locateJoker("red");
	 
	 this.moveCard(redJoker, 1);
	 
//	 if (head instanceof Joker){
//		 Joker temp = (Joker) head;
//		 
//		 if ((temp.getColor().equals("red"))) {
//			 this.moveCard(head, 1);
//		 }
//		 
//	 }
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head = head.next;
//	 }
//	 System.out.println();
	 
	 //Step 2: Move Black Joker 2 spaces down
	 //If Black Joker is bottom card, move 2
	 //If Black Joker is above the bottom card, move 2
	 Card blackJoker = this.locateJoker("black");
	 
	 this.moveCard(blackJoker, 2);
	 
	 blackJoker = this.locateJoker("black");
	 
//	 if (head.prev.equals(blackJoker) || head.prev.prev.equals(blackJoker)) {
//		 this.moveCard(blackJoker, 2);
//	 } 
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head = head.next;
//	 }
//	 System.out.println();
	 
	 //Find First and Second Joker
	 Joker firstJoker = null, secondJoker;
	 Card tempHead = head;
	 
	 for (int i = 0; i < numOfCards; i++) {
		 if (tempHead instanceof Joker) {
			  firstJoker = (Joker) tempHead;
			  break;
		 }
		 tempHead = tempHead.next;
	 }
	 
	 if (firstJoker.getColor().equals("red")) {
		 secondJoker = this.locateJoker("black");
	 } else {
		 secondJoker = this.locateJoker("red");
	 }
	 
//	 System.out.println(firstJoker.getColor() + " " + secondJoker.getColor());
	 
	 //Triple Cut
	 this.tripleCut(firstJoker, secondJoker);
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head = head.next;
//	 }
//	 System.out.println();
	 
	 //Count Cut
	 this.countCut();
	 
//	 for (int i = 0; i < numOfCards; i++) {
//		 System.out.println(head);
//		 head = head.next;
//	 }
//	 System.out.println();
	 
	 //Look Up Card
	 //If Null Redo, Else Return
	 Card result = this.lookUpCard();
	 if (result == null) {
		 return generateNextKeystreamValue();
	 }
	 
	 return result.getValue();
	 
 }


 public abstract class Card { 
  public Card next;
  public Card prev;

  public abstract Card getCopy();
  public abstract int getValue();

 }

 public class PlayingCard extends Card {
  public String suit;
  public int rank;

  public PlayingCard(String s, int r) {
   this.suit = s.toLowerCase();
   this.rank = r;
  }

  public String toString() {
   String info = "";
   if (this.rank == 1) {
    //info += "Ace";
    info += "A";
   } else if (this.rank > 10) {
    String[] cards = {"Jack", "Queen", "King"};
    //info += cards[this.rank - 11];
    info += cards[this.rank - 11].charAt(0);
   } else {
    info += this.rank;
   }
   //info += " of " + this.suit;
   info = (info + this.suit.charAt(0)).toUpperCase();
   return info;
  }

  public PlayingCard getCopy() {
   return new PlayingCard(this.suit, this.rank);   
  }

  public int getValue() {
   int i;
   for (i = 0; i < suitsInOrder.length; i++) {
    if (this.suit.equals(suitsInOrder[i]))
     break;
   }

   return this.rank + 13*i;
  }

 }

 public class Joker extends Card{
  public String redOrBlack;

  public Joker(String c) {
   if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black")) 
    throw new IllegalArgumentException("Jokers can only be red or black"); 

   this.redOrBlack = c.toLowerCase();
  }

  public String toString() {
   //return this.redOrBlack + " Joker";
   return (this.redOrBlack.charAt(0) + "J").toUpperCase();
  }

  public Joker getCopy() {
   return new Joker(this.redOrBlack);
  }

  public int getValue() {
   return numOfCards - 1;
  }

  public String getColor() {
   return this.redOrBlack;
  }
 }

}
