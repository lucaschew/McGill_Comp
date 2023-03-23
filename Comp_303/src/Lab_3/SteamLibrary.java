package Lab_3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SteamLibrary {

    private final List<LibraryItem> aGames = new ArrayList<>();
    private LibraryItem aDefault;

    /**
     * @pre pGame != null
     */
    public void addItem(LibraryItem pGame) {
        assert pGame != null;
        aGames.add(pGame);
    }

    /**
     * Installs all items in the library.
     */
    public void installAll() {
    	Iterator<LibraryItem> iterator = aGames.iterator();
    	
    	while (iterator.hasNext()) {
    		iterator.next().install();
    	}
    	
    }
    
    public void setDefault(LibraryItem pDefault) {
    	this.aDefault = pDefault.copy();
    }
    
    public void addDefault() {
    	assert aDefault != null;
    	aGames.add(aDefault.copy());
    }

    public static void main(String[] args) {
        SteamLibrary library = new SteamLibrary();

        Game g1 = new Game("Prison Architect", 10.2);
        Game g2 = new Game("Cities: Skylines", 3.1);
        Game g3 = new Game("American Truck Simulator", 20.5);

        // demonstrate your implementation below
        library.addItem(g1);
        library.addItem(g2);
        library.addItem(g3);
        library.installAll();
    }
}
