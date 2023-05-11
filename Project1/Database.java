import java.util.ArrayList;

public class Database{
    // Create an arraylist of entertainment objects
    public ArrayList<entertainment> mediaList;
    public ArrayList<entertainment> movie;
    public ArrayList<entertainment> tvshow;

    // Constructor
    public Database(){
        mediaList = new ArrayList<entertainment>();
        movie = new ArrayList<entertainment>();
        tvshow = new ArrayList<entertainment>();
    }

    // Getters and Setters
    public void addMedia(entertainment media, String type){
        mediaList.add(media);
        // Add to movie or tvshow arraylist
        if(type.equalsIgnoreCase("Movie")){
            movie.add(media);
        }
        else if(type.equalsIgnoreCase("TV Show")){
            tvshow.add(media);
        }
    }

    public void removeMedia(entertainment media){
        mediaList.remove(media);
    }

    public ArrayList<entertainment> getMediaList() {
        return mediaList;
    }

    public ArrayList<entertainment> getMovie() {
        return movie;
    }

    public ArrayList<entertainment> getTvshow() {
        return tvshow;
    }

    // Print out the database
    public void printDatabase(){
        for(int i = 0; i < mediaList.size(); i++){
            System.out.println(mediaList.get(i).writeOutput());
        }
    }
}

