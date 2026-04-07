package mod4.lab5;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/******************************************************************************
 * A LinkedList<E> provides a generic singly linked list implementation.
 * It manages various connected Node objects.
 * Complete the areas marked with a TODO comment.
 ******************************************************************************/
public class LinkedList<E> implements Iterable<E> {

    /**
     * Custom Internal Generic Node Class
     *
     *         Invariant of the Node class:
     *            1. Each node has one reference to an E Object, stored in the instance
     *               variable data.
     *            2. For the final node of a list, the link is null.
     *               Otherwise, the  link is a reference to the next list node.
     *
     * @param <E>
     */
    class Node<E> {

        private final E data;
        private Node<E> next;
        private Node<E> previous;

        /**
         * Initialize a node with a specified initial data and link.
         *
         * @param initNext     link to the next node - may be null
         * @param initPrevious link to the previous node - may be null
         **/
        public Node(E initialData, Node<E> initNext, Node<E> initPrevious) {
            data = initialData;
            next = initNext;
            previous = initPrevious;
        }
    }

    /********** Iterator class **********/
    public class LinkedListIterator implements ListIterator<E> {
        private Node<E> current; //Points to the node to be returned by next()
        private int index;

        public LinkedListIterator() {
            current = null;
            index = -1;
        }

        /**
         * Returns true if the iteration has a next element.
         */
        @Override
        public boolean hasNext() {
            return index + 1 < size;
        }

        /**
         * Returns true if the iteration has a previous element.
         */
        @Override
        public boolean hasPrevious() {
            return index - 1 >= 0;
        }

        /**
         * Returns the element at the current position.
         */
        public E get() {
            return current.data;
        }

        /**
         * Moves to and returns the next element in the iteration.
         *
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (index == -1) {
                current = head; //Attach head on first advance
            } else {
                current = current.next;
            }
            E result = get();
            index++;
            return result;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         */
        @Override
        public int nextIndex() {
            return index + 1;
        }

        /**
         * Moves to and returns the previous element in the iteration.
         *
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            E result = null;
            //TODO - Implement previous by backtracking up the list

            if (index == -1) {
                current = head; //Attach head on first advance
                index++;
            } else {
                current = current.previous;
                index--;
            }
            result = get();
            return result;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous().
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        //************************************************************************
        //Unimplemented methods from the ListIterator interface, not needed for this assignment

        /**
         * Removes from the underlying collection the last element returned (optional operation).
         * Not needed for this assignment.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Replaces the last element returned by next or previous with
         * the specified element (optional operation).
         * Not needed for this assignment.
         */
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * Inserts the specified element into the list (optional operation).
         * Not needed for this assignment.
         */
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    private int size;

    private Node<E> head;
    private Node<E> tail;

    /**
     * Creates an empty LinkedList
     **/
    public LinkedList() {
    }

    /**
     * Adds the element to the end of the list
     **/
    public void add(E element) {
        // For empty list, set head and tail to the new node
        if (head == null) {
            head = new Node<E>(element, null, null);
            tail = head;
        } else {
            tail.next = new Node<E>(element, null, tail);
            tail = tail.next;
        }
        size++;
    }

    //Build a String representation of the list as a timeline with the current element in brackets
    public String toString(ListIterator it) {
        LinkedListIterator iterator = (LinkedListIterator) it;
        //Access current iterator item with iterator.current
        StringBuilder sb = new StringBuilder();
        //TODO - Add <-> between elements and [ ] around current element
        Node<E> cursor = head;
        while(cursor != null) {
            if (cursor.data.equals(iterator.current.data)){
                sb.append("[");
                sb.append(cursor.data.toString());
                sb.append("]");
            } else {
                sb.append(cursor.data.toString());
            }

            if(cursor.next != null) {
                sb.append(" ↔ ");
            }
            cursor = cursor.next;
        }
        return sb.toString();
    }

    /**
     * Get an iterator for the list
     */
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }


}
