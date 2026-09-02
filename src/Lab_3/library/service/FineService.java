package Lab_3.library.service;

import Lab_3.library.model.LibraryResource;
import Lab_3.library.model.Printable;
import Lab_3.library.util.InputValidator;

public class FineService {
    public double calculateTotalFine(LibraryResource[] resources, int[] overdueDays) {
        if (resources.length != overdueDays.length) {
            throw new IllegalArgumentException("Each resource must have one overdue-days value");
        }

        double totalFine = 0;
        for (int i = 0; i < resources.length; i++) {
            if (!InputValidator.isValidFineDays(overdueDays[i])) {
                throw new IllegalArgumentException("Overdue days cannot be negative");
            }
            totalFine += resources[i].calculateFine(overdueDays[i]);
        }
        return totalFine;
    }

    public void displayResources(LibraryResource[] resources, int[] overdueDays) {
        for (int i = 0; i < resources.length; i++) {
            ((Printable) resources[i]).printDetails();
            System.out.printf("  Overdue days: %d | Fine: Rs. %.2f%n", overdueDays[i], resources[i].calculateFine(overdueDays[i]));
        }
    }
}
