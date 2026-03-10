package edu.pfw.richtj03.mod5;
import edu.pfw.richtj03.mod4.LinkedList;


public class Stack<E> implements java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    LinkedList<E> list;

    public Stack() {
        list = new LinkedList<>();
    }

    /**
     * Adds the element to the top of the stack (head of the list)
   *
     */
    public void push(E element) {
        list.add(element);
    }

    /**
     * Removes and returns the top element from the stack (head of the list)
     */
    public E pop() {
       return list.removeAt(list.size() - 1);
    }

    /**
     * Returns the top element from the stack (head of the list)
     */
    public E peek() {

    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
