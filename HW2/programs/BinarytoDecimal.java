//BinarytoDecimal.java
//This program takes in a binary number(INPUT) and converts it to a decimal number(OUTPUT)

//Import the Scanner class
import java.util.Scanner;

public class BinarytoDecimal {

    public static void main(String[] args) {
        //Create a new Scanner object
        Scanner input = new Scanner(System.in);
        //Prompt the user to enter a binary number
        System.out.print("Enter a binary number: ");
        //Store the binary number in a String
        String binary = input.nextLine();
        //Create a variable to store the decimal value and and increasing multiplier
        int decimal = 0;
        int multi = 1;
        //Loop through the binary number from right to left
        for (int i = binary.length() - 1; i >= 0; i--) {
            //Only if the current digit is 1, then add the multiplier to the decimal value
            if (binary.charAt(i) == '1') {
                //Add the multiplier to the decimal value
                decimal += multi;
            }
            //Increase the multiplier by a factor of 2
            multi *= 2;
        }
        //Print the decimal value
        System.out.println("The decimal value is " + decimal);
    }
    
}
