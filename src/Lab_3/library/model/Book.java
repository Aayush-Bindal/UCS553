package Lab_3.library.model;

public class Book extends LibraryResource implements Printable {
    private static final double FINE_PER_DAY = 5.0;

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * FINE_PER_DAY;
    }

    @Override
    public void printDetails() {
        System.out.println("Book | " + resourceSummary());
    }
}
