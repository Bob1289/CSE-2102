// BMIClassification.java
// This program takes in a weight (INPUT 1) and height (INPUT 2) and converts them to kilograms and meters, respectively. It then calculates the BMI and prints the risk category.

import java.util.Scanner;

public class BMIClassification {
    public static void main(String[] args){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter weight and height
        System.out.print("Enter your weight in pounds.");
        String line = input.nextLine();
        System.out.print("Enter your height in inches.");
        String line2 = input.nextLine();

        // Convert weight and height to doubles
        double weight = Double.parseDouble(line);
        double height = Double.parseDouble(line2);

        // Convert weight from pounds to kilograms
        weight = weight * 0.45359237;

        // Convert height from inches to meters
        height = height * 0.0254;

        // Calculate BMI
        double BMI = (weight) / (height * height);

        // Print BMI and risk category
        System.out.println("Your BMI is " + BMI);

        if (BMI < 18.5) {
            System.out.println("Your risk category is Underweight.");
        } else if (BMI < 25) {
            System.out.println("Your risk category is Normal weight.");
        } else if (BMI < 30) {
            System.out.println("Your risk category is Overweight.");
        } else {
            System.out.println("Your risk category is Obese.");
        }
    }
}