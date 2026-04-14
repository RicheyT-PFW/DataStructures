package study.ch14;

import mod6.Queue;
import mod5.Stack;
public class Graph<E> {

    private boolean[][] edges;
    private E[] labels;
    
    /**
     * Constructor for the Graph<E>
     * public Graph(int n)
     * Initialize a Graph with n vertices, no edges, and null labels.
     * Parameter:
     * n – the number of vertices for this Graph
     * Precondition:
     * n >= 0.
     * Postcondition:
     * This Graph has n vertices, numbered from 0 to n-1. It has no edges, and
     * all vertex labels are null.
     * Throws: OutOfMemoryError
     * Indicates insufficient memory to create this Graph.
     * Throws: NegativeArraySizeException
     * Indicates that n is negative.
     */

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        edges = new boolean[n][n];
        labels = (E[]) new Object[n];
    }

    /**
     * public void addEdge(int source, int target)
     * Add an edge
     * Parameters:
     * source – the vertex number of the source of the edge
     * target – the vertex number of the target of the edge
     * Precondition:
     * Both source and target are non-negative and less than size().
     * Postcondition:
     * The specified edge is added to this Graph (unless it was already present)
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the source or target was not a valid vertex number.
     */
    public void addEdge(int source, int target) {
        edges[source][target] = true;
    }

    /**
     * public boolean isEdge(int source, int target)
     * Test wheter an edge exists
     * Parameters:
     * source - the vertex number of the source of the edge
     * target - the vertext number of the target of the edge
     * Precondition:
     * Both source and target are non-negative and less than size()
     * Postcondition:
     * The return value is true if the specified edge exists and is false otherwise
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the source or target was not a valid vertex number
     */

    public boolean isEdge(int source, int target) {
        return edges[source][target];
    }

    /**
     * public void removeEdge(int source, int target)
     * Test wheter an edge exists
     * Parameters:
     * source - the vertex number of the source of the edge
     * target - the vertext number of the target of the edge
     * Precondition:
     * Both source and target are non-negative and less than size()
     * Postcondition:
     * The specified edge is removed from this graph
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the source or target was not a valid vertex number
     * 
     */

    public void removeEdge(int source, int target) {
        edges[source][target] = false;
    }

    /**
     * public Graph<E> clone()
     * Generate a copy of this graph
     * Returns:
     * The return value is a copy of this Graph. Subsequent changes to the copy will
     * not affect the
     * original, nor vice versa. The return value must be typecast to a Graph before
     * it is used
     * Throws: OutOfMemoryError
     * Indicates insufficient memory for creating the clone.
     */

    @SuppressWarnings("unchecked")
    public Graph<E> clone() {
        Graph<E> answer;

        try {
            answer = (Graph<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }

        answer.edges = edges.clone();
        answer.labels = labels.clone();

        return answer;
    }

    /**
     * public E getLabel(int vertex)
     * Accessor method to get the label of a vertex of this Graph
     * Parameters:
     * vertex - a vertex number
     * Precondition:
     * vertex is non-negative and less than size()
     * Returns:
     * The label of the specified vertex in this Graph
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the vertex was not a valid vertex number
     * 
     */
    public E getLabel(int vertex) {
        return labels[vertex];
    }

    /**
     * public int[] neighbors(int vertex)
     * Accessor method to obtain a list of neighbors of a specified vertex of this
     * Graph
     * Parameters:
     * vertex - a vertex number
     * Precondition:
     * vertex is non-negative and less than size()
     * Returns:
     * The return value is an array that contains all the vertex numbers of vertices
     * that are targets
     * of edges with a source at the specified vertex.
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the vertex was not a valid vertex number
     */

    public int[] neighbors(int vertex) {
        int i;
        int count;
        int[] answer;

        // First count how many edges have the vertex as their source:
        count = 0;
        for (i = 0; i < labels.length; i++) {
            if (edges[vertex][i]) {
                count++;
            }
        }

        // Allocate the array for the answer:
        answer = new int[count];

        // Fill the array for the answer:
        count = 0;
        for (i = 0; i < labels.length; i++) {
            if (edges[vertex][i]) {
                answer[count++] = i;
            }
        }

        return answer;
    }

    /**
     * public void setLabel(int vertex, E newLabel)
     * Change the label of a vertex of this Graph
     * Parameters:
     * vertex - a vertex number
     * newLabel - a vertex number
     * Precondition:
     * vertex is non-negative and less than size()
     * Postcondition:
     * The label of the specified vertex in this Graph has been changed to newLabel.
     * Throws: ArrayIndexOutOfBoundsException
     * Indicates that the vertex was not a valid vertex number
     */

    public void setLabel(int vertex, E newLabel) {
        labels[vertex] = newLabel;
    }

    /**
     * public int size()
     * Accessor method to determin the number of vertices in this Graph
     * Returns:
     * The number of vertices in this Graph
     */

    public int size() {
        return labels.length;
    }

    // Makes the simple graph (Graph with no loops and no multiple edges)
    // shown on page 735 of the textbook (Figure 14.2 - Crocodile Airlines Routes)
    // and returns it.
    public static Graph<String> crocodileAirlinesRoutes() {
        Graph<String> graph = new Graph<>(9);

        // Assign all the vertices to a label
        graph.setLabel(0, "Darwin");
        graph.setLabel(1, "Black Stump");
        graph.setLabel(2, "Brisbane");
        graph.setLabel(3, "Perth");
        graph.setLabel(4, "Adelaide");
        graph.setLabel(5, "Canberra");
        graph.setLabel(6, "Sydney");
        graph.setLabel(7, "Melbourne");
        graph.setLabel(8, "Hobart");

        // Add all the edges
        graph.addEdge(0, 5);
        graph.addEdge(1, 0);
        graph.addEdge(2, 6);
        graph.addEdge(3, 1);
        graph.addEdge(4, 3);
        graph.addEdge(4, 7);
        graph.addEdge(4, 3);
        graph.addEdge(5, 2);
        graph.addEdge(5, 6);
        graph.addEdge(6, 5);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(7, 5);
        graph.addEdge(8, 7);

        return graph;
    }

    public static <E> void depthFirstRecurse(Graph<E> g, boolean[] marked, Stack<Integer> stk) {
        int[] connections = g.neighbors(stk.peek());
        int v = stk.pop();

        marked[v] = true;
        System.out.println(g.getLabel(v));

        // Traverse all the neighbors, looking for unmarked vertices:
        for (int nextNeighbor : connections) {
            if (!marked[nextNeighbor]) {
                stk.push(nextNeighbor);
                depthFirstRecurse(g, marked, stk);
            }
       }
    }

    public static <E> void depthFirstPrint(Graph<E> g, int start) {
        boolean[] marked = new boolean[g.size()];
        Stack<Integer> stk = new Stack<>();
        marked[start] = true;
        stk.push(start);
        depthFirstRecurse(g, marked, stk);
    }

    public static <E> void breadthFirstPrint(Graph<E> g, int start) {
        boolean[] marked = new boolean[g.size()];
        Queue<Integer> q = new Queue<>();
        marked[start] = true;
        q.enqueue(start);
        breadthFirstRecurse(g, marked, q);
    }

    private static <E> void breadthFirstRecurse(Graph<E> g, boolean[] marked, Queue<Integer> queue) {
        // Base case
        if (queue.isEmpty()) {
            return;
        }

        int v = queue.dequeue();
        System.out.println(g.getLabel(v));

        // enqueue all unmarked neighbors
        for (int nextNeighbor : g.neighbors(v)) {
            if (!marked[nextNeighbor]) {
                marked[nextNeighbor] = true; // mark when enqueuing to avoid duplicates
                queue.enqueue(nextNeighbor);
            }
        }

        // recurse to process next in queue
            breadthFirstRecurse(g, marked, queue);

    }

    public static void main(String[] args) {
        Graph<String> graph = Graph.crocodileAirlinesRoutes();
        int start;

        if(args.length != 0) {
            start = Integer.parseInt(args[0]);
        } else {
            start = 7;
        }
        
        System.out.println("BFS\n-------------------------------");
        Graph.breadthFirstPrint(graph, start);
        System.out.println("\nDFS\n-------------------------------------");
        Graph.depthFirstPrint(graph, start);
    }
}
