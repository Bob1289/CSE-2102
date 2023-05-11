// Movie.java
// This class holds data about a movie.

public class Movie {

    // Fields for the movie name, MIPAA rating, and the number of ratings
    private String name;
    private String MIPAA;
    private int terrible;
    private int bad;
    private int ok;
    private int good;
    private int great;
    private int count;

    // Constructor
    public Movie(String name, String MIPAA) {
        this.name = name;
        this.MIPAA = MIPAA;
        terrible = 0;
        bad = 0;
        ok = 0;
        good = 0;
        great = 0;
    }

    // The addRating method accepts an argument that represents a rating and increments the appropriate field.
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            if (rating == 1) {
                terrible++;
            } else if (rating == 2) {
                bad++;
            } else if (rating == 3) {
                ok++;
            } else if (rating == 4) {
                good++;
            } else if (rating == 5) {
                great++;
            }
        }
    }

    // The getAverage method returns the average of the ratings.
    public double getAverage() {
        double average = 0.0;
        int count = terrible + bad + ok + good + great;
        average = (terrible + (bad * 2) + (ok * 3) + (good * 4) + (great * 5)) / count;
        return average;
    }

    // The getName method returns the name of the movie.
    public String getName() {
        return name;
    }

    // The getMIPAA method returns the MIPAA rating of the movie.
    public String getMIPAA() {
        return MIPAA;
    }


}
