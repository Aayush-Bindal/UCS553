package Lab_5;

import java.util.function.IntSupplier;

public class Q1 {
    public static void main(String[] args) {
        int zero = 0;
        demonstrate("10 / 0", () -> 10 / zero);
        demonstrate("array[4]", () -> {
            int[] array = {1, 2, 3};
            return array[4];
        });
        demonstrate("parse text", () -> Integer.parseInt("abc"));
        demonstrate("null length", () -> ((String) null).length());
    }

    private static void demonstrate(String operation, IntSupplier operationToRun) {
        try {
            operationToRun.getAsInt();
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException |
                 NumberFormatException | NullPointerException exception) {
            System.out.println(operation + " -> " + exception.getClass().getSimpleName()
                    + ": " + exception.getMessage());
        }
    }
}
