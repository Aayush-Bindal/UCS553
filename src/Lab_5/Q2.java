package Lab_5;

public class Q2 {
    public static void main(String[] args) {
        try {
            System.out.println("Outer block started");
            try {
                System.out.println("Inner result: " + (10 / 0));
            } catch (ArithmeticException exception) {
                System.out.println("Inner catch: " + exception.getMessage());
                throw new IllegalArgumentException("Propagated from inner block", exception);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Outer catch: " + exception.getMessage());
        } finally {
            System.out.println("Outer finally always executes");
        }
    }
}
