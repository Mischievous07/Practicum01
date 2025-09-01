import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();

        boolean done = false;
        while (!done) {
            System.out.println("\nEnter a new product record:");

            String id = SafeInput.getNonZeroLenString(pipe, "Enter Product ID");
            String name = SafeInput.getNonZeroLenString(pipe, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(pipe, "Enter Product Description");
            double cost = SafeInput.getDouble(pipe, "Enter Product Cost");

            String record = String.format("%s, %s, %s, %.2f", id, name, description, cost);
            products.add(record);

            done = !SafeInput.getYNConfirm(pipe,"Do you want to add another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(pipe, "Enter file name to save (e.g., ProductTestData.txt)");

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            for (String rec : products) {
                writer.write(rec);
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
/* buh buh aiosnidfnoiasndifonaiosdnfioasdn HELLO IT is ME HOLDEN K*/