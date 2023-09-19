package Lab_3;

import java.util.ArrayList;
import java.util.List;

public class GamePack implements LibraryItem {

    private final String aName;
    private final double aSpaceRequired; // in GB
    private boolean aInstalled = false;
    private List<LibraryItem> aGames;

    public GamePack(GamePack pGamePack) {
    	this.aName = pGamePack.aName;
    	this.aSpaceRequired = pGamePack.aSpaceRequired;
    	this.aGames = new ArrayList<>(pGamePack.aGames);
    	
    	this.aGames = new ArrayList<>();
    	for (LibraryItem g: pGamePack.aGames) {
    		this.aGames.add(g.copy());
    	}
    }
    
    public GamePack(String pName) {
    	this.aName = pName;
        this.aSpaceRequired = 0;
        this.aGames = new ArrayList<>();
    }
    
    public void addItem(LibraryItem aItem) {
    	assert aItem != null;
    	this.aGames.add(aItem);
    }
	
	@Override
	public void install() {
		if (aInstalled) return;
		
		for (LibraryItem g: aGames) {
			g.install();
		}
		System.out.println("GamePack " + aName + " installed");
		this.aInstalled = true;
	}
	
    @Override
    public String getName() {
    	return this.aName;
    }

	@Override
	public double getSpaceRequired() {
		return aSpaceRequired;
	}
	
	@Override
	public LibraryItem copy() {
		return new GamePack(this);
	}

}
