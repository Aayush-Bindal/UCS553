package Lab_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Q1 {
    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(101, "Aarav Sharma", "Computer Science", 8.7),
                new Student(102, "Diya Kapoor", "Information Technology", 9.1),
                new Student(103, "Kabir Singh", "Electronics", 8.4));

        try {
            serializeStudents(students);
            List<Student> restoredStudents = deserializeStudents();
            System.out.println("Deserialized student records:");
            restoredStudents.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Student serialization failed: " + exception.getMessage());
        }
    }

    private static void serializeStudents(List<Student> students) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            output.writeObject(students);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Student> deserializeStudents()
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) input.readObject();
        }
    }

    private static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int studentId;
        private final String name;
        private final String department;
        private final double cgpa;

        private Student(int studentId, String name, String department, double cgpa) {
            this.studentId = studentId;
            this.name = name;
            this.department = department;
            this.cgpa = cgpa;
        }

        @Override
        public String toString() {
            return String.format("%d | %s | %s | CGPA: %.2f",
                    studentId, name, department, cgpa);
        }
    }
}
