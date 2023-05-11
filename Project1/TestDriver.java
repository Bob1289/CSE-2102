import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

public class TestDriver{
    public static void save_file(Database database, String fileName){
        // This method saves the database to a file
        try {
            // Creates a file with the name the user inputted
            FileOutputStream file = new FileOutputStream(fileName);
            // Writes the header to the file
            file.write(("Show_id,Type,Title,Director,Country,Release_year,Rating,Duration,Genre\n").getBytes());
            // Loops through the database and writes each media to the file
            for(int i = 0; i < database.getMediaList().size(); i++){
                file.write((database.getMediaList().get(i).getID() + "," + database.getMediaList().get(i).getType() + "," + database.getMediaList().get(i).getTitle() + "," + "\"" + String.join(", ",database.getMediaList().get(i).getDirector()) + "\"" + "," + "\"" + String.join(", ", database.getMediaList().get(i).getCountry()) + "\"" + "," + database.getMediaList().get(i).getYear() + "," + database.getMediaList().get(i).getRating() + "," + database.getMediaList().get(i).getDurationString() + "," + "\"" + String.join(", ", database.getMediaList().get(i).getGenre()) + "\"" + "\n").getBytes());
            }
            // Closes the file
            file.close();
        } catch (Exception e) {
            // Prints out error if there is an error
            System.out.println("Error");
        }
    }

    public static String load_file(Database database){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        // Get the file name.
        String fileName = input.nextLine();
        // Create a File object for the file.
        File file = new File(fileName);
        // Create a Scanner object for the file.
        Scanner fileInput = null;
        // Open the file.
        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }  
        // Read lines from the file until no more are left and add them to the database based on their type
        while (fileInput.hasNext()) {
            // Sets the line to the line variable
            String line = fileInput.nextLine();
            // Splits the line into tokens based on the comma and ignores commas within quotes
            String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            // Removes anything but the numbers from the year
            tokens[7] = tokens[7].replaceAll("[^0-9]","");
            // Makes a new arraylist for the director, country, and genre so it can be dynamically sized
            ArrayList<String> director = new ArrayList<String>();
            ArrayList<String> country = new ArrayList<String>();
            ArrayList<String> genre = new ArrayList<String>();
            // Splits the director, country, and genre into arrays then adds them to the arraylist while removing the quotes and trimming the whitespace
            String[] directors = tokens[3].split(",");
            for(int i = 0; i < directors.length; i++){
                directors[i] = directors[i].replaceAll("^\"|\"$", "").trim();
                director.add(directors[i]);
            }

            String[] countries = tokens[4].split(",");
            for(int i = 0; i < countries.length; i++){
                countries[i] = countries[i].replaceAll("^\"|\"$", "").trim();
                country.add(countries[i]);
            }
            
            String[] genres = tokens[8].split(",|&");
            for(int i = 0; i < genres.length; i++){
                genres[i] = genres[i].replaceAll("^\"|\"$", "").trim();
                genre.add(genres[i]);
            }
            // Adds the media to the database based on the type
            if(tokens[1].equalsIgnoreCase("TV Show")){
                TvShow tvshow = new TvShow(tokens[0], tokens[1], tokens[2], director, country, Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), genre);
                database.addMedia(tvshow, "TV Show");
            }
            else if(tokens[1].equalsIgnoreCase("Movie")){
                Movie movie = new Movie(tokens[0], tokens[1], tokens[2], director, country, Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), genre);
                database.addMedia(movie, "Movie");
            }
        }

        return fileName;
    }

    public static void search(Database database){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        // Create an arraylist for the type of media the user is searching for either a tv show or movie
        ArrayList<entertainment> type_entertain = new ArrayList<entertainment>();
        // Asks the user if they are searching for a tv show or movie and adds the corresponding arraylist to the type_entertain arraylist from the database
        System.out.println("Are you searching for a TV show or a Movie?\n");
        String choice = input.nextLine();
        if(choice.equalsIgnoreCase("TV Show")){
            type_entertain = database.getTvshow();
        }
        else if(choice.equalsIgnoreCase("Movie")){
            type_entertain = database.getMovie();
        }
        else{
            System.out.println("Invalid input");
        }
        // Asks the user what attribute they are searching for and then searches the database based on the attribute
        System.out.println("Which attribute are you searching based on?\n 1. Rating \n 2. Director \n 3. Genre \n 4. Duration \n 5. Country \n 6. Year \n (Type number)");
        String attribute = input.nextLine();
        // Searches the database based on the rating
        if(attribute.equalsIgnoreCase("1")){
            // Creates a hashmap for the rating with the key being the rating and the value being an arraylist of the media with that rating
            HashMap<String, ArrayList<entertainment>> rating = new HashMap<String, ArrayList<entertainment>>();
            // Adds the media to the hashmap based on the rating
            for (int i = 0; i < type_entertain.size(); i++) {
                if (rating.containsKey(type_entertain.get(i).getRating())) {
                    rating.get(type_entertain.get(i).getRating()).add(type_entertain.get(i));
                } else {
                    ArrayList<entertainment> temp = new ArrayList<entertainment>();
                    temp.add(type_entertain.get(i));
                    rating.put(type_entertain.get(i).getRating(), temp);
                }
            }
            // Asks the user what rating they are searching for and then prints out the media with that rating
            System.out.println("Please select one of the unique attributes (Type out the selection)");
            int count = 1;
            for(String key : rating.keySet()){
                System.out.println(count + ": " + key);
                count++;
            }
            String ratingChoice = input.nextLine();
            if(rating.containsKey(ratingChoice)){
                for(int i = 0; i < rating.get(ratingChoice).size(); i++){
                    System.out.println(rating.get(ratingChoice).get(i).getTitle());
                    System.out.println("--------------------------------------------");
                }
            }
            else{
                System.out.println("No results found");
            }
        }
        // Searches the database based on the director
        else if(attribute.equalsIgnoreCase("2")){
            // Creates a hashmap for the director with the key being the director and the value being an arraylist of the media with that director
            HashMap<String, ArrayList<entertainment>> director = new HashMap<String, ArrayList<entertainment>>();
            // Adds the media to the hashmap based on the director
            for (int i = 0; i < type_entertain.size(); i++) {
                for(int j = 0; j < type_entertain.get(i).getDirector().size(); j++){
                    if (director.containsKey(type_entertain.get(i).getDirector().get(j))) {
                        director.get(type_entertain.get(i).getDirector().get(j)).add(type_entertain.get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(type_entertain.get(i));
                        director.put(type_entertain.get(i).getDirector().get(j), temp);
                    }
                }
            }
            // Asks the user what director they are searching for and then prints out the media with that director
            System.out.println("Please select one of the unique attributes (Type out the selection)");
            int count = 1;
            for(String key : director.keySet()){
                if (key.length() > 0){
                System.out.println(count + ": " + key);
                count++;}
            }
            String directorChoice = input.nextLine();
            if(director.containsKey(directorChoice)){
                for(int i = 0; i < director.get(directorChoice).size(); i++){
                    System.out.println(director.get(directorChoice).get(i).getTitle());
                    System.out.println("--------------------------------------------");
                }
            }
            else{
                System.out.println("No results found");
            }
            
        }
        // Searches the database based on the genre
        else if(attribute.equalsIgnoreCase("3")){
            // Creates a hashmap for the genre with the key being the genre and the value being an arraylist of the media with that genre
            HashMap<String, ArrayList<entertainment>> genre = new HashMap<String, ArrayList<entertainment>>();
            // Adds the media to the hashmap based on the genre
            for (int i = 0; i < type_entertain.size(); i++) {
                for(int j = 0; j < type_entertain.get(i).getGenre().size(); j++){
                    if (genre.containsKey(type_entertain.get(i).getGenre().get(j))) {
                        genre.get(type_entertain.get(i).getGenre().get(j)).add(type_entertain.get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(type_entertain.get(i));
                        genre.put(type_entertain.get(i).getGenre().get(j), temp);
                    }
                }
            }
            // Asks the user what genre they are searching for and then prints out the media with that genre
            System.out.println("Please select one of the unique attributes (Type out the selection)");
            int count = 1;
            for(String key : genre.keySet()){
                System.out.println(count + ": " + key);
                count++;
            }
            String genreChoice = input.nextLine();
            if(genre.containsKey(genreChoice)){
                for(int i = 0; i < genre.get(genreChoice).size(); i++){
                    System.out.println(genre.get(genreChoice).get(i).getTitle());
                    System.out.println("--------------------------------------------");
                }
            }
            else{
                System.out.println("No results found");
            }
        }
        // Searches the database based on the duration
        else if(attribute.equalsIgnoreCase("4")){
            if(choice.equalsIgnoreCase("TV Show")){
                // Creates a hashmap for the duration with the key being the duration and the value being an arraylist of the media with that duration for tv shows
                HashMap<String, ArrayList<entertainment>> duration = new HashMap<String, ArrayList<entertainment>>();
                // Adds the media to the hashmap based on the duration
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    if (duration.containsKey(Integer.toString(database.getTvshow().get(i).getDuration()) + " seasons")) {
                        duration.get(Integer.toString(database.getTvshow().get(i).getDuration()) + " seasons").add(database.getTvshow().get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(database.getTvshow().get(i));
                        duration.put(Integer.toString(database.getTvshow().get(i).getDuration()) + " seasons", temp);
                    }
                }
                // Asks the user what duration they are searching for and then prints out the media with that duration
                System.out.println("Please select one of the unique attributes (Type out the selection)");
                int count = 1;
                for(String key : duration.keySet()){
                    System.out.println(count + ": " + key);
                    count++;
                }
                String durationChoice = input.nextLine();
                if(duration.containsKey(durationChoice)){
                    for(int i = 0; i < duration.get(durationChoice).size(); i++){
                        System.out.println(duration.get(durationChoice).get(i).getTitle());
                        System.out.println("--------------------------------------------");
                    }
                }
                else{
                    System.out.println("No results found");
                }
            }
            // Case for movies
            else if(choice.equalsIgnoreCase("Movie")){
                // Creates a hashmap for the duration with the key being the duration and the value being an arraylist of the media with that duration for movies
                HashMap<String, ArrayList<entertainment>> duration = new HashMap<String, ArrayList<entertainment>>();
                // Split each movie up in 30 minute intervals in the hashmap
                for (int i = 0; i < database.getMovie().size(); i++) {
                    if(database.getMovie().get(i).getDuration() <= 30) {
                        if (duration.containsKey("0-30 minutes")) {
                            duration.get("0-30 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("0-30 minutes", temp);
                        }
                    }
                    else if(database.getMovie().get(i).getDuration() <= 60){
                        if (duration.containsKey("31-60 minutes")) {
                            duration.get("31-60 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("31-60 minutes", temp);
                        }
                    }
                    else if(database.getMovie().get(i).getDuration() <= 90){
                        if (duration.containsKey("61-90 minutes")) {
                            duration.get("61-90 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("61-90 minutes", temp);
                        }
                    }
                    else if(database.getMovie().get(i).getDuration() <= 120){
                        if (duration.containsKey("91-120 minutes")) {
                            duration.get("91-120 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("91-120 minutes", temp);
                        }
                    }
                    else if(database.getMovie().get(i).getDuration() <= 150){
                        if (duration.containsKey("121-150 minutes")) {
                            duration.get("121-150 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("121-150 minutes", temp);
                        }
                    }
                    else if(database.getMovie().get(i).getDuration() <= 180){
                        if (duration.containsKey("151-180 minutes")) {
                            duration.get("151-180 minutes").add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            duration.put("151-180 minutes", temp);
                        }   
                    }
                }
                // Asks the user what duration they are searching for and then prints out the media with that duration
                System.out.println("Please select one of the unique attributes (Type out the selection) \nPlease pick a range");
                int count = 1;
                for(String key : duration.keySet()){
                    System.out.println(count + ": " + key);
                    count++;
                }
                String durationChoice = input.nextLine();
                if(duration.containsKey(durationChoice)){
                    for(int i = 0; i < duration.get(durationChoice).size(); i++){
                        System.out.println(duration.get(durationChoice).get(i).getTitle());
                        System.out.println("--------------------------------------------");
                    }
                }
                else{
                    System.out.println("No results found");
                }
            }
            else{
                System.out.println("Invalid input");
            }

        }
        // Searches the database based on country
        else if(attribute.equalsIgnoreCase("5")){
            // Creates a hashmap for the country with the key being the country and the value being an arraylist of the media with that country
            HashMap<String, ArrayList<entertainment>> country = new HashMap<String, ArrayList<entertainment>>();
            // Adds each media to the hashmap based on the country
            for (int i = 0; i < type_entertain.size(); i++) {
                for(int j = 0; j < type_entertain.get(i).getCountry().size(); j++){
                    if (country.containsKey(type_entertain.get(i).getCountry().get(j))) {
                        country.get(type_entertain.get(i).getCountry().get(j)).add(type_entertain.get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(type_entertain.get(i));
                        country.put(type_entertain.get(i).getCountry().get(j), temp);
                    }
                }
            }
            // Asks the user what country they are searching for and then prints out the media with that country
            System.out.println("Please select one of the unique attributes (Type out the selection)");
            int count = 1;
            for(String key : country.keySet()){
                if(key.length() > 1){
                System.out.println(count + ": " + key);
                count++;}
            }
            String countryChoice = input.nextLine();
            if(country.containsKey(countryChoice)){
                for(int i = 0; i < country.get(countryChoice).size(); i++){
                    System.out.println(country.get(countryChoice).get(i).getTitle());
                    System.out.println("--------------------------------------------");
                }
            }
            else{
                System.out.println("No results found");
            }
            
        }
        // Searches the database based on year
        else if(attribute.equalsIgnoreCase("6")){
            // Creates a hashmap for the year with the key being the year and the value being an arraylist of the media with that year
            HashMap <String, ArrayList<entertainment>> year = new HashMap<String, ArrayList<entertainment>>();
            // Adds each media to the hashmap based on the year
            for(int i = 0; i < type_entertain.size(); i++){
                if(year.containsKey(Integer.toString(type_entertain.get(i).getYear()))){
                    year.get(Integer.toString(type_entertain.get(i).getYear())).add(type_entertain.get(i));
                }
                else{
                    ArrayList<entertainment> temp = new ArrayList<entertainment>();
                    temp.add(type_entertain.get(i));
                    year.put(Integer.toString(type_entertain.get(i).getYear()), temp);
                }
            }
            // Asks the user what year they are searching for and then prints out the media with that year
            System.out.println("Please select one of the unique attributes (Type out the selection)");
            int count = 1;
            for(String key : year.keySet()){
                if(key != null){
                    System.out.println(count + ": " + key);
                    count++;
                }
            }
            String yearChoice = input.nextLine();
            if(year.containsKey(yearChoice)){
                for(int i = 0; i < year.get(yearChoice).size(); i++){
                    System.out.println(year.get(yearChoice).get(i).getTitle());
                    System.out.println("---------------------------------");
                }
            }
            else{
                System.out.println("No results found");
            }
        }
        else{
            System.out.println("Invalid input");
        }
        
    }

    public static void delete(Database database){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        // Make a array list of the media of combined Movie and TV show withe Movie first
        ArrayList<entertainment> type_entertain = new ArrayList<entertainment>();
        for(int i = 0; i < database.getMovie().size(); i++){
            type_entertain.add(database.getMovie().get(i));
        }
        for(int i = 0; i < database.getTvshow().size(); i++){
            type_entertain.add(database.getTvshow().get(i));
        }
        // Creates a array list of the media with the same title
        ArrayList<entertainment> temp2 = new ArrayList<entertainment>();

        // Asks the user what they want to delete and then deletes it
        System.out.println("Please select one of the unique attributes (Type out the selection)");
        // Holds the count of the media
        int count = 1;
        // Loops through the array list of the media and prints out the media
        for(int i = 0; i < type_entertain.size(); i++){

            System.out.println(count + ": " + type_entertain.get(i).getTitle());
            temp2.add(type_entertain.get(i));
            count++;
            // Prints out the media in groups of 10 and then asks the user if they want to see more or delete a media
            if(count == 11 || i == type_entertain.size() - 1){ 
                // Asks the user if they want to see more or delete a media
                System.out.println("Press space bar and enter to see more or Type the number of the media you want to delete or type exit to exit");
                String choice = input.nextLine();
                // If the user wants to see more then it clears the temp2 array list and resets the count
                if(choice.equalsIgnoreCase(" ") && i < type_entertain.size() - 1){
                    count = 1;
                    temp2.clear();
                }
                // If the user wants to exit then it exits the method
                else if(choice.equalsIgnoreCase("exit")){
                    return;
                }
                else{
                    // If the user wants to delete a media then it deletes the media and exits the method
                    int choiceInt = Integer.parseInt(choice);
                    if(choiceInt > 0 && choiceInt < 11){
                        if(temp2.get(choiceInt - 1) instanceof Movie){
                            database.getMovie().remove(temp2.get(choiceInt - 1));
                            database.getMediaList().remove(temp2.get(choiceInt - 1));
                            System.out.println("Deleted " + (temp2.get(choiceInt - 1)).getTitle());
                            return;
                        }
                        else{
                            database.getTvshow().remove((temp2.get(choiceInt - 1)));
                            database.getMediaList().remove((temp2.get(choiceInt - 1)));
                            System.out.println("Deleted " + (temp2.get(choiceInt - 1)).getTitle());
                            return;
                        }
                    }
                    else{
                        System.out.println("Invalid input");
                    }
                }
            }
    
        }
        
    }

    public static void add(Database database){
        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        // Asks the user what type of media they want to add
        System.out.println("Enter the type you want to add (Movie or TV Show)");
        // Holds the type of media
        String type = input.nextLine();
        System.out.println("Enter the title");
        // Holds the title of the media
        String title = input.nextLine();
        System.out.println("Enter the director (Seperate by commas))");
        // Holds the director of the media
        String director = input.nextLine();
        String[] directorArray = director.split(",");
        ArrayList<String> directorList = new ArrayList<String>();
        // Adds the directors to the directorList
        for(int i = 0; i < directorArray.length; i++){
            directorList.add(directorArray[i]);
        }
        // Holds the country of the media
        System.out.println("Enter the country(Seperate by commas)");
        String country = input.nextLine();
        String[] countryArray = country.split(",");
        ArrayList<String> countryList = new ArrayList<String>();
        // Adds the country to the array list
        for(int i = 0; i < countryArray.length; i++){
            countryList.add(countryArray[i]);
        }
        // Holds the year of the media
        System.out.println("Enter the release year");
        int year = input.nextInt();
        input.nextLine();
        // Holds the rating of the media
        System.out.println("Enter the rating");
        String rating = input.nextLine();
        // Holds the duration of the media
        System.out.println("Enter the duration");
        int duration = input.nextInt();
        input.nextLine();
        // Holds the genres of the media
        System.out.println("Enter the genres(seperate by commas)");
        String genres = input.nextLine();
        // Splits the genres by commas and adds them to the array list
        String[] genresArray = genres.split(",");
        ArrayList<String> genresList = new ArrayList<String>();
        // Adds the genres to the array list
        for(int i = 0; i < genresArray.length; i++){
            genresList.add(genresArray[i]);
        }
        // Adds the media to the database
        if(type.equalsIgnoreCase("Movie")){
            Movie movie = new Movie("s"+String.valueOf(database.getMediaList().size()), "Movie", title, directorList, countryList, year, rating, duration, genresList);
            database.addMedia(movie, "Movie");
        }
        // Adds the media to the database
        else if(type.equalsIgnoreCase("TV Show")){
            TvShow tvshow = new TvShow("s"+String.valueOf(database.getMediaList().size()), "TV Show", title, directorList, countryList, year, rating, duration, genresList);
            database.addMedia(tvshow, "TV Show");
        }
        else{
            System.out.println("Invalid input");
        }
    }

    public static void modify(Database database){

        // Create a Scanner object for keyboard input.
        Scanner input = new Scanner(System.in);
        // Make a array list of the media of combined Movie and TV show withe Movie first
        ArrayList<entertainment> type_entertain = new ArrayList<entertainment>();
        for(int i = 0; i < database.getMovie().size(); i++){
            type_entertain.add(database.getMovie().get(i));
        }
        for(int i = 0; i < database.getTvshow().size(); i++){
            type_entertain.add(database.getTvshow().get(i));
        }
        // Creates a array list of the media with the same title
        ArrayList<entertainment> temp2 = new ArrayList<entertainment>();

        // Asks the user what they want to delete and then deletes it
        System.out.println("The list of media is:");
        // Holds the count of the media
        int count = 1;
        // Loops through the array list of the media and prints out the media
        for(int i = 0; i < type_entertain.size(); i++){

            System.out.println(count + ": " + type_entertain.get(i).getTitle());
            temp2.add(type_entertain.get(i));
            count++;
            // Prints out the media in groups of 10 and then asks the user if they want to see more or delete a media
            if(count == 11 || i == type_entertain.size() - 1){ 
                // Asks the user if they want to see more or delete a media
                System.out.println("Press space bar and enter to see more or Type the number of the media you want to delete or type exit to exit");
                String choice = input.nextLine();
                // If the user wants to see more then it clears the temp2 array list and resets the count
                if(choice.equalsIgnoreCase(" ") && i < type_entertain.size() - 1){
                    count = 1;
                    temp2.clear();
                }
                // If the user wants to exit then it exits the method
                else if(choice.equalsIgnoreCase("exit")){
                    return;
                }
                else{
                    // If the user wants to modify a media then it modifies the media and exits the method
                    int choiceInt = Integer.parseInt(choice);
                    if(choiceInt > 0 && choiceInt < 11){
                        if(temp2.get(choiceInt - 1) instanceof Movie){
                            System.out.println("Enter the new rating for the movie");
                            String newRating = input.nextLine();
                            database.getMovie().remove(temp2.get(choiceInt - 1));
                            database.getMediaList().remove(temp2.get(choiceInt - 1));
                            temp2.get(choiceInt - 1).setRating(newRating);
                            database.addMedia(temp2.get(choiceInt - 1), "Movie");
                            return;
                        }
                        else{
                            System.out.println("Enter the new rating for the TV show");
                            String newRating = input.nextLine();
                            database.getTvshow().remove((temp2.get(choiceInt - 1)));
                            database.getMediaList().remove((temp2.get(choiceInt - 1)));
                            temp2.get(choiceInt - 1).setRating(newRating);
                            database.addMedia(temp2.get(choiceInt - 1), "TV Show");
                            return;
                        }
                    }
                    else{
                        System.out.println("Invalid input");
                    }
                }
            }
    
        }

    }

    public static void main(String[] args){
        // Creates a new database
        Database database = new Database();
        // Creates a running variable to keep the program running
        boolean running = true;
        // Loads values into the database
        String name = load_file(database);
        // Loops through the program until the user types exit
        while(running){
            // Asks the user what they want to do
            System.out.println("1. Add a Title \n2. Remove a Title \n3. Search for a Title \n4. Modify a Title \nType Exit to end the program (Type Number)");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("Exit")){
                running = false;
            }
            else if(choice.equalsIgnoreCase("1")){
                add(database);
            }
            else if(choice.equalsIgnoreCase("2")){
                delete(database);
            }
            else if(choice.equalsIgnoreCase("3")){
                search(database);
            }
            else if(choice.equalsIgnoreCase("4")){
                modify(database);
            }
            else{
                System.out.println("Invalid input");
            }
            save_file(database, name);
        }
    }
}
