import java.util.Scanner;
import java.util.ArrayList;

public class TestDriver{
    public static void main(String[] args){

        Database database = new Database();
        int counter_id = 0;

        while(true){
            // Ask the user if they want to build a TV show or a Movie
            System.out.println("Would you like to build a TV show or a Movie?");
            // Create a scanner object to read in the user's choice
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            ArrayList<String> director = new ArrayList<String>();
            ArrayList<String> country = new ArrayList<String>();
            ArrayList<String> genre = new ArrayList<String>();
            // Ask the user for the attributes of the TV show or Movie
            System.out.println("Okay we are going to build a " + choice);
            System.out.println("Please enter a Title:");
            String name = input.nextLine();
            System.out.println("Please enter a Director:");
            director.add(input.nextLine());
            System.out.println("Please enter a Country:");
            country.add(input.nextLine());
            System.out.println("Please enter a Release year:");
            int year = input.nextInt();
            input.nextLine();
            System.out.println("Please enter a Rating:");
            String rating = input.nextLine();
            System.out.println("Please enter a Duration (seasons for shows and minutes for movies):");
            int duration = input.nextInt();
            input.nextLine();
            System.out.println("Please enter a Genre:");
            genre.add(input.nextLine());

            // Create the TV show or Movie object depending on the user's choice
            if(choice.equalsIgnoreCase("TV Show")){
                System.out.println("Here is the created TV Show:\n");
                // Create a new TV show object
                TvShow tvshow = new TvShow(name, director, country, year, rating, duration, genre);
                // Print out the TV show object
                System.out.println(tvshow.writeOutput());

                database.addMedia(tvshow, Integer.toString(counter_id), "TV Show");
            }
            else if(choice.equalsIgnoreCase("Movie")){
                System.out.println("Here is the created Movie:\n");
                // Create a new Movie object
                Movie movie = new Movie(name, director, country, year, rating, duration, genre);
                // Print out the Movie object
                System.out.println(movie.writeOutput());

                database.addMedia(movie, Integer.toString(counter_id), "Movie");
            }
            else{
                System.out.println("Invalid input");
            }

            // Print out the database
            System.out.println("\nHere is the database:\n");
            database.printDatabase();
        }
    }
}
