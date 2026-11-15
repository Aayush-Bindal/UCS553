package Lab_5;

import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            double first = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter operator (+, -, *, /): ");
            String operator = scanner.nextLine().trim();
            System.out.print("Enter second number: ");
            double second = Double.parseDouble(scanner.nextLine());
            System.out.printf("Result: %.2f%n", calculate(first, second, operator));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid numeric input.");
        } catch (ArithmeticException exception) {
            System.out.println("Division by zero is not allowed.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid operator: " + exception.getMessage());
        }
    }

    private static double calculate(double first, double second, String operator) {
        return switch (operator) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> {
                if (second == 0) throw new ArithmeticException("zero divisor");
                yield first / second;
            }
            default -> throw new IllegalArgumentException(operator);
        };
    }
}
