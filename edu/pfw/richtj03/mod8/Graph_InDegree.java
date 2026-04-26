package mod8;

import java.io.File;
import java.io.IOException;
import java.util.*;

//Directed Graph implemented with an adjacency list that tracks vertex in-degree
public class Graph_InDegree<V extends Comparable<V>> {
    class Vertex {
        V data;
        ArrayList<V> neighbors;
        int inDegree;

        Vertex(V data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
            this.inDegree = 0;
        }
    }

    private HashMap<V, Vertex> adjacencyList;

    public Graph_InDegree() {
        adjacencyList = new HashMap<>(100);
    }

    public void addVertex(V vertex) {
        adjacencyList.putIfAbsent(vertex, new Vertex(vertex));
    }

    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);
        // Link neighbor from -> to
        adjacencyList.get(from).neighbors.add(to);
        // Update in-degree from incoming edge
        adjacencyList.get(to).inDegree++;
    }

    public ArrayList<V> getNeighbors(V vertex) {
        return adjacencyList.get(vertex).neighbors;
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph adjacency list:\n");
        for (V vertex : getVertices()) {
            // TODO
            sb.append(vertex).append(" -> ").append(getNeighbors(vertex)).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<V> kahnTopoSort() {
        ArrayList<V> sortedList = new ArrayList<>();
        Stack<V> zeroStack = new Stack<>(); // Holds vertices with in-degree 0
        // Initialize the stack with vertices of in-degree 0
        
        
        
        // Process vertices with in-degree 0
        for(;;) {
        
            // Add to sorted list
            
            // Decrease in-degree of neighbors
            
                
                // If in-degree becomes 0, push to zeroStack        
                if(true) {

                }
        }

        // Check for cycles
        if (sortedList.size() != adjacencyList.size()) {
            // Sort fails
            throw new IllegalStateException("Graph has cycles, topological sort not possible.");
        }
        // Successful sort
        return sortedList;
    }

    public static void main(String[] args) throws IOException {
        // Prerequisites graph

        // Read the file and add edges

        // Print the unsorted graph

        // Topologically sort and print the graph

    }
}
