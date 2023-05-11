import java.util.ArrayList;

public class TvShow extends entertainment{
    // Instance Variables
    public int duration;

    // Constructor from entertainment class with additional seasons variable
    public TvShow(String ID, String type, String title, ArrayList<String> director, ArrayList<String> country, int year, String rating, int duration, ArrayList<String> genre) {
        super(ID, type, title, director, country, year, rating, duration, genre);
        this.duration = duration;
    }

    // WriteOutput method to print out the TvShow object
    public String writeOutput() {
        return "ID: "+ ID + "\nTv Show Title: " + title + "\nTv Show Director: " + director + "\nTv Show Country: " + country + "\nTv Show Year: " + year + "\nTv Show Rating: " + rating + "\nTv Show Duration: " + duration + "\nTv Show Genre: " + genre;
    }

    // Getters and Setters
    public int getDuration() {
        return duration;
    }
}