package Lab_2;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        McGillLibrary aMcGillLibrary = McGillLibrary.getMcGillLibraryInstance();

        Book LOTR = new Book("Lord of The Rings", "Tolkein", Genre.FANTASY);
        Book Hobbit = new Book("The Hobbit", "Tolkein", Genre.FANTASY);
        Book Bliss = new Book("Mr. Bliss", "Tolkein", Genre.CHILDREN);
        Book Eragon = new Book("Eragon", "Paolini", Genre.FANTASY);

        aMcGillLibrary.addBookToLibrary(LOTR);
        aMcGillLibrary.addBookToLibrary(Hobbit);
        aMcGillLibrary.addBookToLibrary(Bliss);
        aMcGillLibrary.addBookToLibrary(Eragon);
        
        System.out.println(getBook(aMcGillLibrary, "Tolkein", Genre.FANTASY));
    }
    
    public static ArrayList<Book> getBook(McGillLibrary aMcGillLibrary, String author, Genre genre) {
    	
    	//System.out.println(authored);
    	
    	ArrayList<Book> authored = aMcGillLibrary.getBooks(author);
    	ArrayList<Book> genred = aMcGillLibrary.getBooks(genre);
    	
    	System.out.println(authored);
    	System.out.println(genred);
    	
    	ArrayList<Book> combined = new ArrayList<Book>();
    	
    	for (Book aBook: authored) {
    		
    		if (genred.contains(aBook)) {
    			combined.add(aBook);
    		}
    		
    	}
    	
    	return combined;
    	
    }
}
