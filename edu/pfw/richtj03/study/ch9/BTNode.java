//This is directly from the textbook
package edu.pfw.richtj03.study.ch9;

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

    public BTNode<E> getLeft() {
        return this.left;
    }

    public E getLeftMostData() {
        if (this.left == null) {
            return this.data;
        } else {
            return left.getLeftMostData();
        }
    }

    public BTNode<E> getRight() {
        return this.right;
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

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public void postorderPrint() {
        if (this.left != null) {
            this.left.postorderPrint();
        }

        if (this.right != null) {
            this.right.postorderPrint();
        }
        System.out.println(this.data);
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

    public BTNode<E> removeLeftMost() {
        if (left == null) {
            return this.right;
        } else {
            left = left.removeLeftMost();
            return this;
        }
    }

    public BTNode<E> removeRightMost() {
        if (this.right == null) {
            return this.left;
        } else {
            this.right = this.removeRightMost();
            return this;
        }
    }

    public void setData(E newData) {
        this.data = newData;
    }

    public void setLeft(BTNode<E> newLeft) {
        this.left = newLeft;
    }

    public void setRight(BTNode<E> newRight) {
        this.right = newRight;
    }

    public static <E> BTNode<E> treeCopy(BTNode<E> source) {
        BTNode<E> leftCopy, rightCopy;

        if (source == null) {
            return null;
        } else {
            leftCopy = treeCopy(source.left);
            rightCopy = treeCopy(source.right);
            return new BTNode<>(leftCopy, rightCopy, source.data);
        }
    }

    public static <E> int treeSize(BTNode<E> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + treeSize(root.left) + treeSize(root.right);
        }
    }

}
