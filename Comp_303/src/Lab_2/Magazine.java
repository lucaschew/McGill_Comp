package Lab_2;

public class Magazine {
	
	private final String title;
	private Integer availableCopies;
	
	
	public Magazine(String title, Integer availableCopies) {
		
		this.title = title;
		this.availableCopies = 0;
		
	}
	
	public String getTitle() {
		return this.title;
	}
	
    public Integer getAvailableCopies() {
        return this.availableCopies;
    }

    public void addAvailableCopies(Integer numberOfCopies) {
        assert numberOfCopies != null;
        availableCopies += numberOfCopies;
    }

    public void removeAvailableCopies(Integer numberOfCopies) {
        assert numberOfCopies != null;
        availableCopies -= numberOfCopies;
    }
}
