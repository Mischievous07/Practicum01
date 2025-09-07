import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        SafeInputObj input = new SafeInputObj(pipe);
        ArrayList<Product> products = new ArrayList<>();

        boolean done = false;
        while (!done) {
            System.out.println("\nEnter a new product record:");

            String id = input.getNonZeroLenString("Enter Product ID");
            String name = input.getNonZeroLenString("Enter Product Name");
            String description = input.getNonZeroLenString("Enter Product Description");
            double cost = input.getDouble("Enter Product Cost");

            Product p = new Product(id, name, description, cost);
            products.add(p);

            done = !input.getYNConfirm("Do you want to add another product?");
        }

        String fileName = input.getNonZeroLenString("Enter file name to save (e.g., ProductTestData.txt)");

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            for (Product p : products) {
                writer.write(p.toCSV());
                writer.newLine();
            }
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            pipe.close();
        }
    }
}
