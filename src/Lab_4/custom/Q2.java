package Lab_4.custom;

public class Q2 {
    public static void main(String[] args) {
        System.out.println("CUSTOM EXCEPTION AND DEBUGGING DEMONSTRATION");

        testRegistration("Aarav", 20);
        testRegistration("Diya", 15);
        testRegistration("", 22);

        testDivision(100, 5);
        testDivision(100, 0);
    }

    private static void testRegistration(String name, int age) {
        try {
            registerStudent(name, age);
            System.out.println("Registration successful for " + name);
        } catch (InvalidAgeException | InvalidNameException exception) {
            System.out.println("Registration failed: " + exception.getMessage());
        }
    }

    private static void registerStudent(String name, int age)
            throws InvalidAgeException, InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be empty");
        }
        if (age < 18) {
            throw new InvalidAgeException("Student must be at least 18 years old");
        }
    }

    private static void testDivision(int totalMarks, int numberOfSubjects) {
        try {
            double average = calculateAverage(totalMarks, numberOfSubjects);
            System.out.printf("Average marks: %.2f%n", average);
        } catch (IllegalArgumentException exception) {
            System.out.println("Calculation failed: " + exception.getMessage());
        }
    }

    private static double calculateAverage(int totalMarks, int numberOfSubjects) {
        // The guard fixes the original debugging error: division by zero.
        if (numberOfSubjects <= 0) {
            throw new IllegalArgumentException("Number of subjects must be greater than zero");
        }
        return (double) totalMarks / numberOfSubjects;
    }

    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }
}
