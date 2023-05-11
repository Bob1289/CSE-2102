package programs;
// Force.java
// This program takes in the mass of 2 objects(INPUT) and distance between centers of the masses(INPUT) and calculates the force(OUTPUT)

public class Force {
    public static void main(String[] args){
        //Set the value of m1 to the first input of mass
        double m1 = Double.parseDouble(args[0]); 

        //Set the value of m2 to the second input of mass
        double m2 = Double.parseDouble(args[1]); 

        //Set the value of r to the input of distance between centers of the masses
        double r = Double.parseDouble(args[2]); 

        //Calculate the force
        double force = (6.67 * Math.pow(10, -11)) * ((m1 * m2) / Math.pow(r, 2)); 

        //Print the force
        System.out.printf("Force is: %.2f\n", force); 
    }
}
