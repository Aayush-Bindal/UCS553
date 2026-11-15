package Lab_7;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Q4 {
    public static void main(String[] args) {
        String name = "Java Workshop";
        LocalDate date = LocalDate.now().plusDays(10);
        LocalTime start = LocalTime.of(10, 0);
        Optional<String> venue = Optional.of("Room 101");
        System.out.println(name + " | " + date + " at " + start);
        System.out.println(date.isAfter(LocalDate.now()) ? "Future event" : date.equals(LocalDate.now()) ? "Today" : "Past event");
        System.out.println("Days remaining: " + ChronoUnit.DAYS.between(LocalDate.now(), date));
        System.out.println("Ends at: " + start.plusHours(2));
        System.out.println("Venue: " + venue.orElse("Venue not specified"));
    }
}
