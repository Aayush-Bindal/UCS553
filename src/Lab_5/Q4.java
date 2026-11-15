package Lab_5;

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of subjects: ");
            int count = Integer.parseInt(scanner.nextLine());
            if (count <= 0) {
                throw new ArithmeticException("subject count must be positive");
            }
            int total = 0;
            for (int i = 1; i <= count; i++) {
                System.out.print("Enter marks for subject " + i + ": ");
                int marks = Integer.parseInt(scanner.nextLine());
                if (marks < 0 || marks > 100) {
                    throw new InvalidMarksException("Marks must be between 0 and 100.");
                }
                total += marks;
            }
            double percentage = (double) total / count;
            System.out.printf("Total: %d, Percentage: %.2f, Grade: %s%n",
                    total, percentage, grade(percentage));
        } catch (InvalidMarksException exception) {
            System.out.println("Invalid marks: " + exception.getMessage());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter valid whole numbers.");
        } catch (ArithmeticException exception) {
            System.out.println("At least one subject is required.");
        }
    }

    private static String grade(double percentage) {
        if (percentage >= 90) return "A";
        if (percentage >= 75) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 40) return "D";
        return "F";
    }

    public static class InvalidMarksException extends Exception {
        public InvalidMarksException(String message) { super(message); }
    }
}
