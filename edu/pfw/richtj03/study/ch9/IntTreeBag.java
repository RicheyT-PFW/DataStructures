package edu.pfw.richtj03.study.ch9;

/* Binary Search Tree Storage Rules 
 * In a binary search tree, the elements of the nodes can be compared with a total order semantics. 
 * These two rules are followed for every node n: 
 * 1. Every element in n's left subtree is less than or equal to the element in node n. 
 * 2. Every element in n's right subtree is greater than the element in node n. 
 * */

/* An IntTreeBag is a collection of int numbers.
 * Beyond Integer.Max_VALUE elements, countOccurrences and size are wrong*/
public class IntTreeBag implements Cloneable {

	private IntBTNode root;

	/*
	 * public IntTreeBag()
	 * Initialize an empty bag.
	 *
	 * Postcondition:
	 * This bag is empty
	 */
	public IntTreeBag() {
		root = null;
	}

	/*
	 * public void add(int element)
	 * Add a new element to this bag.
	 * Parameter:
	 * element - the new element that is being added
	 * Postcondition:
	 * A new copy of the element has been added to this bag.
	 * Throws: OutOfMemoryError
	 * Indicates insufficient memory for adding a new element.
	 */

	public void add(int element) {
		
		if(root == null) {
			root = new IntBTNode(null, null, element);
			return;
		}

		IntBTNode cursor = root;
		boolean done = false;

		while(!done) {
			// If the element being added is less than or equal to the current data
			if(cursor.data > element || cursor.data == element) {
				//Check if we can insert to the left
				if(cursor.getLeft() == null) {
					cursor.insertLeft(element);
					done = true;
					// If not go to the left node
				} else {
					cursor = cursor.left;
				}

			// If the element being added is greater than current data
			} else if(cursor.data < element) {
				// Check if we can insert to the right
				if(cursor.getRight() == null) {
					cursor.insertRight(element);
					done = true;
					// If not go to the right node
				} else {
					cursor = cursor.right;
				}
			}
		}
	}

	/* private void addTree(IntBTNode addroot)
	 * 
	 * Precondition:
	 * addroot is a reference to the root of a binary search tree
	 * that is separate from the binary search tree of the bag that 
	 * activated this method
	 * Postcondition:
	 * All the elements from the addroot's binary search tree have been
	 * added to the binary search tree of the bag that activated this method. 
	 */
	private void addTree(IntBTNode addroot) {
		if(addroot != null) {
			add(addroot.getData());
			addTree(addroot.getLeft());
			addTree(addroot.getRight());
		}
	}

	/*
	 * public void addMany(int... elements)
	 * Add a variable number of new elements to this bag. If these new
	 * elements would take this bag beyond its current capacity, then
	 * the capacity is increased before adding the new elements.
	 * Parameter:
	 * elements - a variable number of new elements that are all being added
	 * Postcondition:
	 * New copies of all the elements have been added to this bag.
	 * Throws: OutOfMemoryError
	 * Indicates insufficient memory for increasing the capacity.
	 * Note:
	 * Creating a bag with capacity beyond Integer.MAX_VALUE causes arithmetic
	 * overflow.
	 */
	public void addMany(int... element) {
		for(int e : element) {
			add(e);
		}
	}

	/*
	 * public void addAll(IntTreeBag addend)
	 * Add the contents of another bag to this bag.
	 * Parameter:
	 * addend - a bag whose contents will be added to this bag
	 * Precondition:
	 * The parameter, added, is not null.
	 * Postcondition:
	 * The elements from addend have been added to this bag.
	 * Throws: IllegalArgumentException
	 * Indicates that addend is null.
	 * Throws: OutOfMemoryError
	 * Indicates insufficient memory to increase the size of this bag
	 */
	public void addAll(IntTreeBag addend) {
		IntBTNode addroot;

		//If addend is the same bag as this
		if(root == addend.root) {
			addroot = IntBTNode.treeCopy(addend.root);
			addTree(addroot);
		} else {
			addTree(addend.root);
		}
	}

	/*
	 * public boolean remove(int target)
	 * Remove one copy of a specified element from this bag
	 * Parameter:
	 * target - the element to remove from the bag
	 * Postcondition:
	 * If target was found in the bag, then one copy of target has been
	 * removed and the method returns true. Otherwise, the bag remains
	 * unchanged and the method returns false.
	 */
	public boolean remove(int target) {
		IntBTNode parentOfCursor = null, cursor = root;
		boolean done = false;

		//If the cursor is null
		if(cursor == null) return false;

		//Find the target if it exists
		while(!done) {
			if(cursor.data == target) {
				done = true;
			} else if(cursor.data > target) {
				parentOfCursor = cursor;
				cursor = cursor.left;
			} else if(cursor.data < target) {
				parentOfCursor = cursor;
				cursor = cursor.right;
			} else if(cursor == null) {
				done = true;
			}
		}


		//If cursor is at root of the tree with no left child
		if(cursor == root && cursor.left == null) {
			root = root.getRight();
			return true;
		}

		//If the cursor is farther down the tree but still without a left child of its own
		if(cursor != root && cursor.left == null) {
			// The cursor is on the left side of the parent, so change parent’s left link.
			if (cursor == parentOfCursor.getLeft()) { 
				parentOfCursor.setLeft(cursor.getRight());
			}
			// The cursor is on the right side of the parent, so change parent’s right link.
			else { 
				parentOfCursor.setRight(cursor.getRight());
			}
			return true;
		}

		//If the cursor is non-null and have a left child
		if(cursor != null && cursor.left != null) {
			cursor.setData(cursor.getLeft().getRightMostData());
			return true;
		}

		return false;
	}


	/*
	 * public IntTreeBag clone()
	 * Generate a copy of this bag.
	 * Returns:
	 * The return value is a copy of this bag. Subsequent changes to the copy
	 * will not affect the original, nor vice versa. The return value must be
	 * typecast to an IntTreeBag before it is used.
	 * Throws: OutOfMemoryError
	 * Indicates insufficient memory for creating the clone.
	 * Throws: CloneNotSupported
	 * Indicates that clone is not supported (should not happen)
	 */
	public IntTreeBag clone() {
		IntTreeBag clone = null;
		try {
			clone = (IntTreeBag) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("This class does not implement Cloneable.");
		}
		return clone;
	}

	/*
	 * public int countOccurrences(int target)
	 * Accessor method to count the number of occurrences of a
	 * particular element in this bag
	 * Parameter:
	 * target - the element that needs to be counted
	 * Returns:
	 * the number of times that target occurs in this bag
	 */
	public int countOccurrences(int target) {
		int count = 0;
		IntBTNode cursor = root;
		while(cursor != null) {
			if(cursor.data == target) {
				count++;
				cursor = cursor.left;
			} else if(cursor.data < target) {
				cursor = cursor.right;
			} else if(cursor.data > target) {
				cursor = cursor.left;
			}  
		}

		return count;
	}

	/*
	 * public int size()
	 * Accessor method to determine the number of elements in this bag.
	 * Returns:
	 * the number of elements in this bag
	 */
	public int size() {
		// TODO
		return IntBTNode.treeSize(root);
	}

	/*
	 * public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2)
	 * Create a new bag that contains all the elements from two other bags.
	 * Parameters:
	 * b1 - the first of two bags
	 * b2 - the second of two bags
	 * Precondition:
	 * Neither b1 nor b2 is null
	 * Returns:
	 * a new bag that is the union of b1 and b2
	 * Throws: IllegalArgumentException
	 * Indicates that one of the arguments is null.
	 * Throws: OutOfMemoryError
	 * Indicates insufficient memory for the new bag.
	 */
	public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2) {
		IntTreeBag answer = new IntTreeBag();
		answer.addTree(b1.root);
		answer.addTree(b2.root);
		return answer;
	}

	public static void main(String[] args) {
		IntTreeBag treeBag = new IntTreeBag();
		String query = "Do you want to add to the bag? [Y or N]: ";
		String buffer = "";
		boolean running = true;
		java.util.Scanner scanObj = new java.util.Scanner(System.in);

		do {
			System.out.print(query);
			buffer = scanObj.nextLine().trim();
			if(buffer.toLowerCase().startsWith("y")) {
				System.out.print("\n\nEnter number: ");
				buffer = scanObj.nextLine().trim();
				treeBag.add(Integer.parseInt(buffer));
			} else {
				running = false;
				scanObj.close();
			}
			
		}while(running);

		if(treeBag.root != null) {
			treeBag.root.print(0);
		} 
	}
	
	

	/*
	 ******************************************************************
	 * Inner Node Class
	 ******************************************************************
	 */
	private static class IntBTNode {

		private IntBTNode left, right;
		private int data;

		IntBTNode(IntBTNode initialLeft, IntBTNode initialRight, int initialData) {
			left = initialLeft;
			right = initialRight;
			data = initialData;
		}

		public void setData(int data) {
			this.data = data;
		}

		public void setLeft(IntBTNode left) {
			this.left = left;
		}

		public void setRight(IntBTNode right) {
			this.right = right;
		}

		public int getData() {
			return this.data;
		}

		public IntBTNode getLeft() {
			return this.left;
		}

		
		public IntBTNode getRight() {
			return this.right;
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

			// Print the left subtree (or a dash if there is a right child and no left
			// child).
			if (left != null) {
				left.print(depth + 1);
			} else if (right != null) {
				for (i = 1; i <= depth + 1; i++) {
					System.out.print(" ");
				}
				System.out.println("--");
			}

			// Print the right subtree (or a dash if there is a left child and no left
			// child).
			if (right != null) {
				right.print(depth + 1);
			} else if (left != null) {
				for (i = 1; i <= depth + 1; i++) {
					System.out.print(" ");
				}
				System.out.println("--");
			}
		}

		public boolean isLeaf() {
        	return (this.left == null && this.right == null);
    	}

		public IntBTNode removeLeftMost() {
			if (left == null) {
				return this.right;
			} else {
				left = left.removeLeftMost();
				return this;
			}
		}

		public IntBTNode removeRightMost() {
			if (this.right == null) {
				return this.left;
			} else {
				right = right.removeRightMost();
				return this;
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

		public static int treeSize(IntBTNode root) {
			if (root == null) {
				return 0;
			} else {
				return 1 + treeSize(root.left) + treeSize(root.right);
			}
    	}

		public static IntBTNode treeCopy(IntBTNode source) {
			IntBTNode leftCopy, rightCopy;

			if (source == null) {
				return null;
			} else {
				leftCopy = treeCopy(source.left);
				rightCopy = treeCopy(source.right);
				return new IntBTNode(leftCopy, rightCopy, source.data);
			}
		}

		public void insertLeft(int data) {
			IntBTNode node = new IntTreeBag.IntBTNode(null, null, data);
			this.left = node;
		}

		public void insertRight(int data) {
			IntBTNode node = new IntTreeBag.IntBTNode(null, null, data);
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
