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
        Scanner pipe = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        SafeInputObj input = new SafeInputObj(pipe);

        boolean done = false;
        while (!done) {
            System.out.println("\nEnter a new person record:");

            String id = input.getNonZeroLenString("Enter ID");
            String firstName = input.getNonZeroLenString("Enter First Name");
            String lastName = input.getNonZeroLenString("Enter Last Name");
            String title = input.getNonZeroLenString("Enter Title (Mr., Mrs., Ms., Dr., etc.)");
            int yob = input.getRangedInt("Enter Year of Birth", 1940, 2010);

            Person p = new Person(id, firstName, lastName, title, yob);
            people.add(p);

            done = !input.getYNConfirm("Do you want to add another person?");
        }

        String fileName = input.getNonZeroLenString("Enter file name to save (e.g., PersonTestData.txt)");

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            for (Person p : people) {
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
