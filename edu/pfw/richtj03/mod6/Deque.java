package mod6;

import mod4.LinkedList;
public class Deque<E> implements java.io.Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 1L;
    LinkedList<E> list;

    // A -> B -> C -> D
    // h t

    public Deque() {
        list = new LinkedList<>();
    }

    /**
     * Adds the element to the back of the deque
     * O(1)
     **/
    public void addLast(E element) {
        list.add(element);
    }

    /**
     * Adds the element to the front of the deque
     * O(1)
     **/
    public void addFirst(E element) {
        list.add(0, element);
    }

    /**
     * Removes and returns the first element from the deque
     * O(1) - each operation is done in constant time. Meaning that the number of
     * operations
     * is not affected by the input size.
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        E old = peekFirst();
        list.removeAt(0);
        return old;
    }

    /**
     * Removes and returns the last element from the deque
     * O(1) - each operation is done in constant time. Meaning that the number of
     * operations
     * is not affected by the input size.
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List Is empty");
        }

        E old = peekLast();
        list.removeAt(size() - 1);
        return old;
    }

    /**
     * Returns the head element from the deque
     * O(1)
     **/
    public E peekFirst() {
        if (isEmpty()) {
            throw new NullPointerException("Queue is empty");
        }
        return list.get(0);
    }

    /**
     * Returns the tail element from the deque
     * O(1)
     **/
    public E peekLast() {
        if (isEmpty()) {
            throw new NullPointerException("Queue is empty");
        }
        return list.get(size() - 1);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        // Create a Deque containing Integer’s 1 – 5 representing the turn order for
        // players.
        Deque<Integer> deque = new Deque<>();
        deque.addLast(Integer.valueOf("1"));
        deque.addLast(Integer.valueOf("2"));
        deque.addLast(Integer.valueOf("3"));
        deque.addLast(Integer.valueOf("4"));
        deque.addLast(Integer.valueOf("5"));

        // In a 10-iteration loop:
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                // The # at the front of the queue is the current player turn.
                // Print the current player's turn.
                System.out.printf("Player %d's turn\n", deque.peekFirst());
                // Move to the next player after moving the current player to the end of the
                // queue.
                deque.addLast(deque.removeFirst());
            } else {
                deque.addFirst(deque.removeLast());
                System.out.printf("Player %d's turn\n", deque.peekFirst());
        

            }
        }
    }
}
