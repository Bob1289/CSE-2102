
public class Documentary extends Movie {

    // Fields for the TV rating
    private String TV;

    // Constructor
    public Documentary(String name, String MIPAA, String TV) {
        super(name, MIPAA);
        this.TV = TV;
    }

    // Accessor and mutator methods for the TV rating
    public String getTV() {
        return TV;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    // writeOutput() method that prints the values of all the instance variables for a given documentary.
    public void writeOutput() {
        System.out.println("Name: " + getName());
        System.out.println("MIPAA: " + getMIPAA());
        System.out.println("TV: " + getTV());
    }

}