package Lab_8;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int employeeId;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(int employeeId, String name, String department, double salary) {
        if (name == null || name.isBlank() || department == null || department.isBlank()) {
            throw new IllegalArgumentException("Employee name and department are required.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | Rs. %.2f",
                employeeId, name, department, salary);
    }
}
