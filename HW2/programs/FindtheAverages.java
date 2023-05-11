//FindtheAverages.java
//This program reads a file of student names and scores and prints the average score for each student.

//Import statements
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;


public class FindtheAverages {
    public static void main(String[] args) {
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file:\n");
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
        // Read the file's contents line by line.
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            // Split the line into an array of strings by using commas
            String[] data = line.split(",");
            // Calculate the sum of the scores
            double sum = 0;
            for (int i = 1; i < data.length; i++) {
                sum += Double.parseDouble(data[i]);
            }
            // Print the average score
            System.out.printf("The average score for " + data[0] + "is %.2f \n", (sum / 3));
        }        
    }

}
