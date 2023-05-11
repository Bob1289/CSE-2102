import java.util.Scanner;
// VendingChange.java
// This program takes in the amount of an item(USER INPUT) and calculates the change of $1 in the amount of quarters, dimes, and nickels(OUTPUT)

public class VendingChange {
    public static void main(String[] args){
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the price of an item: ");
            String line = input.nextLine();

            //Set the value of item to the input
            int item = Integer.parseInt(line);

            if (item > 100 || item < 25 || item % 5 != 0) {
                System.out.println("Invalid Input!");
            } else {
            //Calculate the change
            int change = 100 - item; 

            //Calculate the amount of quarters
            int quarters = change / 25; 

            //Calculate the amount of dimes
            int dimes = (change % 25) / 10; 

            //Calculate the amount of nickels
            int nickels = ((change % 25) % 10) / 5; 

            //Print the change
            System.out.printf("You bought a item for %d cents and gave me a dollar, so your change is \n%d quarters, \n%d dimes, and \n%d nickels\n", item, quarters, dimes, nickels); 
            break;
            } 
        }
    }       
}
