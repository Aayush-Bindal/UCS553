package Lab_7;

import java.util.*;

public class Q3 {
    static class Result<T extends Number> {
        private final T marks;
        Result(T marks) { this.marks = Objects.requireNonNull(marks, "Marks are required."); }
        double percentage(double maximum) { return marks.doubleValue() / maximum * 100; }
        boolean passed(double threshold, double maximum) { return percentage(maximum) >= threshold; }
        Optional<T> marks() { return Optional.ofNullable(marks); }
    }
    public static void main(String[] args) {
        Result<Integer> result = new Result<>(82);
        System.out.println("Marks: " + result.marks().orElse(0));
        System.out.printf("Percentage: %.2f%nPassed: %s%n", result.percentage(100), result.passed(40, 100));
    }
}
