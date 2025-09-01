//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);             // <-- create Scanner
        ArrayList<String> records = new ArrayList<>();

        boolean done = false;
        while (!done) {
            System.out.println("\nEnter a new person record:");

            // Pass 'pipe' as the first argument to SafeInput methods:
            String id = SafeInput.getNonZeroLenString(pipe, "Enter ID");
            String firstName = SafeInput.getNonZeroLenString(pipe, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(pipe, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(pipe, "Enter Title (Mr., Mrs., Ms., Dr., etc.)");
            int yob = SafeInput.getRangedInt(pipe, "Enter Year of Birth", 1000, 9999);

            // Build the CSV line exactly as required
            String record = String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yob);
            records.add(record);

            // getYNConfirm takes only the prompt in SafeInput
            done = !SafeInput.getYNConfirm(pipe,"Do you want to add another person?");
        }

        // Ask file name (use SafeInput with pipe)
        String fileName = SafeInput.getNonZeroLenString(pipe, "Enter file name to save (e.g., PersonTestData.txt)");

        // Save file using NIO
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            for (String rec : records) {
                writer.write(rec);
                writer.newLine();
            }
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            // optional: close scanner when finished (program exits afterward)
            pipe.close();
        }
    }
}
