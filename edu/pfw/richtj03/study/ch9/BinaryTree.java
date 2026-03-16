package edu.pfw.richtj03.study.ch9;

public class BinaryTree<E> {

	private class Node<E> {
	   E data;
	   Node<E> left;
	   Node<E> right;

	   private Node(Node<E> left, Node<E> right, E data) {
	   	this.left = left;
		this.right = right;
		this.data = data;
	   }

	   private E getData() {
			return this.data; 
	   }
	}

	public BinaryTree(E data) {
		root = new Node<>(null, null, data);
		cursor = root;
    }


	
	private Node<E> cursor;
	private Node<E> root;
	private int nodeCount;
	private int childCount;
	private int leafCount;
	private int depth;

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>(3);
		System.out.println(tree.root.getData());

	}

}

