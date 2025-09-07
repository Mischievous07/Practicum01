import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonReader {
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
        ArrayList<Person> people = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 5) {
                    String id = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String title = parts[3];
                    int yob = Integer.parseInt(parts[4]);

                    Person p = new Person(id, firstName, lastName, title, yob);
                    people.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Display data
        System.out.printf("%-8s %-12s %-12s %-8s %-6s%n", "ID#", "FirstName", "LastName", "Title", "YOB");
        System.out.println("==========================================================");
        for (Person p : people) {
            System.out.printf("%-8s %-12s %-12s %-8s %-6d%n",
                    p.getID(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYOB());
        }

        // Optional: read another file
        if (input.getYNConfirm("Do you want to read another file?")) {
            main(null); // restart program
        }

        pipe.close();
    }
}
