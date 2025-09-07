public class ObjInputTest {
    public static void main(String[] args) {
        // Create SafeInputObj instance
        SafeInputObj input = new SafeInputObj();

        System.out.println("=== SafeInputObj Interactive Test ===");

        // Test getNonZeroLenString
        String name = input.getNonZeroLenString("Enter your name");
        System.out.println("You entered: " + name);

        // Test getInt
        int age = input.getInt("Enter your age");
        System.out.println("You entered: " + age);

        // Test getRangedInt
        int score = input.getRangedInt("Enter a score between 0 and 100", 0, 100);
        System.out.println("You entered: " + score);

        // Test getDouble
        double price = input.getDouble("Enter a price");
        System.out.println("You entered: " + price);

        // Test getRangedDouble
        double rating = input.getRangedDouble("Enter a rating between 1.0 and 5.0", 1.0, 5.0);
        System.out.println("You entered: " + rating);

        // Test getYNConfirm
        boolean wantsNewsletter = input.getYNConfirm("Do you want to receive the newsletter?");
        System.out.println("You answered: " + (wantsNewsletter ? "Yes" : "No"));

        // Test getRegExString
        String phone = input.getRegExString("Enter your phone number (XXX-XXX-XXXX)", "\\d{3}-\\d{3}-\\d{4}");
        System.out.println("You entered: " + phone);

        // Close the input scanner
        input.close();

        System.out.println("\n=== Test Complete ===");
    }
}

