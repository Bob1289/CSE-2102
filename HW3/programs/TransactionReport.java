// TransactionReport.java
// This program read in a file of transactions formatted as SKU,Quantity,Price,Description and prints each transaction and the total sales.

import java.util.Scanner;
import java.lang.Math;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class TransactionReport {

    public static void main(String[] args){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        // Get the file name.
        String fileName = input.nextLine();
        // Create a File object for the file.
        File file = new File(fileName);
        // Create a Scanner object for the file.
        Scanner fileInput = null;
        // Open the file.
        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }  

        // Read the file's contents line by line but skip the first line
        fileInput.nextLine();
        double grand_total = 0;
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            // Split the line into an array of strings by using commas
            String[] data = line.split(",");
            // Get the SKU, quantity, price, and description
            String SKU = data[0];
            double quantity = Double.parseDouble(data[1]);
            double price = Double.parseDouble(data[2]);
            String description = data[3];

            // Calculate the total for the transaction
            double total = quantity * price;
            grand_total += total;
            
            // Print the transaction
            System.out.printf("Sold %.0f of %s (SKU: %s) at $%.2f each. Sale is $%.2f\n", quantity, description, SKU, price, total);
        }

        // Print the grand total
        System.out.printf("Total sales: $%.2f\n", grand_total);

    }

}