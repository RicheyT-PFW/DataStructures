package study.ch14;

import java.lang.module.FindException;
import java.util.*;

//Directed Graph implemented with an adjacency list
public class WeightedGraph<V extends Comparable<V>> {
    // Key = Vertex : Value = Hashmap of neighbors and the cost to get to them
    private final HashMap<V, HashMap<V, Integer>> adjacencyList;

    public WeightedGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(V vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    public void addEdge(V from, V to, int weight) {
        if (!adjacencyList.containsKey(from)) {
            addVertex(from);
        }

        if (!adjacencyList.containsKey(to)) {
            addVertex(to);
        }

        adjacencyList.get(from).put(to, weight);
    }

    // Adds an edge in both directions
    public void addMutualEdge(V from, V to, int weight) {
        if (!adjacencyList.containsKey(from)) {
            addVertex(from);
        }

        if (!adjacencyList.containsKey(to)) {
            addVertex(to);
        }

        adjacencyList.get(from).put(to, weight);
        adjacencyList.get(to).put(from, weight);
    }

    public boolean hasEdge(V from, V to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new FindException("Could not find vertex/vertices.");
        }

        return adjacencyList.get(from).containsKey(to);
    }

    // Checks for edge in both directions
    public boolean hasMutualEdge(V from, V to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new FindException("Could not find vertex/vertices.");
        }

        return adjacencyList.get(from).containsKey(to) && adjacencyList.get(to).containsKey(from);
    }

    public Set<V> getNeighbors(V vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new FindException("Could not find vertex.");
        }

        return adjacencyList.get(vertex).keySet();
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


// Dijkstra's algorithm to find the shortest distance from startV to all other vertices
public Map<V, Integer> dijkstra(V startV, Map<V,V> previous) {
    Map<V, Integer> distances = new HashMap<>(); //Distances to other vertices
    for (V vertex : adjacencyList.keySet()) {
        distances.put(vertex, Integer.MAX_VALUE); //Initialize all distances to maximum
        previous.put(vertex, null); //Initialize all previous vertices to null
    }
    distances.put(startV, 0);

    // Priority queue to select the vertex with the smallest distance
    PriorityQueue<Map.Entry<V, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
    pq.add(new AbstractMap.SimpleEntry<>(startV, 0)); // startV is 0 distance away

    Set<V> visited = new HashSet<>(); // Set of visited vertices

    while (!pq.isEmpty()) {
        V current = pq.poll().getKey(); // Get the vertex with the smallest distance
        //Skip visited vertices
        if(visited.contains(current)) {
            continue;
        }
        //Indicate vertex was visited   
        visited.add(current);     

        //Loop through neighbors (Google HashMap .entrySet() and Map.Entry)
        for(V neighbor : adjacencyList.get(current).keySet()) {
            // Calculate new neighbor distance
            distances.put(current, adjacencyList.get(current).get(neighbor));
            
            
            // If the new distance is smaller, update distance and previous
           if(true) { 
            //TODO
            
                // Put the neighbor in the priority queue (Google AbstractMap.SimpleEntry)
                 
            }
        }
    }

    return distances;
}



    public static void main(String[] args) {
        main2();

    }


    public static void main2() {
    WeightedGraph<String> mapGraph = new WeightedGraph<>();

    mapGraph.addMutualEdge("Fort Wayne", "Roanoke", 20);
    mapGraph.addMutualEdge("Fort Wayne", "Huntertown",  11);
    mapGraph.addMutualEdge("Fort Wayne", "Bluffton", 25);
    mapGraph.addMutualEdge("Fort Wayne", "Markle", 25);
    mapGraph.addMutualEdge("Fort Wayne", "Columbia City", 22);
    mapGraph.addMutualEdge("Huntington", "Bluffton", 23);
    mapGraph.addMutualEdge("Huntington", "Roanoke", 18);
    mapGraph.addMutualEdge("Huntington", "Wabash", 20);
    mapGraph.addMutualEdge("Huntington", "Markle", 10);
    mapGraph.addMutualEdge("Columbia City", "Huntertown", 21);
    mapGraph.addMutualEdge("Columbia City", "Huntington", 22);
    mapGraph.addMutualEdge("Columbia City", "Roanoke", 19);
    mapGraph.addMutualEdge("Columbia City", "Wabash", 30);
    mapGraph.addMutualEdge("Markle", "Bluffton", 13);
    mapGraph.addMutualEdge("Markle", "Roanoke", 11);

    System.out.println(mapGraph);

    // Dijkstra's algorithm
    Map<String, String> previous = new HashMap<>();
    Map<String, Integer> distances = mapGraph.dijkstra("Fort Wayne", previous);
    System.out.println("Shortest Distances from Fort Wayne: " + distances);
    System.out.println("Shortest path from Fort Wayne to Wabash: " + mapGraph.getPath("Fort Wayne", "Wabash", previous));

    previous = new HashMap<>(); // Reset previous for new path
    distances = mapGraph.dijkstra("Bluffton", previous);
    System.out.println("Shortest distances from Bluffton: " + distances);
    System.out.println("Shortest path from Bluffton to Columbia City: " + mapGraph.getPath("Bluffton", "Columbia City", previous));

    /* Expected output:
    Weighted Graph adjacency list:
    Columbia City -> {Wabash=30, Huntington=22, Huntertown=21, Roanoke=19, Fort Wayne=22}
    Wabash -> {Columbia City=30, Huntington=20}
    Markle -> {Huntington=10, Bluffton=13, Roanoke=11, Fort Wayne=25}
    Huntington -> {Columbia City=22, Wabash=20, Markle=10, Bluffton=23, Roanoke=18}
    Huntertown -> {Columbia City=21, Fort Wayne=11}
    Bluffton -> {Markle=13, Huntington=23, Fort Wayne=25}
    Roanoke -> {Columbia City=19, Markle=11, Huntington=18, Fort Wayne=20}
    Fort Wayne -> {Columbia City=22, Markle=25, Huntertown=11, Bluffton=25, Roanoke=20}

    Shortest Distances from Fort Wayne: {Columbia City=22, Wabash=52, Markle=25, Huntington=35, Huntertown=11, Bluffton=25, Roanoke=20, Fort Wayne=0}
    Shortest path from Fort Wayne to Wabash: [Fort Wayne, Columbia City, Wabash]
    Shortest distances from Bluffton: {Columbia City=43, Wabash=43, Markle=13, Huntington=23, Huntertown=36, Bluffton=0, Roanoke=24, Fort Wayne=25}
    Shortest path from Bluffton to Columbia City: [Bluffton, Markle, Roanoke, Columbia City]
     */
    }


    public static void main1() {
        // Friend list example
        WeightedGraph<String> friendGraph = new WeightedGraph<>();
        friendGraph.addVertex("Alice");
        friendGraph.addEdge("Alice", "Bob", 0);
        friendGraph.addEdge("Alice", "Charlie", 2);
        friendGraph.addMutualEdge("Bob", "Charlie", 7);
        friendGraph.addMutualEdge("Bob", "Diana", 1);
        System.out.println(friendGraph);
        System.out.println("Is Bob friends with Charlie? " + friendGraph.hasEdge("Bob", "Charlie"));
        System.out.println("Are Bob and Diana mutual friends? " + friendGraph.hasMutualEdge("Bob", "Diana"));
    }
}