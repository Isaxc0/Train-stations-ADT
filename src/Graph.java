/**
 * Main graph class
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;

public class Graph implements GraphADT
{

    /**
    * uses adjacency list structure that has a hash map with the key (head) being a vertex
    * the data in the hash map are linked lists of incident edges to the head
    */
    private HashMap<Vertex, LinkedList<Edge>> al; //adjacency list

    /**
     * Constructer
     * @param vertices arraylist containing vertices of the graph
     * @param edges arraylist containging edges of the graph 
     */
    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges)
    {
        this.al = new HashMap<Vertex, LinkedList<Edge>>();
        for (Vertex v: vertices)
        {
            al.put(v, new LinkedList<Edge>()); 
            for (Edge e: edges)
            {
                if(v == e.getEnd1() || v == e.getEnd2()){
                    al.get(v).add(e);
                }
            }
        }
    }
    
    /**
     * Alternative constructor used to create empty graph
     */
    public Graph(){
        this.al = new HashMap<Vertex, LinkedList<Edge>>();
    }
    
    /**
     * inserts a new vertex into the graph
     * @param n name of the new vertex or station
     * @return new station object
     */
    public Vertex insertVertex(String n)
    {
        Vertex newStation = new Vertex(n);
        al.put(newStation, new LinkedList<Edge>());
        return newStation;
    }
    
    /**
     * removes the specified vertex (v) from the graph and removes the edge connecting to it
     * @param v vertex to be removed
     * @return name of station that was removed
     */
    public String removeVertex(Vertex v)
    {
        for (Vertex vertex: al.keySet()){
            for (Edge e: al.get(vertex)){
                if (e.getEnd1() == v || e.getEnd2() == v){
                    al.get(vertex).remove(e); //removes the edge
                }
            }
        }
        al.remove(v); //removes the vertex
        return v.getStation();
    }
    
    /**
     * inserts a new edge with a specified name between two specified vertices
     * @param v vertex end point
     * @param w vertex end point
     * @param n name of edge or train line
     * @return new edge object
     */
    public Edge insertEdge(Vertex v, Vertex w, String n)
    {
        Edge newEdge = new Edge(v,w,n); //creates new edge object
        al.get(v).add(newEdge); //added to the adjacency list
        al.get(w).add(newEdge);
        return newEdge;
    }
    
    /**
     * removes specified edge (e) from the graph
     * @param e edge object to be removed
     * @return name of edge or train line that was removed or null if edge not in the graph
     */
    public String removeEdge(Edge e)
    {
        int edgeCount = 0;
        for (Vertex v: al.keySet()){  //loops through each head in adjacency
            if (al.get(v).contains(e)){
                al.get(v).remove(e);  //removes edge of adjacency list
                edgeCount++;
            }
        }
        if (edgeCount == 0){
            return null; //no edge found
        }
        return e.getLine();
    }
    
    /**
     * returns the opposite vertex to the specified (on other end of edge)
     * @param e the edge connected to v
     * @param v the vertex on one end of e
     * @return the vertex that is adjacent to v and null if edge is not in the graph or v is not connected to e 
     */
    public Vertex opposite(Edge e, Vertex v)
    {
        for (Vertex vertex: al.keySet()){
            if (al.get(vertex).contains(e)){
                if (v == e.getEnd1()){
                    return e.getEnd2();
                }
                else if (v == e.getEnd2()){
                    return e.getEnd1();
                }
            }
        }
        return null;
    }
    
    /**
     * returns a collection of all vertices in the graph
     * @return array list of all vertices in the graph
     */
    public ArrayList<Vertex> vertices()
    {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for(Vertex v: al.keySet()){
            vertices.add(v);
        }
        return vertices;
    }
    
     /**
     * returns a collection of all edges in the graph
     * @return array list of all edges in the graph
     */
    public ArrayList<Edge> edges()
    {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(Vertex v: al.keySet()){
            for(Edge e: al.get(v)){
                if(!edges.contains(e)){
                    edges.add(e);
                }
            }
        }
        return edges;
    }
    
    /**
     * checks whether the two specified vertices are connected via the same edge
     * @param v one endpoint
     * @param w one endpoint
     * @return true if v and w are adjacent or false if they are not adjacent
     */
    public boolean areAdjacent(Vertex v, Vertex w)
    {
        for(Edge e1:al.get(v)){
            for(Edge e2:al.get(w)){
                if(e1 == e2){
                    return true;  //if both vertices have the edge in their linked list
                }
            }
        }
        return false;
    }
    
    /**
     * returns iterable collection of edges that are incident to v
     * @param v the vertex
     * @return array list of edges incident to v
     */
    public ArrayList<Edge> incidentEdges(Vertex v)
    {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(Edge e: al.get(v))
        {
            edges.add(e);
        }
        return edges;
    }
    
    /**
     * renames the vertex v to the name n and returns its old name
     * @param v the vertex
     * @param n new name
     * @return its old name
     */
    public String rename(Vertex v, String n)
    {
        String oldName = v.getStation();
        v.setStation(n);
        return oldName;
    }
    
     /**
     * renames the edge e to the name n and returns its old name
     * @param e the edge
     * @param n new name
     * @return its old name
     */
    public String rename(Edge e, String n)
    {
        String oldName = e.getLine();
        e.setLine(n);
        return oldName;
    }
    
    
    /**
     * performs breadth-first traversal of the rail network starting from given station (v)
     * @param v starting station
     */
    public void bftraverse(Vertex v){
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(v);
        v.toggleVisit();
        while(!queue.isEmpty()){
            Vertex removed = queue.remove(); //removed from front of queue 
            for(Edge e: al.get(removed)){
                Vertex toAdd = opposite(e,removed); //get opposite node
                if(!toAdd.isVisited()){
                    queue.add(toAdd); //add visited node to back of queue
                    toAdd.toggleVisit(); //visit node
                }
            }
        }
        
        for(Vertex vertex: vertices()){ //sets all vertices to unvisted
            vertex.toggleVisit();
        }
    }
    
    /**
     * performs breadth-first traversal of the whole rail network including non connected graphs
     */
    public void bftraverse(){
        Queue<Vertex> queue = new LinkedList<Vertex>();
        ArrayList<Vertex> output = new ArrayList<Vertex>();
        ArrayList<Vertex> vertices = vertices();
        Vertex start = vertices.get(0);
        queue.add(start);
        start.toggleVisit();
        while(output.size() < vertices.size()){
            Vertex removed = queue.remove(); 
            output.add(removed);//add to output
            for(Edge e: al.get(removed)){
                Vertex toAdd = opposite(e,removed);
                if(!toAdd.isVisited()){
                    queue.add(toAdd);
                    toAdd.toggleVisit();
                }
            }
            //if not all nodes have been visited but the queue is empty
            //this means that the graph is non connected
            if(queue.isEmpty() && output.size() < vertices.size()){ 
                for(Vertex v: vertices){
                    if(!v.isVisited()){
                        queue.add(v);
                        v.toggleVisit();
                        break;
                    }
                }
            }
        }
        for(Vertex vertex: vertices){
            vertex.toggleVisit();
        }
    }
    
    /**
     * returns array list of all reachable stations from v
     * @param v starting station
     * @return array list of reachable stations
     */
    
    public ArrayList<Vertex> allReachable(Vertex v){
        //uses slightly modified breadth-first traversal
        ArrayList<Vertex> reachable = new ArrayList<Vertex>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(v);
        v.toggleVisit();
        while(!queue.isEmpty()){
            Vertex removed = queue.remove();
            reachable.add(removed);
            for(Edge e: al.get(removed)){
                Vertex toAdd = opposite(e,removed);
                if(!toAdd.isVisited()){
                    queue.add(toAdd);
                    toAdd.toggleVisit();
                }
            }
        }
        return reachable;
    }

    /**
     * checks whether all nodes can be reached from one another
     * @return true if all stations can be reached in whole rail network and false if not 
     */
    public boolean allConnected(){
        //I would have used the bftraversal(v) method but it has to be void, therefore code has to be repeated
        Queue<Vertex> queue = new LinkedList<Vertex>();
        ArrayList<Vertex> output = new ArrayList<Vertex>();
        ArrayList<Vertex> vertices = vertices();
        Vertex start = vertices.get(0);
        queue.add(start);
        start.toggleVisit();
        while(output.size() < vertices.size()){
            Vertex removed = queue.remove();
            output.add(removed);
            for(Edge e: al.get(removed)){
                Vertex toAdd = opposite(e,removed);
                if(!toAdd.isVisited()){
                    queue.add(toAdd);
                    toAdd.toggleVisit();
                }
            }
            if(queue.isEmpty() && output.size() < vertices.size()){
                return false;
            }
        }
        return true;
    }
    
     /**
     * finds the most direct route between the two vertices v and u
     * @para v start vertex
     * @para u destination vertex
     * @return array of edges that are the shortest route 
     */   
    public ArrayList<Edge> mostDirectRoute(Vertex u, Vertex v){
        Queue<Vertex> queue = new LinkedList<Vertex>();
        ArrayList<Edge> output = new ArrayList<Edge>();
        queue.add(v);
        v.toggleVisit();
        while(!queue.isEmpty()){
            Vertex removed = queue.remove();
            if(removed == u){
                break;
            }
            for(Edge e: al.get(removed)){
                Vertex toAdd = opposite(e,removed);
                if(!toAdd.isVisited()){
                    output.add(e);
                    queue.add(toAdd);
                    toAdd.toggleVisit();
                }
            }
        }
        for(Vertex vertex: vertices()){
            vertex.toggleVisit();
        }
        return output;
    }
}
