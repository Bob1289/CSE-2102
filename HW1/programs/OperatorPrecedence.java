package programs;
// OperatorPrecedence.java
// This program takes in 3 numbers(INPUT) and uses them in a equation to calculate the cube root of a number(OUTPUT)

public class OperatorPrecedence {
    public static void main(String[] args){
        //Set the value of x to the first input
        double x = Double.parseDouble(args[0]);

        //Set the value of y to the second input
        double y = Double.parseDouble(args[1]); 
        
        //Set the value of z to the third input
        double z = Double.parseDouble(args[2]); 
        
        //Plugs in values into equation then cubes it
        double answer = Math.cbrt(Math.pow(x, 2) + Math.pow(y, 2) - Math.abs(z)); 

        //Print the cube root
        System.out.printf("Cube Root is: %.2f\n", answer); 
    }
}
