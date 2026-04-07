package mod7;
/*
PART A: 

   i. Copy the provided BinarySearchTree class (BST) into your project. 

  ii. Complete the add() method that adds a new data element to the BST. A node <= parent will go to the left subtree, and a node > parent will go to the right subtree. 

 iii. Test the provided main method to confirm the BST is being organized correctly. 

  iv. What would the tree look like if the nodes are added in the following order: 80, 70, 60, 50, 40, 30, 20? Discuss this with the instructor. 

PART B: 

   i. Complete the printInOrder() method based on the provided //TODO comments. 
      Test the method by uncommenting code in the main method. Add a comment above the test code observing how this traversal order is useful. 

PART C: 

    i. Complete the remove() method based on the provided //TODO comments.
       Test the method by uncommenting code in the main method. 

   ii. Debug the last line of the remove recursion to fully understand how the leaf node is removed.  
 */

public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(E data) {
        root = add(root, data);
        size++;
    }

    // Helper method for add recursion
    private TreeNode<E> add(TreeNode<E> node, E data) {
        if (root == null) {
            root = new TreeNode<E>(data, null, null);
            return root;
        }

        // If data is equal to or less than the current node
        if (data.compareTo(node.getData()) <= 0) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode<>(data, null, null));
            } else {
                add(node.getLeft(), data);
            }

            // If data is greater than the current node
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new TreeNode<>(data, null, null));
            } else {
                add(node.getRight(), data);
            }
        }

        return root;
    }

    public E remove(E data) {
        TreeNode<E> removedNode = remove(root, data);
        if (removedNode != null) {
            size--;
            return removedNode.getData();
        }
        return null; // Data not found
    }

    // Helper method for remove recursion
    private TreeNode<E> remove(TreeNode<E> node, E data) {
        // Base case: Data not found
        if (node == null)
            return null;

        // If the target is smaller than the current node, the current node tells its left child to go find 
        // the target. The left child will return and update the current node with what its new left link should be.
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data)); // Traverse left

        // If the target is larger than current node, the current node tells the right child to go find 
        // the target. The right child will return and update the current node with what its new right link should be.
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), data)); // Traverse right
        } else {
            // Found the node to be deleted, count the children
            int children = node.getChildCount();
            
           // Node with no child
            if (children == 0) {
                return null;
            }
           // Node with one child 
            else if (children == 1) {
                if (node.getLeft() == null) {
                    return node.getRight();
                } else {
                    return node.getLeft();
                }
            // Node with two children:
            } else if (children == 2) {
                // Get the inorder successor (smallest node greater than this node)
                TreeNode<E> successor = node.getRight().getLeftMost();

                // Copy the inorder successor's content to this node
                node.setData(successor.getData());

                // Delete the inorder successor
                node.setRight(remove(node.getRight(), successor.getData()));
            }
        }
        return node;
    }

    public String toString() {
        return root.toString();
    }

    public int size() {
        return size;
    }

    public void printInOrder() {
        System.out.println("In-order traversal: ");
        printInOrder(root);
        System.out.println();
    }

    // Helper method for printInOrder recursion
    private void printInOrder(TreeNode<E> node) {
        if (node != null) {
            // Recurse left
            printInOrder(node.getLeft());
            // Print node data
            System.out.println(node);
            // Recurse right
            printInOrder(node.getRight());
        }
    }

    public boolean contains(E data) {
        return contains(root, data);
    }

    // Helper method for contains recursion
    private boolean contains(TreeNode<E> node, E data) {
        if (node == null)
            return false;
        if (data.compareTo(node.getData()) == 0)
            return true;
        else if (data.compareTo(node.getData()) < 0)
            return contains(node.getLeft(), data);
        else
            return contains(node.getRight(), data);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        bst.add(40);

        System.out.println(bst);

        bst.printInOrder();

        System.out.println("Tree contains " + bst.size() + " nodes.");
        System.out.println("Tree contains 50: " + bst.contains(50)); // Expected output: true
        System.out.println("Tree contains 25: " + bst.contains(25)); // Expected output: false

        bst.add(25);
        System.out.println("After adding 25:\n" + bst);

        bst.remove(50);
        System.out.println("After removing 50:\n" + bst);

        bst.remove(40);
        System.out.println("After removing 40:\n" + bst);

        bst.remove(20);
        System.out.println("After removing 20:\n" + bst);
    }
}
