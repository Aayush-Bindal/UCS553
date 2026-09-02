package Lab_3.library;

import Lab_3.library.model.Book;
import Lab_3.library.model.DigitalResource;
import Lab_3.library.model.Journal;
import Lab_3.library.model.LibraryResource;
import Lab_3.library.service.FineService;
import Lab_3.library.util.InputValidator;

public class Main {
    public static void main(String[] args) {
        LibraryResource.setLibraryName("Central University Library");

        LibraryResource[] resources = {
            createBook(201, "Clean Code", "Robert C. Martin"),
            createBook(202, "Effective Java", "Joshua Bloch"),
            createJournal(203, "Java Research Journal", "University Press"),
            createDigitalResource(204, "Data Structures E-Book", "Mark Allen"),
            createDigitalResource(205, "Algorithms Online Course", "Nina Sharma")
        };
        int[] overdueDays = {4, 0, 3, 6, 2};

        System.out.println("Library: " + LibraryResource.getLibraryName());
        System.out.println("==================== RESOURCES ====================");
        FineService fineService = new FineService();
        fineService.displayResources(resources, overdueDays);
        System.out.printf("Total fine: Rs. %.2f%n", fineService.calculateTotalFine(resources, overdueDays));
        LibraryResource.displayTotalResources();
    }

    private static LibraryResource createBook(int id, String title, String author) {
        validateResourceId(id);
        return new Book(id, title, author);
    }

    private static LibraryResource createDigitalResource(int id, String title, String author) {
        validateResourceId(id);
        return new DigitalResource(id, title, author);
    }

    private static LibraryResource createJournal(int id, String title, String author) {
        validateResourceId(id);
        return new Journal(id, title, author);
    }

    private static void validateResourceId(int id) {
        if (!InputValidator.isValidResourceId(id)) {
            throw new IllegalArgumentException("Resource ID must be greater than zero");
        }
    }
}
