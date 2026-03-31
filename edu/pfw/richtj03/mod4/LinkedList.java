package edu.pfw.richtj03.mod4;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.*;

/******************************************************************************
 * A LinkedList<E> provides a generic singly linked list implementation.
 * It manages various connected Node objects.
 
 * Part C
 *  To make this class a doubly linked list we would need to add a previous node.
 *  We would need to account for this change in any method that adds or removes
 *  nodes from the list.
 
 *  For adding, not only would we need to adjust the previous node to have the next new node,
 *  we would also need to adjust the next new node to reference the previous node.
 
 *  For removing, we would need to adjust the 2 nodes forward to previous and the previous
 *  2 two nodes forward.
 
 *  One way that the NodeAt method could be modified to have improved performance with a
 *  doubly linked list is to check the middle index. After checking the middle index, if
 *  the target is greater than the middle index, traverse from the tail. If less than the
 *  middle index, traverse from the head. If the target is the middle, do either.
 */
public class LinkedList<E> implements Cloneable, Iterable<E>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int size;
    private Node<E> head;
    private Node<E> tail;

    private class Node<E> {
        // Invariant of the Node class:
        // 1. Each node has one reference to an E Object, stored in the instance
        // variable data.
        // 2. For the final node of a list, the link is null.
        // Otherwise, the link is a reference to the next list node.
        private final E data;
        private Node<E> next;


        /**
         * Initialize a node with a specified initial data and link.
         *
         * @param initialData the initial data of this new node
         * @param initialLink a reference to the node after this new node - may be null
         **/
        public Node(E initialData, Node<E> nextLink) {
            data = initialData;
            next = nextLink;
        }
    }


    class LinkedListIterator<T> implements Iterator<T> {
        Node<T> current;

        public LinkedListIterator(Node<T> head){
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T answer;

            if (!hasNext()) {
                throw new NoSuchElementException("Empty list.");
            }

            answer = current.data;
            current = current.next;
            return answer;
        }


        @Override
        public void remove() {
            removeNode((E) current.data);
        }
    }

    /**
     * Creates an empty LinkedList
     **/
    public LinkedList() {}

    @Override
    public LinkedList<E> clone() {

        LinkedList <E> clone = null;
        try{
            clone = (LinkedList<E>) super.clone();
        } catch(Exception e) {
            System.out.print(e);
        }
        return clone;
    } 
    
    
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head);
    }

    public int size() {
        return size;
    }
    /**
     * Adds the element to the end of the list
     **/
    public void add(E element) {
// For empty list, set head and tail to the new node
        if (head == null) {
            head = new Node<>(element, null);
            tail = head;
        } else {
            tail.next = new Node<>(element, null);
            tail = tail.next;
        }
        size++;
    }
/**
     If the input is the size of the list, the worst case for the method
     void add(int index, E data) method is O(1). This is because the number
     of operations to add a node to the list remains the same regardless of the
     input size. */
    public void add(int index, E data) {
        validateIndex(index);
        if (index == 0) {
            Node<E> temp = head;
            head = new Node<>(data, temp);

        } else {
            Node<E> prev = nodeAt(index - 1);
            prev.next = new Node<>(data, prev.next.next);
        }
        size++;
    }

    /**
     * Removes the element at the specified index
     */
    public boolean removeAt(int index) {
        validateIndex(index);

	//If the list is empty
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from an empty list");
        }

	//If list has more than one element
	//and we are removing the first index
        if (index == 0 && size != 1) { 
            head = head.next;
	//If the list has one element and we are removing
	//the first index
        } else if (index == 0 && size == 1) {
		head = null;
		tail = null;
	} else {
	    // Grab the node before the specified index
            Node<E> previous = nodeAt(index - 1);

	    // Skip over the specified index by setting
	    // the nextLink to the node after the specified index
            previous.next = previous.next.next;
        }
        size--;
        return true;
    }
    /**
     * Removes the first occurrence of the element
     */
    private boolean removeNode(E element) {
        if (element == null) {
            throw new NullPointerException("element is null");
        }
        if (size == 0) {
            throw new NullPointerException("List is empty");
        }
        if (head.data.equals(element)) {
            return removeAt(0); //Remove head
        }
        Node<E> current = head;
        while (current.next != null) { //Keep previous node for easy removal
            if (current.next.data.equals(element)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; //Element not found
    }
    /**
     * Go to a specific index and return the element.
     * head is at index 0 and tail is at index size - 1.
     */
    private Node<E> nodeAt(int index) {
        validateIndex(index);
    //Conveniently return tail if index is the last element
        if (index == size - 1) {
            return tail;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    /**
     * Finds the index of the first occurrence of the target element
     */
    public int indexOf(E target) {
        if (target == null) {
            throw new NullPointerException("target is null");
        }
        Node<E> current = null;
        int index = 0;
        for (current = head; current.next != null; current = current.next) {
            if (current.data.equals(target)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    /**
     * Determines if the element is in the list
     **/
    public boolean contains(E target) {
        return indexOf(target) != -1;
    }
    /**
     * Counts the number of occurrences of the target element
     */
    public int countOccurrences(E target) {
        int count = 0;
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(target)) {
                count++;
            }
            current = current.next;
        }
        return count;
    }
    /**
     * Check index bounds
     */
    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * Get a deep copy of the list
     */
    public LinkedList<E> copy() {
        LinkedList<E> copy = new LinkedList<>();
        Node<E> current = head;
        while (current != null) {
            copy.add(current.data);
            current = current.next;
        }
        return copy;
    }


    /**
     * Add all of `otherList`'s nodes to this list (deep copies)
     * The worst case should  be 0(1) because we only do 3 operations at most for every add
     */
    public void addAll(LinkedList<E> otherList) {
        if (otherList == null) {
            return;
        }

        if (head == null) {
            head = otherList.head;
            tail = head;
        } else {
            tail.next = otherList.head;
            tail = otherList.tail;
        }

        size += otherList.size;
    }


    /**
     * Get element at index
     *
     * Time Complexity is O(n)
     */
     public E get(int index) {
         validateIndex(index);
         if (index == size - 1) {
             return tail.data;
         }
         Node<E> current = head;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }
         return current.data;
     }

     //Time Complexity is O(n)
     @Override
     public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size(); i++){
            sb.append(get(i));
            if((i+1) != size()){
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//M4.2 - Iterator test code
        LinkedList<Integer> intList = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            intList.add(i);
        }
//Print squares of each number in the list
        for (int num: intList) {
            System.out.println(Math.pow(num, 2));
        }
        System.out.println("Old size: " + intList.size());
//Remove all even numbers
        Iterator<Integer> iter = intList.iterator();
        while (iter.hasNext()) {
            Integer next = iter.next();
            if (next % 2 == 0) {
                iter.remove();
            }
        }
        System.out.println("Current size: " + intList.size());
    }
}
