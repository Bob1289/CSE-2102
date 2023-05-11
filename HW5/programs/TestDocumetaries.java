// TestDocumetaries.java
// This class reads data from a file and creates a object for each documentary and documentary series. It also prints the number of documentaries and documentary series, the highest number of seasons and episodes, and the number of documentaries and documentary series for each MIPAA rating and TV rating.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestDocumetaries {
    public static void main(String[] args) throws FileNotFoundException {
        // Prompt for the name of the data file
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String fileName = input.nextLine();

        // Create a File object for the file
        File file = new File(fileName);
        Scanner fileInput = null;

        // Create a Scanner object for the file
        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 

        // Create a hash map to count the mpaa ratings and tv ratings
        HashMap<String, Integer> mpaa_counts = new HashMap<String, Integer>();
        HashMap<String, Integer> tv_counts = new HashMap<String, Integer>();

        // Create counters for the number of documentaries and documentary series
        int documentariesCount = 0;
        int documentariesSeriesCount = 0;

        // Create counters for the highest number of seasons and episodes
        int highestSeasons = 0;
        int highestEpisodes = 0;
        String highestSeasonsName = "";
        String highestEpisodesName = "";
        
        // Read the data from the file line by line
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();

            // Split the line into an array of strings
            String[] data = line.split(",");

            // Create variables for the name, mpaa rating, and tv rating
            String name = data[0];
            String mpaa = data[1].trim();
            String tvRating = data[2].trim();

            // Add the mpaa rating and tv rating to the hash map
            if (mpaa_counts.containsKey(mpaa)) {
                mpaa_counts.put(mpaa, mpaa_counts.get(mpaa) + 1);
            } else {
                mpaa_counts.put(mpaa, 1);
            }

            if (tv_counts.containsKey(tvRating)) {
                tv_counts.put(tvRating, tv_counts.get(tvRating) + 1);
            } else {
                tv_counts.put(tvRating, 1);
            }

            // Create a documentary or documentary series object
            if (data.length == 5) {
                int seasons = Integer.parseInt(data[3].trim());
                int episodes = Integer.parseInt(data[4].trim());
                DocumentarySeries docSeries = new DocumentarySeries(name, mpaa, tvRating, seasons, episodes);

                // Check if the number of seasons or episodes is the highest
                if (seasons > highestSeasons) {
                    highestSeasons = seasons;
                    highestSeasonsName = name;
                }
                if (episodes > highestEpisodes) {
                    highestEpisodes = episodes;
                    highestEpisodesName = name;
                }
                documentariesSeriesCount++;
            } else {
                Documentary doc = new Documentary(name, mpaa, tvRating);
                documentariesCount++;
            }
        }
        // Print the number of documentaries and documentary series, the highest number of seasons and episodes, and the number of documentaries and documentary series for each MIPAA rating and TV rating
        System.out.println("Number of Documentaries: " + documentariesCount + "\nNumber of Documentary Series: " + documentariesSeriesCount);
        for (Object objectName : mpaa_counts.keySet()) 
        {
        System.out.println("Number of " + objectName + " Documentaries: " + mpaa_counts.get(objectName));
        }
        for (Object objectName : tv_counts.keySet()) 
        {
        System.out.println("Number of " + objectName + " Documentaries: " + tv_counts.get(objectName));
        }
        System.out.println(highestSeasonsName + "is the documentary series with the highest number of seasons: - " + highestSeasons);
        System.out.println(highestEpisodesName + "is the documentary series with the highest number of episodes: - " + highestEpisodes);
    }
}