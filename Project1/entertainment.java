import java.util.ArrayList;

public class entertainment {
    // Instance variables
    String title;
    String type;
    ArrayList<String> director;
    ArrayList<String> country;
    int year;
    String rating;
    int duration;
    ArrayList<String> genre;
    String ID;

    // Constructor
    public entertainment(String ID, String type, String title, ArrayList<String> director, ArrayList<String> country, int year, String rating, int duration, ArrayList<String> genre) {
        this.ID = ID;
        this.type = type;
        this.title = title;
        this.director = director;
        this.country = country;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getDirector() {
        return director;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationString() {
        if(type.equalsIgnoreCase("Movie")) {
            return duration + " min";
        } else {
            if (duration == 1) {
                return duration + " Season";
            } else {
                return duration + " Seasons";
            }
        }
    }

    public int setDuration(int duration) {
        return this.duration = duration;
    }

    public String setType(String type) {
        return this.type = type;
    }

    // toString method
    public String writeOutput() {
        return "Title: " + title + " Director: " + director + " Country: " + country + " Year: " + year + " Rating: " + rating + " Genre: " + genre;
    }
}