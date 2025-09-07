import java.util.Scanner;

/**
 * SafeInputObj provides safe, reusable input methods for reading from the console.
 * All methods use the Scanner instance stored in the object (pipe).
 */
public class SafeInputObj {
    private Scanner pipe;

    /**
     * Default constructor initializes Scanner with System.in
     */
    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    /**
     * Constructor that accepts an existing Scanner
     * @param scanner Scanner instance to use
     */
    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    /**
     * Get a non-empty String from the user
     * @param prompt Prompt message
     * @return Non-empty String
     */
    public String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    /**
     * Get an int value without constraints
     * @param prompt Prompt message
     * @return int entered
     */
    public int getInt(String prompt) {
        int retVal = 0;
        String trash;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get an int within a specific range
     * @param prompt Prompt message
     * @param low Inclusive lower bound
     * @param high Inclusive upper bound
     * @return int value in range
     */
    public int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        String trash;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) done = true;
                else System.out.println("Number is out of range [" + low + "-" + high + "]: " + retVal);
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a double value without constraints
     * @param prompt Prompt message
     * @return double value entered
     */
    public double getDouble(String prompt) {
        double retVal = 0;
        String trash;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a double value within a specific range
     * @param prompt Prompt message
     * @param low Inclusive lower bound
     * @param high Inclusive upper bound
     * @return double value in range
     */
    public double getRangedDouble(String prompt, double low, double high) {
        double retVal = 0;
        String trash;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) done = true;
                else System.out.println("Number is out of range [" + low + "-" + high + "]: " + retVal);
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);

        return retVal;
    }

    /**
     * Get a yes/no confirmation
     * @param prompt Prompt message
     * @return true for yes, false for no
     */
    public boolean getYNConfirm(String prompt) {
        boolean retVal = true;
        String response;
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) { gotAVal = true; retVal = true; }
            else if (response.equalsIgnoreCase("N")) { gotAVal = true; retVal = false; }
            else { System.out.println("You must answer [Y/N]!"); }
        } while (!gotAVal);

        return retVal;
    }

    /**
     * Get a string that matches a regex pattern
     * @param prompt Prompt message
     * @param regExPattern Regex pattern
     * @return String that matches pattern
     */
    public String getRegExString(String prompt, String regExPattern) {
        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                gotAVal = true;
            } else {
                System.out.println(response + " must match the pattern " + regExPattern);
                System.out.println("Try again!");
            }
        } while (!gotAVal);

        return response;
    }

    /**
     * Close the Scanner instance (optional)
     */
    public void close() {
        if (pipe != null) pipe.close();
    }
}
