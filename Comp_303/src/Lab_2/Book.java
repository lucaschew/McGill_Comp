package Lab_2;
public class Book {
    private final String aTitle;
    private final String aAuthor;
    private final Genre aGenre;
    private Integer aAvailableCopies;

    /** @pre pTitle != null
     *  @pre pAuthor != null
     *  @pre pGenre != null
     **/
    public Book(String pTitle, String pAuthor, Genre pGenre){
        assert pTitle != null && pAuthor != null && pGenre != null;
        aTitle = pTitle;
        aAuthor = pAuthor;
        aGenre = pGenre;
        aAvailableCopies = 2;
    }

    public String getaTitle() {
        return aTitle;
    }

    public String getaAuthor() {
        return aAuthor;
    }

    public Genre getaGenre() {
        return aGenre;
    }

    public Integer getaAvailableCopies() {
        return aAvailableCopies;
    }

     //  @pre pNumberOfCopies != null
    public void addAvailableCopies(Integer pNumberofCopies) {
        assert pNumberofCopies != null;
        aAvailableCopies = aAvailableCopies + pNumberofCopies;
    }

    //  @pre pNumberOfCopies != null
    public void removeAvailableCopies(Integer pNumberofCopies) {
        assert pNumberofCopies != null;
        aAvailableCopies = aAvailableCopies - pNumberofCopies;
    }
}
