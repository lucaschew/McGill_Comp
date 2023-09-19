package Lab_3;

public class Game implements LibraryItem {

    private final String aName;
    private final double aSpaceRequired; // in GB
    private boolean aInstalled = false;

    public Game(Game pGame) {
    	this.aName = pGame.aName;
    	this.aSpaceRequired = pGame.aSpaceRequired;
    }
    
    public Game(String pName, double pSpaceRequired) {
        aName = pName;
        aSpaceRequired = pSpaceRequired;
    }

    @Override
    public void install() {
        if (aInstalled) return;
        System.out.println("Game " + aName + " installed");
        aInstalled = true;
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
		return new Game(this);
	}
}
