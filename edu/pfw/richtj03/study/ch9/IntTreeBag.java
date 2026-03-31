package edu.pfw.richtj03.study.ch9;

/* Binary Search Tree Storage Rules 
 * In a binary search tree, the elements of the nodes can be compared with a total order semantics. 
 * These two rules are followed for every node n: 
 * 1. Every element in n's left subtree is less than or equal to the element in node n. 
 * 2. Every element in n's right subtree is greater than the element in node n. 
 * */

/* An IntTreeBag is a collection of int numbers.
 * Beyond Integer.Max_VALUE elements, countOccurrences and size are wrong*/
public class IntTreeBag implemnts Cloneable {

	private IntBTNode root;
	
	/* public IntTreeBag()
	 * Initialize an empty bag.
	 *
	 * Postcondition:
	 * 	This bag is empty */
	public IntTreeBag() {
		//TODO
	}

	/* public void add(int element)
	 * Add a new element to this bag.
	 * Parameter:
	 * 	element - the new element that is being added
	 * Postcondition:
	 * 	A new copy of the element has been added to this bag.
	 * Throws: OutOfMemoryError
	 * 	Indicates insufficient memory for adding a new element.*/

	public void add(int element) {
		//TODO
	}

	/* public void addMany(int... elements)
	 * Add a variable number of new elements to this bag. If these new
	 * elements would take this bag beyond its current capacity, then
	 * the capacity is increased before adding the new elements.
	 * Parameter:
	 * 	elements - a variable number of new elements that are all being added
	 * Postcondition:
	 * 	New copies of all the elements have been added to this bag.
	 * Throws: OutOfMemoryError
	 * 	Indicates insufficient memory for increasing the capacity.
	 * Note:
	 * 	Creating a bag with capacity beyond Integer.MAX_VALUE causes arithmetic overflow.
	 */
	 public void addMany(int... element) {
		//TODO
	 }

	 /* public void addAll(IntTreeBag addend)
	  * Add the contents of another bag to this bag.
	  * Parameter:
	  *  addend - a bag whose contents will be added to this bag
	  * Precondition:
	  *  The parameter, added, is not null.
	  * Postcondition:
	  *  The elements from addend have been added to this bag.
	  * Throws: IllegalArgumentException
	  *  Indicates that addend is null.
	  * Throws: OutOfMemoryError
	  *  Indicates insufficient memory to increase the size of this bag
	  */
	  public void addAll(IntTreeBag addend) {
		//TODO
	  }

	  /* public IntTreeBag clone()
	   * Generate a copy of this bag.
	   * Returns:
	   *  The return value is a copy of this bag. Subsequent changes to the copy
	   *  will not affect the original, nor vice versa. The return value must be
	   *  typecast to an IntTreeBag before it is used.
	   * Throws: OutOfMemoryError
	   *  Indicates insufficient memory for creating the clone.
	   * Throws: CloneNotSupported
	   *  Indicates that clone is not supported (should not happen)
	   */
	   public IntTreeBag clone() throws CloneNotSupportedException {
		//TODO
	   }
	   
	  /* public int countOccurrences(int target)
	   * Accessor method to count the number of occurrences of a
	   * particular element in this bag
	   * Parameter:
	   *  target - the element that needs to be counted
	   * Returns:
	   *  the number of times that target occurs in this bag
	   */
	   public int countOccurrences(int target) {
	       //TODO
	   }

      /* public int size()
       * Accessor method to determine the number of elements in this bag.
       * Returns:
       *  the number of elements in this bag
       */
	   public int size() {
	      //TODO
	   }

	 /* public boolean remove(int target)
	  * Remove one copy of a specified element from this bag 
	  * Parameter:
	  *  target - the element to remove from the bag
	  * Postcondition:
	  *  If target was found in the bag, then one copy of target has been
	  *  removed and the method returns true. Otherwise, the bag remains
	  *  unchanged and the method returns false.
	  */
	   public boolean remove(int target) {
			   //TODO
	   }
     
	  /* public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2)
	   * Create a new bag that contains all the elements from two other bags.
	   * Parameters:
	   *  b1 - the first of two bags
	   *  b2 - the second of two bags
	   * Precondition:
	   *  Neither b1 nor b2 is null
	   * Returns:
	   *  a new bag that is the union of b1 and b2
	   * Throws: IllegalArgumentException
	   *  Indicates that one of the arguments is null.
	   * Throws: OutOfMemoryError
	   *  Indicates insufficient memory for the new bag.
	   */
	   public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2) {
           //TODO
	   }

	/*
	 ******************************************************************
	 *Inner Node Class
	 ******************************************************************/
    private class IntBTNode {

        IntBTNode left, right;
        int data;

        IntBTNode(IntBTNode initialLeft, IntBTNode initialRight, int initialData) {
            left = initialLeft;
            right = initialRight;
            data = initialData;
        }

        public int getData() {
            return this.data;
        }

        public int getLeftMostData() {
            if (this.left == null) {
                return this.data;
            } else {
                return left.getLeftMostData();
            }
        }

        public int getRightMostData() {
            if (this.right == null) {
                return this.data;
            } else {
                return this.getRightMostData();
            }
        }

        public void inorderPrint() {
            if (this.left != null) {
                this.left.inorderPrint();
            }
            System.out.println(this.data);
            if (this.right != null) {
                this.right.inorderPrint();
            }
        }

        public void preorderPrint() {
            System.out.println(this.data);
            if (this.left != null) {
                this.left.preorderPrint();
            }

            if (this.right != null) {
                this.right.preorderPrint();
            }

        }

        public void postorderPrint() {
            if (this.right != null) {
                this.right.postorderPrint();
            }

            if (this.left != null) {
                this.left.postorderPrint();
            }
            System.out.println(this.data);
        }

        public void print(int depth) {
            int i;

            // Print the indentation and the data from the current node:
            for (i = 1; i <= depth; i++) {
                System.out.print(" ");
            }
            System.out.println(data);

            // Print the left subtree (or a dash if there is a right child and no left child).
            if (left != null) {
                left.print(depth + 1);
            } else if (right != null) {
                for (i = 1; i <= depth + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("--");
            }

            // Print the right subtree (or a dash if there is a left child and no left child).
            if (right != null) {
                right.print(depth + 1);
            } else if (left != null) {
                for (i = 1; i <= depth + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("--");
            }
        }

        public int getNodeCount() {
            int count = 0;

            if (left != null) {
                count++;
                count += left.getNodeCount();
            }

            if (right != null) {
                count++;
                count += right.getNodeCount();
            }
            return count;
        }

        public void insertLeft(int data) {
            IntBTNode node = new IntBTNode(null, null, data);
            this.left = node;
        }

        public void insertRight(int data) {
            IntBTNode node = new IntBTNode(null, null, data);
            this.right = node;
        }

        public int getChildCount() {
            int count = 0;

            if (left != null) {
                count++;
            }

            if (right != null) {
                count++;
            }

            return count;
        }

 
    }
	


}
