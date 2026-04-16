package mod8;

import java.lang.module.FindException;
import java.util.*;

//Directed Graph implemented with an adjacency list
public class Graph<V extends Comparable<V>> {
    // Key = Vertex : Value = TreeSet of neighbors
    private HashMap<V, TreeSet<V>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>(100);
    }

    public void addVertex(V vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new TreeSet<>());
        }
    }

    public void addEdge(V from, V to) {
        if (!adjacencyList.containsKey(from)){
            addVertex(from);
        }

        if (!adjacencyList.containsKey(to)) {
            addVertex(to);
        }

        adjacencyList.get(from).add(to);
    }

    // Adds an edge in both directions
    public void addMutualEdge(V from, V to) {
        if (!adjacencyList.containsKey(from)){
            addVertex(from);
        }

        if (!adjacencyList.containsKey(to)) {
            addVertex(to);
        }

        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }

    public boolean hasEdge(V from, V to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new FindException("Could not find vertex/vertices.");
        }

        return adjacencyList.get(from).contains(to);
    }

    //Checks for edge in both directions
    public boolean hasMutualEdge(V from, V to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new FindException("Could not find vertex/vertices.");
        }

        return adjacencyList.get(from).contains(to) && adjacencyList.get(to).contains(from);
    }

    public TreeSet<V> getNeighbors(V vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new FindException("Could not find vertex.");
        }

        return adjacencyList.get(vertex);
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph adjacency list:\n");
        for (V vertex : getVertices()) {
            sb.append(vertex.toString())
                    .append(" -> ")
                    .append("\t")
                    .append(adjacencyList.get(vertex).toString())
                    .append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //Friend list example
        Graph<String> friendGraph = new Graph<>();
        friendGraph.addVertex("Alice");
        friendGraph.addEdge("Alice", "Bob");
        friendGraph.addEdge("Alice", "Charlie");
        friendGraph.addMutualEdge("Bob", "Charlie");
        friendGraph.addMutualEdge("Bob", "Diana");
        System.out.println(friendGraph);
        System.out.println("Is Bob friends with Charlie? " + friendGraph.hasEdge("Bob", "Charlie"));
        System.out.println("Are Bob and Diana mutual friends? " + friendGraph.hasMutualEdge("Bob", "Diana"));
    }
}