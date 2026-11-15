package Lab_8;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        Path directory = Paths.get("out", "lab8-files");
        Path logFile = directory.resolve("application.log");
        Path summaryFile = directory.resolve("log-summary.txt");
        Path filteredFile = directory.resolve("filtered-entries.log");

        try {
            Files.createDirectories(directory);
            createSampleLog(logFile);
            analyzeLog(logFile, summaryFile, filteredFile);
            System.out.println("Log analysis written to: " + summaryFile);
            System.out.println("Filtered entries written to: " + filteredFile);
        } catch (IOException exception) {
            System.out.println("Log processing failed: " + exception.getMessage());
        }
    }

    private static void createSampleLog(Path logFile) throws IOException {
        List<String> entries = List.of(
                "INFO Application started",
                "WARNING Disk space is low",
                "INFO User logged in",
                "ERROR Database connection failed",
                "INFO Retry completed",
                "ERROR Request timed out");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile.toFile()))) {
            for (String entry : entries) {
                writer.write(entry);
                writer.newLine();
            }
        }
    }

    private static void analyzeLog(Path logFile, Path summaryFile, Path filteredFile)
            throws IOException {
        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile.toFile()));
             BufferedWriter summaryWriter = new BufferedWriter(new FileWriter(summaryFile.toFile()));
             BufferedWriter filteredWriter = new BufferedWriter(new FileWriter(filteredFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalEntries++;
                if (line.contains("ERROR")) {
                    errorCount++;
                    filteredWriter.write(line);
                    filteredWriter.newLine();
                } else if (line.contains("WARNING")) {
                    warningCount++;
                    filteredWriter.write(line);
                    filteredWriter.newLine();
                }
            }
            summaryWriter.write("Total log entries: " + totalEntries);
            summaryWriter.newLine();
            summaryWriter.write("ERROR entries: " + errorCount);
            summaryWriter.newLine();
            summaryWriter.write("WARNING entries: " + warningCount);
            summaryWriter.newLine();
        }
        System.out.printf("Total entries: %d, ERROR: %d, WARNING: %d%n",
                totalEntries, errorCount, warningCount);
    }
}
