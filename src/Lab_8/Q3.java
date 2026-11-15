package Lab_8;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Path file = Paths.get("out", "lab8-files", "employees.ser");
        List<Employee> employees = List.of(
                new Employee(101, "Aarav Sharma", "Engineering", 75000),
                new Employee(102, "Diya Kapoor", "Human Resources", 68000),
                new Employee(103, "Kabir Singh", "Finance", 72000));

        try {
            Files.createDirectories(file.getParent());
            serializeEmployees(file, employees);
            List<Employee> restoredEmployees = deserializeEmployees(file);
            System.out.println("Deserialized employee records:");
            restoredEmployees.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Serialization operation failed: " + exception.getMessage());
        }
    }

    private static void serializeEmployees(Path file, List<Employee> employees)
            throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(file.toFile()))) {
            output.writeObject(new ArrayList<>(employees));
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> deserializeEmployees(Path file)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file.toFile()))) {
            return (List<Employee>) input.readObject();
        }
    }
}
