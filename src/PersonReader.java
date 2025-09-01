import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);

        JFileChooser chooser = new JFileChooser(".");
        int choice = chooser.showOpenDialog(null);

        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();

        // Display header
        System.out.printf("%-8s %-12s %-12s %-8s %-6s%n", "ID#", "FirstName", "LastName", "Title", "YOB");
        System.out.println("==========================================================");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 5) {
                    System.out.printf("%-8s %-12s %-12s %-8s %-6s%n",
                            parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Ask user if they want to read another file (optional)
        boolean again = SafeInput.getYNConfirm(pipe,"Do you want to read another file?");
        if (again) {
            main(null); // restart program
        }

        pipe.close();
    }
}