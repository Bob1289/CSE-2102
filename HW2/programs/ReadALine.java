// ReadALine.java
// This program reads a line of text from the user and prints it out in a new form.

// Import statements
import java.util.Scanner;

public class ReadALine {
    public static void main(String[] args) {
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a line of text. No punctuation please.\n");
        // Get the line of text.
        String line = input.nextLine();
        System.out.println("I have rephrased that line to read:");
        // Get the first word and make it lowercase
        String firstWord = line.substring(0, line.indexOf(" ")).toLowerCase();
        // Get the rest of the line
        String restOfLine = line.substring(line.indexOf(" ") + 1);
        // Combine the rest of the line with the first word and make the first letter uppercase
        String newLine = (restOfLine.substring(0,1).toUpperCase() + restOfLine.substring(1) + " " + firstWord);
        System.out.println(newLine);
    }
}
