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
        for (V v : adjacencyList.keySet()) {
            if (adjacencyList.get(v).inDegree == 0) {
                zeroStack.push(v);
            }
        }
        
        
        // Process vertices with in-degree 0
        int totalVertices = adjacencyList.size();
        for (int i = 0; i < totalVertices; i++) {
            if (zeroStack.isEmpty()) break;
        
            // Add to sorted list
            V current = zeroStack.pop();
            sortedList.add(current);
            
            // Decrease in-degree of neighbors
            for (V neighbor : getNeighbors(current)) {
                Vertex v = adjacencyList.get(neighbor);
                v.inDegree--;
                
                // If in-degree becomes 0, push to zeroStack        
                if (v.inDegree == 0) {
                    zeroStack.push(neighbor);
                }
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
        Graph_InDegree<String> graph = new Graph_InDegree<>();
        // Read the file and add edges
File file = new File(Graph_InDegree.class.getResource("/lab8/prerequisites.txt").getFile());
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("->")) {
                String[] buffer = line.split("->");
                String from = buffer[0].trim();
                String to = buffer[1].trim();
                graph.addEdge(from, to);
            }
        }
        scanner.close();

        // Print the unsorted graph
        System.out.println(graph.toString());

        // Topologically sort and print the graph
        ArrayList<String> sorted = graph.kahnTopoSort();
        System.out.println("Topological Sort: " + sorted);
    }
}
