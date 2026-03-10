package edu.pfw.richtj03.study.ch9;

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
            System.out.println(this.data);
            if (this.right != null) {
                this.right.postorderPrint();
            }

            if (this.left != null) {
                this.left.postorderPrint();
            }
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
            }else if (right != null) {
                for (i = 1; i <= depth + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("--");
            }

            // Print the right subtree (or a dash if there is a left child and no left child).
            if (right != null) {
                right.print(depth + 1); 
            }else if (left != null) {
                for (i = 1; i <= depth + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("--");
            }
        }

    }
    BTNode<E> root;


    public BTreeExample(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        root = new BTNode<>(null, null, data);
    }

    public static void main(String[] args) {
        BTreeExample<String> strTree = new BTreeExample<>("1");
        strTree.root.left = strTree.new BTNode<>(null, null, "2");
        strTree.root.right = strTree.new BTNode<>(null, null, "3");

        strTree.root.print(0);
    }
}
