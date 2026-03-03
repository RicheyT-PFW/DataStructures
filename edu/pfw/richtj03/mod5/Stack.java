public class Stack<E> implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  
   LinkedList<E> list;
   public Stack() {
   list = new LinkedList<>();
   }
   /**
   * Adds the element to the top of the stack (head of the list)
   **/
   public void push(E element) {
  
   }
   /**
   * Removes and returns the top element from the stack (head of the list)
   */
   public E pop() {
  
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
   public String toString() {
   return list.toString();
   }
}
