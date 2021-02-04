/**
 * Used for edge objects used in Graph class
 */
public class Edge
{
    private Vertex end1;
    private Vertex end2;
    private String line;

    /**
     * Constructor for objects of class Edge
     * 
     * @param endpoint1 one of the two vertices that the edge is connected to (Vertex object)
     * @param endpoint2 one of the two vertices that the edge is connected to (Vertex object)
     * @param name the name of the train line connecting the stations or end points
     */
    public Edge(Vertex endpoint1, Vertex endpoint2, String name)
    {
        // initialise instance variables
        this.end1 = endpoint1;
        this.end2 = endpoint2;
        this.line = name;
    }

    /**
     * getter for line attribute
     *
     * @return the attribute line (string)
     */
    public String getLine()
    {
        // put your code here
        return line;
    }
    
    /**
     * getter for end1 attribute
     *
     * @return the attribute end1 (Edge object)
     */
    public Vertex getEnd1()
    {
        return end1;
    }
    
    /**
     * getter for end2 attribute
     *
     * @return the attribute end2 (Edge object)
     */
    public Vertex getEnd2()
    {
        return end2;
    }
    
     /**
     * setter for line attribute
     * 
     * @param name new name for the train line
     */
    public void setLine(String name)
    {
        this.line = name;
    }
    /**
     * setter for line attribute
     * 
     * @param end1 new vertex null if no vertex
     */
    public void setEnd1(Vertex end1) {
        this.end1 = end1;
    }
    /**
     * setter for line attribute
     * 
     * @param end2 new vertex null if no vertex
     */
    public void setEnd2(Vertex end2) {
        this.end2 = end2;
    }
    
    
}
