package Lab_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Q3 {
    private static final String FILE_NAME = "department-graph.dat";

    public static void main(String[] args) {
        Course sharedCourse = new Course("CS401", "Advanced Java");
        Faculty firstFaculty = new Faculty("F001", "Dr. Anil Mehta",
                List.of(sharedCourse));
        Faculty secondFaculty = new Faculty("F002", "Dr. Neha Rao",
                List.of(sharedCourse));
        Department department = new Department("Computer Science",
                List.of(firstFaculty, secondFaculty));

        try {
            serializeDepartment(department);
            Department restoredDepartment = deserializeDepartment();
            restoredDepartment.display();
            Course firstReference = restoredDepartment.facultyMembers.get(0).courses.get(0);
            Course secondReference = restoredDepartment.facultyMembers.get(1).courses.get(0);
            System.out.println("Shared course reference preserved: "
                    + (firstReference == secondReference));
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Object graph serialization failed: " + exception.getMessage());
        }
    }

    private static void serializeDepartment(Department department) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            output.writeObject(department);
        }
    }

    private static Department deserializeDepartment() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Department) input.readObject();
        }
    }

    private static class Department implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String name;
        private final List<Faculty> facultyMembers;

        private Department(String name, List<Faculty> facultyMembers) {
            this.name = name;
            this.facultyMembers = facultyMembers;
        }

        private void display() {
            System.out.println("Department: " + name);
            for (Faculty faculty : facultyMembers) {
                System.out.println("  Faculty: " + faculty.name);
                for (Course course : faculty.courses) {
                    System.out.println("    Course: " + course.code + " - " + course.title);
                }
            }
        }
    }

    private static class Faculty implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String facultyId;
        private final String name;
        private final List<Course> courses;

        private Faculty(String facultyId, String name, List<Course> courses) {
            this.facultyId = facultyId;
            this.name = name;
            this.courses = courses;
        }
    }

    private static class Course implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String code;
        private final String title;

        private Course(String code, String title) {
            this.code = code;
            this.title = title;
        }
    }
}
