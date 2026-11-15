package Lab_7;

import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        Optional<String> email = Optional.of("student@example.com");
        Optional<String> missing = Optional.empty();
        email.ifPresent(value -> System.out.println("Email: " + value));
        System.out.println("Default: " + missing.orElse("default@example.com"));
        try {
            System.out.println(missing.orElseThrow(() -> new IllegalStateException("Email is missing.")));
        } catch (IllegalStateException exception) {
            System.out.println("Handled: " + exception.getMessage());
        }
    }
}
