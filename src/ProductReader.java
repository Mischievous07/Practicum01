import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {
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
        System.out.printf("%-8s %-15s %-30s %-8s%n", "ID#", "Name", "Description", "Cost");
        System.out.println("====================================================================");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 4) {
                    System.out.printf("%-8s %-15s %-30s $%-8s%n",
                            parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        pipe.close();
    }
}
