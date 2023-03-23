package Lab_2;
import java.util.ArrayList;

public class McGillLibrary {

    private ArrayList<Book> aBooks = new ArrayList<Book>();
    private static McGillLibrary lib = new McGillLibrary();

    private McGillLibrary(){}
    
    public static McGillLibrary getMcGillLibraryInstance() {
    	return lib;
    }

    public void addBookToLibrary(Book pBook){
        assert pBook !=null;
        aBooks.add(pBook);
    }

    public void removeBookFromLibrary(Book pBook){
        assert pBook != null;
        if (aBooks.contains(pBook)) {
            aBooks.remove(pBook);
        }
    }
    
    public ArrayList<Book> getBooks(Object o) {
    	
    	ArrayList<Book> li = new ArrayList<>();
    	
    	if (o.getClass() == String.class) {
    		
    		String author = (String) o;
    		
    		for (Book b: aBooks) {
    			if (b.getaAuthor().equals(author)) 
    				li.add(b);
    		}
    		
    	} else if (o.getClass() == Genre.class) {
    		
    		Genre genre = (Genre) o;
    		
    		for (Book b: aBooks) {
    			if (b.getaGenre().equals(genre)) 
    				li.add(b);
    		}
    		
    	}
    	
    	return li;
    	
    	
    	
    }

}
