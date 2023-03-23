package Lab_3;

public class LogLibrary extends SteamLibrary {

	private double cumulativeSpaceRequired;
	
	public LogLibrary() {
		super();
		cumulativeSpaceRequired = 0;
	}
	
	@Override
    public void addItem(LibraryItem pGame) {
        super.addItem(pGame);
        cumulativeSpaceRequired += pGame.getSpaceRequired();
        System.out.println("Adding Item " + pGame.getName() + ", total space required :" + cumulativeSpaceRequired + "GB");
    }
	
    public static void main(String[] args) {
        LogLibrary library = new LogLibrary();

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
