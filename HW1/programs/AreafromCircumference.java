package programs;
//AreafromCircumference.java
//This program takes in the circumference(INPUT) and calculates the area of a circle(OUTPUT)

public class AreafromCircumference{
    public static void main(String[] args){
        //Set the value of pi to 22/7
        double pi = 22/7; 

        //Set the value of circumference to the input
        double circumference = Double.parseDouble(args[0]); 

        //Calculate the area of the circle
        double area = (circumference * circumference) / (4 * pi); 
        
        //Print the area of the circle
        System.out.printf("Area of circle is: %.2f\n", area); 
    }
}