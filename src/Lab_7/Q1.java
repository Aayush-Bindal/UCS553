package Lab_7;

public class Q1 {
    public static <T extends Number> double sum(T[] values) {
        double total = 0;
        for (T value : values) total += value.doubleValue();
        return total;
    }
    public static void main(String[] args) {
        System.out.println("Integers: " + sum(new Integer[]{1, 2, 3}));
        System.out.println("Floats: " + sum(new Float[]{1.5f, 2.5f}));
        System.out.println("Doubles: " + sum(new Double[]{1.25, 2.75}));
    }
}
