package Lab_4.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        System.out.println("JAVA EXCEPTION HIERARCHY DEMONSTRATION");
        demonstrateArithmeticException();
        demonstrateArrayIndexOutOfBoundsException();
        demonstrateNumberFormatException();
        demonstrateInputMismatchException();
        demonstrateCheckedException();
        demonstrateFinallyBlock();
    }

    private static void demonstrateArithmeticException() {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException exception) {
            System.out.println("ArithmeticException handled: " + exception.getMessage());
        }
    }

    private static void demonstrateArrayIndexOutOfBoundsException() {
        try {
            int[] numbers = {10, 20, 30};
            System.out.println(numbers[3]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("ArrayIndexOutOfBoundsException handled: " + exception.getMessage());
        }
    }

    private static void demonstrateNumberFormatException() {
        try {
            int number = Integer.parseInt("Java");
            System.out.println(number);
        } catch (NumberFormatException exception) {
            System.out.println("NumberFormatException handled: invalid numeric text");
        }
    }

    private static void demonstrateInputMismatchException() {
        try (Scanner scanner = new Scanner("not-a-number")) {
            int number = scanner.nextInt();
            System.out.println(number);
        } catch (InputMismatchException exception) {
            System.out.println("InputMismatchException handled: expected an integer");
        }
    }

    private static void demonstrateCheckedException() {
        try (BufferedReader reader = new BufferedReader(new StringReader("Checked exception example"))) {
            System.out.println("Read text: " + reader.readLine());
        } catch (IOException exception) {
            System.out.println("IOException handled: " + exception.getMessage());
        }
    }

    private static void demonstrateFinallyBlock() {
        try {
            System.out.println("Finally block example started");
        } catch (RuntimeException exception) {
            System.out.println("RuntimeException handled");
        } finally {
            System.out.println("Finally block always executes");
        }
    }
}
