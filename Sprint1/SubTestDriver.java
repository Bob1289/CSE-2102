import java.util.Scanner;

public class TestDriver{
    public static void main(String[] args){
        // Ask the user if they want to build a TV show or a Movie
        System.out.println("Would you like to build a TV show or a Movie?");
        // Create a scanner object to read in the user's choice
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        // Create a new database
        Database database = new Database();

        // Ask the user for the attributes of the TV show or Movie
        System.out.println("Okay we are going to build a " + choice);
        System.out.println("Please enter a Title:");
        String name = input.nextLine();
        System.out.println("Please enter a Director:");
        String director = input.nextLine();
        System.out.println("Please enter a Country:");
        String country = input.nextLine();
        System.out.println("Please enter a Release year:");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Please enter a Rating:");
        String rating = input.nextLine();
        System.out.println("Please enter a Duration (seasons for shows and minutes for movies):");
        int duration = input.nextInt();
        input.nextLine();
        System.out.println("Please enter a Genre:");
        String genre = input.nextLine();

        // Create the TV show or Movie object depending on the user's choice
        if(choice.equals("TV Show")){
            System.out.println("Here is the created TV Show:\n");
            // Create a new TV show object
            TvShow tvshow = new TvShow(name, director, country, year, rating, duration, genre);
            // Print out the TV show object
            System.out.println(tvshow.writeOutput());
        }
        else if(choice.equals("Movie")){
            System.out.println("Here is the created Movie:\n");
            // Create a new Movie object
            Movie movie = new Movie(name, director, country, year, rating, duration, genre);
            // Print out the Movie object
            System.out.println(movie.writeOutput());
        }
        else{
            System.out.println("Invalid input");
        }

        // Ask the user if they want to change any of the attributes
        System.out.println("Would you like to change any of the attributes?");
        String change = input.nextLine();

        // If the user wants to change an attribute, ask them which attribute they want to change
        if(change.equals("Yes")){
            System.out.println("Which attribute would you like to change?");
            String attribute = input.nextLine();
            // If the user wants to change the title, ask them what they want to change it to
            if(attribute.equals("Title")){
                System.out.println("What would you like to change the title to?");
                String newTitle = input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(newTitle, director, country, year, rating, duration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(newTitle, director, country, year, rating, duration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the director, ask them what they want to change it to
            else if(attribute.equals("Director")){
                System.out.println("What would you like to change the director to?");
                String newDirector = input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, newDirector, country, year, rating, duration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, newDirector, country, year, rating, duration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the country, ask them what they want to change it to
            else if(attribute.equals("Country")){
                System.out.println("What would you like to change the country to?");
                String newCountry = input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, director, newCountry, year, rating, duration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, director, newCountry, year, rating, duration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the year, ask them what they want to change it to
            else if(attribute.equals("Year")){
                System.out.println("What would you like to change the year to?");
                int newYear = input.nextInt();
                input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, director, country, newYear, rating, duration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, director, country, newYear, rating, duration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the rating, ask them what they want to change it to
            else if(attribute.equals("Rating")){
                System.out.println("What would you like to change the rating to?");
                String newRating = input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, director, country, year, newRating, duration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, director, country, year, newRating, duration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the duration, ask them what they want to change it to
            else if(attribute.equals("Duration")){
                System.out.println("What would you like to change the duration to?");
                int newDuration = input.nextInt();
                input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, director, country, year, rating, newDuration, genre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, director, country, year, rating, newDuration, genre);
                    System.out.println(movie.writeOutput());
                }
            }
            // If the user wants to change the genre, ask them what they want to change it to
            else if(attribute.equals("Genre")){
                System.out.println("What would you like to change the genre to?");
                String newGenre = input.nextLine();
                if(choice.equals("TV Show")){
                    TvShow tvshow = new TvShow(name, director, country, year, rating, duration, newGenre);
                    System.out.println(tvshow.writeOutput());
                }
                else if(choice.equals("Movie")){
                    Movie movie = new Movie(name, director, country, year, rating, duration, newGenre);
                    System.out.println(movie.writeOutput());
                }
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }
}
