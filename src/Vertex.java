/**
 * Used for vertex objects used in Graph class
 *
 */
public class Vertex
{
    private String station;
    private boolean visited;

    /**
     * Constructor for objects of class Vertex
     */
    public Vertex(String name)
    {
        // initialise instance variables
        this.station = name;
        this.visited = false;
    }

    /**
     * getter for station attribute
     *
     * @return the attribute station (string)
     */
    public String getStation()
    {
        // put your code here
        return station;
    }
    
     /**
     * setter for station attribute
     *
     * @param name new name for the station
     */
    public void setStation(String name)
    {
        this.station = name;
    }
    
    /**
     * toggles whether the station has been visited or not
     */
    public void toggleVisit(){
        this.visited = !this.visited;
    }

    /**
     * getter for visited attribute
     * @return the attribute visited
     */
    public boolean isVisited() {
        return visited;
    }
    
    
}

