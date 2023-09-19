package Lab_3;

public interface LibraryItem {

    void install();
    String getName();
    double getSpaceRequired();
    LibraryItem copy();

}
