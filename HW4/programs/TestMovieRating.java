// TestMovieRating.java
// This program tests the Movie class.

import java.util.Scanner;

public class TestMovieRating {
    public static void main(String[] args) {

        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);

        // Get the name of a movie.
        System.out.print("Enter the name of the movie: ");
        String name = input.nextLine();

        // Get the MIPAA rating of the movie.
        System.out.print("Enter the MIPAA rating of the move: ");
        String MIPAA = input.nextLine();

        // Create a Movie object with the data that was entered.
        Movie movie = new Movie(name, MIPAA);

        // Creat a rating variable to hold the user's rating.
        int rating = 0;

        // Create a loop to get the user's ratings until they enter -1.
        while (rating != -1) {
            System.out.print("Enter the rating for the movie (1 to 5, -1 to exit): ");
            rating = input.nextInt();

            // Validate the rating.
            if (rating < -1 || rating > 5) {
                System.out.println("Incorrect rating\n");
            }else if (rating != -1){
                movie.addRating(rating);
            }
        }

        // Display the movie data.
        System.out.println("\nMovie name: " + movie.getName());
        System.out.println("MIPAA rating: " + movie.getMIPAA());
        System.out.printf("Average rating: %.2f\n", movie.getAverage());
    }
}




