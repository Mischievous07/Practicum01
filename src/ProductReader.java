import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        SafeInputObj input = new SafeInputObj(pipe);

        JFileChooser chooser = new JFileChooser(".");
        int choice = chooser.showOpenDialog(null);

        if (choice != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String description = parts[2];
                    double cost = Double.parseDouble(parts[3]);

                    Product p = new Product(id, name, description, cost);
                    products.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Display data
        System.out.printf("%-8s %-15s %-30s %-8s%n", "ID#", "Name", "Description", "Cost");
        System.out.println("====================================================================");
        for (Product p : products) {
            System.out.printf("%-8s %-15s %-30s $%-8.2f%n",
                    p.getID(), p.getName(), p.getDescription(), p.getCost());
        }

        pipe.close();
    }
}

