package Lab_8;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {
        Path directory = Paths.get("out", "lab8-files");
        Path binarySource = directory.resolve("students.bin");
        Path binaryCopy = directory.resolve("students-copy.bin");
        Path textFile = directory.resolve("students.txt");

        try {
            Files.createDirectories(directory);
            writeStudentDetails(textFile, List.of(
                    "S001,Aarav Sharma,Computer Science",
                    "S002,Diya Kapoor,Information Technology"));
            copyBinaryFile(textFile, binarySource);
            copyBinaryFile(binarySource, binaryCopy);
            System.out.println("Student details written to: " + textFile);
            System.out.println("Binary file copied to: " + binaryCopy);
            System.out.println("Text file contents:");
            readStudentDetails(textFile);
        } catch (IOException exception) {
            System.out.println("File operation failed: " + exception.getMessage());
        }
    }

    private static void writeStudentDetails(Path file, List<String> students) throws IOException {
        try (FileWriter writer = new FileWriter(file.toFile())) {
            for (String student : students) {
                writer.write(student);
                writer.write(System.lineSeparator());
            }
        }
    }

    private static void readStudentDetails(Path file) throws IOException {
        try (FileReader reader = new FileReader(file.toFile())) {
            char[] buffer = new char[128];
            int charactersRead;
            while ((charactersRead = reader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, charactersRead));
            }
        }
    }

    private static void copyBinaryFile(Path source, Path destination) throws IOException {
        try (FileInputStream input = new FileInputStream(source.toFile());
             FileOutputStream output = new FileOutputStream(destination.toFile())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
    }
}
