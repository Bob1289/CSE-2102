
public class DocumentarySeries extends Documentary{
    // Fields for the number of seasons and episodes
    public int seasons;
    public int episodes;

    // Constructor
    public DocumentarySeries(String name, String MIPAA, String TV, int seasons, int episodes){
        super(name, MIPAA, TV);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    // Accessor and mutator methods for the number of seasons and episodes
    public int getSeasons(){
        return seasons;
    }

    public void setSeasons(int seasons){
        this.seasons = seasons;
    }

    public int getEpisodes(){
        return episodes;
    }

    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }

    // writeOutput() method that prints the values of all the instance variables for a given documentary series.
    public void writeOutput(){
        System.out.println("Name: " + getName());
        System.out.println("MIPAA: " + getMIPAA());
        System.out.println("TV: " + getTV());
        System.out.println("Seasons: " + getSeasons());
        System.out.println("Episodes: " + getEpisodes());
    }

}