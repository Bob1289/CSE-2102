// TaylorSeries.java
// This program takes in a number n (INPUT 1) and a number x (INPUT 2) and calculates e^x using the Taylor series.

import java.util.Scanner;
import java.lang.Math;

public class TaylorSeries {

    public static void main(String[] args){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter n and x
        System.out.print("Input n: ");
        String line = input.nextLine();
        System.out.print("Input x: ");
        String line2 = input.nextLine();

        // Convert n and x to doubles
        double n = Double.parseDouble(line);
        double x = Double.parseDouble(line2);

        // Initialize sum and term
        double sum = 1;
        double term = 1;

        // Calculate e^x using the Taylor series by adding the terms to the sum until the nth term is reached
        for (int i = 1; i <= n; i++) {
            term = term * x / i;
            sum = sum + term;
        }

        // Print the result
        System.out.printf("e^x is %.2f\n",sum);
    }
}