package edu.pfw.richtj03.study.ch9;

public class BinaryTree<E> {

	private class  Node<E> {
	   E data;
	   Node<E> left;
	   Node<E> right;

	   private Node(Node<E> left, Node<E> right, E data) {
	   	this.left = left;
		this.right = right;
		this.data = data;
	   }
	}
}

