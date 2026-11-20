package Lab_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q1 {
    private static final String DATABASE_URL = "jdbc:sqlite:CollegeDB.db";

    public static void main(String[] args) {
        try (Connection connection = openConnection()) {
            createStudentTable(connection);
            insertStudent(connection, 101, "Aarav Sharma", "Computer Science",
                    "aarav@example.com", 88.5);
            insertStudent(connection, 102, "Diya Kapoor", "Information Technology",
                    "diya@example.com", 91.0);
            System.out.println("All student records:");
            displayAllStudents(connection);
            System.out.println("\nStudent with ID 101:");
            searchStudent(connection, 101);
        } catch (SQLException exception) {
            System.out.println("Student database operation failed: " + exception.getMessage());
        }
    }

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    private static void createStudentTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Student (
                    student_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    department TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    marks REAL NOT NULL
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void insertStudent(Connection connection, int studentId, String name,
            String department, String email, double marks) throws SQLException {
        String sql = """
                INSERT OR IGNORE INTO Student
                    (student_id, name, department, email, marks)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setString(2, name);
            statement.setString(3, department);
            statement.setString(4, email);
            statement.setDouble(5, marks);
            statement.executeUpdate();
        }
    }

    private static void displayAllStudents(Connection connection) throws SQLException {
        String sql = "SELECT student_id, name, department, email, marks "
                + "FROM Student ORDER BY student_id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                printStudent(results);
            }
        }
    }

    private static void searchStudent(Connection connection, int studentId) throws SQLException {
        String sql = "SELECT student_id, name, department, email, marks "
                + "FROM Student WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    printStudent(results);
                } else {
                    System.out.println("No student found with ID " + studentId);
                }
            }
        }
    }

    private static void printStudent(ResultSet results) throws SQLException {
        System.out.printf("%d | %s | %s | %s | Marks: %.2f%n",
                results.getInt("student_id"),
                results.getString("name"),
                results.getString("department"),
                results.getString("email"),
                results.getDouble("marks"));
    }
}
