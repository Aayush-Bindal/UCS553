package Lab_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Q2 {
    private static final String FILE_NAME = "employees-versioned.dat";

    public static void main(String[] args) {
        Employee employee = new Employee(201, "Meera Joshi", 72000);

        try {
            serializeEmployee(employee);
            Employee restoredEmployee = deserializeEmployee();
            System.out.println("Deserialized employee record:");
            System.out.println(restoredEmployee);
            System.out.println("New fields use their default values when absent in old data.");
            demonstrateVersionMismatch();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Employee serialization failed: " + exception.getMessage());
        }
    }

    private static void serializeEmployee(Employee employee) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            output.writeObject(employee);
        }
    }

    private static Employee deserializeEmployee() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Employee) input.readObject();
        }
    }

    private static void demonstrateVersionMismatch() {
        System.out.println("If serialVersionUID changes, deserialization throws "
                + "InvalidClassException because the class versions do not match.");
    }

    private static class Employee implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int employeeId;
        private final String name;
        private final double salary;
        private String department;
        private int joiningYear;

        private Employee(int employeeId, String name, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("%d | %s | Rs. %.2f | Department: %s | Joining year: %d",
                    employeeId, name, salary, department, joiningYear);
        }
    }
}
