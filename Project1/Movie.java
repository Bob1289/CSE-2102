import java.util.ArrayList;

public class Movie extends entertainment{
    // Instance Variables
    public int duration;

    // Constructor from entertainment class with additional duration variable
    public Movie(String ID, String type, String title, ArrayList<String> director, ArrayList<String> country, int year, String rating, int duration, ArrayList<String> genre) {
        super(ID, type, title, director, country, year, rating, duration, genre);
        this.duration = duration;
    }

    // WriteOutput method to print out the Movie object
    public String writeOutput() {
        return "ID: "+ ID + "\nMovie Title: " + title + "\nMovie Director: " + director + "\nMovie Country: " + country + "\nMovie Year: " + year + "\nMovie Rating: " + rating + "\nMovie Duration: " + duration + "\nMovie Genre: " + genre;
    }

    // Getters and Setters
    public int getDuration() {
        return duration;
    }
}