package study.ch9;

import java.util.Scanner;

public class BTreeExample<E> {

    public class BTNode<E> {

        BTNode<E> left, right;
        E data;

        BTNode(BTNode<E> initialLeft, BTNode<E> initialRight, E initialData) {
            left = initialLeft;
            right = initialRight;
            data = initialData;
        }

        public E getData() {
            return this.data;
        }

        public E getLeftMostData() {
            if (this.left == null) {
                return this.data;
            } else {
                return left.getLeftMostData();
            }
        }

        public E getRightMostData() {
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

        public void insertLeft(E data) {
            if (data == null) {
                throw new IllegalArgumentException("Data cannot be null");
            }
            BTNode<E> node = new BTNode<>(null, null, data);
            this.left = node;
        }

        public void insertRight(E data) {
            if (data == null) {
                throw new IllegalArgumentException("Data cannot be null");
            }
            BTNode<E> node = new BTNode<>(null, null, data);
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

        @SuppressWarnings("unchecked")
        public void populate() {
            String queryLeft = "Do you want to insert into left of " + this.data;
            if (query(queryLeft)) {
                System.out.print("Enter data: ");
                E newData = (E) stdin.nextLine();
                this.insertLeft(newData);
                this.left.populate();
            }

            String queryRight = "Do you want to insert into right of " + this.data;
            if (query(queryRight)) {
                System.out.print("Enter data: ");
                E newData = (E) stdin.nextLine();
                this.insertRight(newData);
                this.right.populate();
            }
        }
    }

    public BTNode<E> root;
    private static final Scanner stdin = new Scanner(System.in);

    public BTreeExample(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = new BTNode<>(null, null, data);
    }

    public int getTotalChildren() {
        return root.getNodeCount();
    }

    public void populate() {
        this.root.populate();
    }

    // The query Method. This method prints a prompt (using System.out.print)
    // and reads the user’s yes or no answer (using stdin.nextLine). If the user
    // responds yes, then the method returns true; otherwise, the method returns false.
    public static boolean query(String prompt) {
        String answer;
        System.out.print(prompt + " [Y or N]: ");
        answer = stdin.nextLine().toUpperCase();
        while (!answer.startsWith("Y") && !answer.startsWith("N")) {
            System.out.print("Invalid response. Please type Y or N: ");
            answer = stdin.nextLine().toUpperCase();
        }
        return answer.startsWith("Y");
    }

    public static void main(String[] args) {
        BTreeExample<String> strTree = new BTreeExample<>("0");
        strTree.populate();
        strTree.root.inorderPrint();
    }
}
