// WhatsInAName.java
// This program reads a name the user and prints out the first name, last name, and initials and the number of characters in each name.

// Import statements
import java.util.Scanner;

public class WhatsInAName {
    public static void main(String[] args) {
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your first name and last name, seperated by a space\n");
        // Get the name.
        String name = input.nextLine();

        // Get the first name and last name
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ") + 1);

        // Print the first name and the length of the first name
        System.out.println("Your first name is " + firstName + ", which has " + firstName.length() + " characters");
        // Print the last name and the length of the last name
        System.out.println("Your last name is " + lastName + ", which has " + lastName.length() + " characters");
        // Print the initials by getting the first letter of the first name and the first letter of the last name
        System.out.println("Your initials are " + firstName.substring(0,1) + lastName.substring(0,1));
        
    }    
}
