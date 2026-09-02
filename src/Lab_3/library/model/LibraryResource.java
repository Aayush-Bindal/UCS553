package Lab_3.library.model;

public abstract class LibraryResource {
    private static int resourceCount;
    private static String libraryName = "Central University Library";

    private int resourceId;
    private String title;
    private String author;

    protected LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        resourceCount++;
    }

    public abstract double calculateFine(int overdueDays);

    protected String resourceSummary() {
        return String.format("ID: %d | Title: %s | Author: %s", resourceId, title, author);
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static void setLibraryName(String libraryName) {
        LibraryResource.libraryName = libraryName;
    }

    public static void displayTotalResources() {
        System.out.println("Total resources created: " + resourceCount);
    }
}
