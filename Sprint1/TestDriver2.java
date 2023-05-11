import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

public class TestDriver{
    public static void load_file(Database database){
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
        // Read lines from the file until no more are left and add them to the database based on their type, the file has them listed as ID, tvshow/movie, title, director, country, year, rating, duration, genre

        while (fileInput.hasNext()) {
            String line = fileInput.nextLine();
            String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            tokens[7] = tokens[7].replaceAll("[^0-9]","");
            ArrayList<String> director = new ArrayList<String>();
            ArrayList<String> country = new ArrayList<String>();
            ArrayList<String> genre = new ArrayList<String>();

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
            
            if(tokens[1].equalsIgnoreCase("TV Show")){
                TvShow tvshow = new TvShow(tokens[0], tokens[2], director, country, Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), genre);
                database.addMedia(tvshow, "TV Show");
            }
            else if(tokens[1].equalsIgnoreCase("Movie")){
                Movie movie = new Movie(tokens[0], tokens[2], director, country, Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7]), genre);
                database.addMedia(movie, "Movie");
            }
            
        }
    }

    public static void search(Database database){

        Scanner input = new Scanner(System.in);

        System.out.println("Are you searching for a TV show or a Movie?\n");
        String choice = input.nextLine();
        System.out.println("Which attribute are you searching based on?\n 1. Rating \n 2. Director \n 3. Genre \n 4. Duration \n 5. Country \n 6. Year \n (Type number)");
        String attribute = input.nextLine();
        if(attribute.equalsIgnoreCase("1")){
            HashMap<String, ArrayList<entertainment>> rating = new HashMap<String, ArrayList<entertainment>>();
            if(choice.equalsIgnoreCase("TV Show")){
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    if (rating.containsKey(database.getTvshow().get(i).getRating())) {
                        rating.get(database.getTvshow().get(i).getRating()).add(database.getTvshow().get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(database.getTvshow().get(i));
                        rating.put(database.getTvshow().get(i).getRating(), temp);
                    }
                }
            }
            else if(choice.equalsIgnoreCase("Movie")){
                for (int i = 0; i < database.getMovie().size(); i++) {
                    if (rating.containsKey(database.getMovie().get(i).getRating())) {
                        rating.get(database.getMovie().get(i).getRating()).add(database.getMovie().get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(database.getMovie().get(i));
                        rating.put(database.getMovie().get(i).getRating(), temp);
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }
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
        else if(attribute.equalsIgnoreCase("2")){
            HashMap<String, ArrayList<entertainment>> director = new HashMap<String, ArrayList<entertainment>>();
            if(choice.equalsIgnoreCase("TV Show")){
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    for(int j = 0; j < database.getTvshow().get(i).getDirector().size(); j++){
                        if (director.containsKey(database.getTvshow().get(i).getDirector().get(j))) {
                            director.get(database.getTvshow().get(i).getDirector().get(j)).add(database.getTvshow().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getTvshow().get(i));
                            director.put(database.getTvshow().get(i).getDirector().get(j), temp);
                        }
                    }
                }
            }
            else if(choice.equalsIgnoreCase("Movie")){
                for (int i = 0; i < database.getMovie().size(); i++) {
                    for(int j = 0; j < database.getMovie().get(i).getDirector().size(); j++){
                        if (director.containsKey(database.getMovie().get(i).getDirector().get(j))) {
                            director.get(database.getMovie().get(i).getDirector().get(j)).add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            director.put(database.getMovie().get(i).getDirector().get(j), temp);
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }

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
        else if(attribute.equalsIgnoreCase("3")){
            HashMap<String, ArrayList<entertainment>> genre = new HashMap<String, ArrayList<entertainment>>();
            if(choice.equalsIgnoreCase("TV Show")){
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    for(int j = 0; j < database.getTvshow().get(i).getGenre().size(); j++){
                        if (genre.containsKey(database.getTvshow().get(i).getGenre().get(j))) {
                            genre.get(database.getTvshow().get(i).getGenre().get(j)).add(database.getTvshow().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getTvshow().get(i));
                            genre.put(database.getTvshow().get(i).getGenre().get(j), temp);
                        }
                    }
                }
            }
            else if(choice.equalsIgnoreCase("Movie")){
                for (int i = 0; i < database.getMovie().size(); i++) {
                    for(int j = 0; j < database.getMovie().get(i).getGenre().size(); j++){
                        if (genre.containsKey(database.getMovie().get(i).getGenre().get(j))) {
                            genre.get(database.getMovie().get(i).getGenre().get(j)).add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            genre.put(database.getMovie().get(i).getGenre().get(j), temp);
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }

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
        else if(attribute.equalsIgnoreCase("4")){
            if(choice.equalsIgnoreCase("TV Show")){
                HashMap<String, ArrayList<entertainment>> duration = new HashMap<String, ArrayList<entertainment>>();
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    if (duration.containsKey(Integer.toString(database.getTvshow().get(i).getDuration()))) {
                        duration.get(Integer.toString(database.getTvshow().get(i).getDuration())).add(database.getTvshow().get(i));
                    } else {
                        ArrayList<entertainment> temp = new ArrayList<entertainment>();
                        temp.add(database.getTvshow().get(i));
                        duration.put(Integer.toString(database.getTvshow().get(i).getDuration()), temp);
                    }
                }

                System.out.println("Please select one of the unique attributes (Type out the selection)");
                int count = 1;
                for(String key : duration.keySet()){
                    System.out.println(count + ": " + key + " seasons");
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
            else if(choice.equalsIgnoreCase("Movie")){
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
        else if(attribute.equalsIgnoreCase("5")){
            HashMap<String, ArrayList<entertainment>> country = new HashMap<String, ArrayList<entertainment>>();
            if(choice.equalsIgnoreCase("TV Show")){
                for (int i = 0; i < database.getTvshow().size(); i++) {
                    for(int j = 0; j < database.getTvshow().get(i).getCountry().size(); j++){
                        if (country.containsKey(database.getTvshow().get(i).getCountry().get(j))) {
                            country.get(database.getTvshow().get(i).getCountry().get(j)).add(database.getTvshow().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getTvshow().get(i));
                            country.put(database.getTvshow().get(i).getCountry().get(j), temp);
                        }
                    }
                }
            }
            else if(choice.equalsIgnoreCase("Movie")){
                for (int i = 0; i < database.getMovie().size(); i++) {
                    for(int j = 0; j < database.getMovie().get(i).getCountry().size(); j++){
                        if (country.containsKey(database.getMovie().get(i).getCountry().get(j))) {
                            country.get(database.getMovie().get(i).getCountry().get(j)).add(database.getMovie().get(i));
                        } else {
                            ArrayList<entertainment> temp = new ArrayList<entertainment>();
                            temp.add(database.getMovie().get(i));
                            country.put(database.getMovie().get(i).getCountry().get(j), temp);
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }

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
        else if(attribute.equalsIgnoreCase("6")){
            HashMap <String, ArrayList<entertainment>> year = new HashMap<String, ArrayList<entertainment>>();
            for(int i = 0; i < database.getMovie().size(); i++){
                if(year.containsKey(Integer.toString(database.getMovie().get(i).getYear()))){
                    year.get(Integer.toString(database.getMovie().get(i).getYear())).add(database.getMovie().get(i));
                }
                else{
                    ArrayList<entertainment> temp = new ArrayList<entertainment>();
                    temp.add(database.getMovie().get(i));
                    year.put(Integer.toString(database.getMovie().get(i).getYear()), temp);
                }
            }
            for(int i = 0; i < database.getTvshow().size(); i++){
                if(year.containsKey(Integer.toString(database.getTvshow().get(i).getYear()))){
                    year.get(Integer.toString(database.getTvshow().get(i).getYear())).add(database.getTvshow().get(i));
                }
                else{
                    ArrayList<entertainment> temp = new ArrayList<entertainment>();
                    temp.add(database.getTvshow().get(i));
                    year.put(Integer.toString(database.getTvshow().get(i).getYear()), temp);
                }
            }
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

    public static void main(String[] args){

        Database database = new Database();
        boolean running = true;

        load_file(database);

        while(running){
            System.out.println("1. Add a Title \n2. Remove a Title \n3. Search for a Title \n4. Modify a Title \nType Exit to end the program (Type Number)");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if(choice.equalsIgnoreCase("Exit")){
                running = false;
            }
            else if(choice.equalsIgnoreCase("1")){
                // TODO
            }
            else if(choice.equalsIgnoreCase("2")){
                // TODO
            }
            else if(choice.equalsIgnoreCase("3")){
                search(database);
            }
            else if(choice.equalsIgnoreCase("4")){
                // TODO
            }
            else{
                System.out.println("Invalid input");
            }
        }
        
    }
}
