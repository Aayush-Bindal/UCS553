package Lab_7;

import java.util.*;

public class Q5 {
    static class Utility {
        static <T> boolean isEqual(T a, T b) { return Objects.equals(a, b); }
        static <T> T getDefault(Optional<T> value, T defaultValue) { return value.orElse(defaultValue); }
        static <T extends Comparable<T>> T max(T a, T b) { return a.compareTo(b) >= 0 ? a : b; }
        static boolean isNull(Object value) { return Objects.isNull(value); }
    }
    public static void main(String[] args) {
        System.out.println("Equal strings: " + Utility.isEqual("Java", "Java"));
        System.out.println("Default: " + Utility.getDefault(Optional.empty(), "fallback"));
        System.out.println("Larger integer: " + Utility.max(10, 20));
        System.out.println("Null check: " + Utility.isNull(null));
    }
}
