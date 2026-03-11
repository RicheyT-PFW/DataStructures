
package edu.pfw.richtj03.study.ch9;
import java.util.Scanner;

public class Animal {
	private static Scanner stdin = new Scanner(System.in);
	
	public static void main(String[] args) {
		BTNode<String> root;

		instruct();
		root = beginningTree();
		do{
		play(root);
		}while(query("Shall we play again?"));
		
		System.out.println("Thanks for teaching me a thing or two.");	
	}

	//This method prints instructions explaning the game
	public static void instruct(){
		System.out.println
			("An animal guessing game that gets smarter and smarter!");	  
	}
/**
 * learn (for the Animal-Guessing Program)
	public static void learn(BTNode<String> current)
	Elicit information from the user to improve the binary taxonomy tree.
	Parameter:
	  current – a reference to a leaf node of a binary taxonomy tree
	Precondition:
	  current is a reference to a leaf in a binary taxonomy tree.
	Postcondition:
	  Information has been elicited from the user, and the tree has been
	  improved.
	Throws: OutOfMemoryError
	  Indicates that there is insufficient memory to add information to the tree
 **/
	public static void learn(BTNode current) {
				
	}

/** The play Method. The play method has one parameter, which initially is a
reference to the root of the binary taxonomy tree, as shown in this specification:
 * play (for the Animal-Guessing Program)
	public static void play(BTNode<String> current)
	Play one round of the animal-guessing game.

	Parameter:
	   current – a reference to the root node of a binary taxonomy tree that
	   will be used to play the game

	Postcondition:
	   The method has played one round of the game and possibly added new
	   information about a new animal.
	Throws: OutOfMemoryError
	   Indicates that there is insufficient memory to add information to the tree.
 **/
	public static void play(BTNode current) {
		while (!current.isLeaf( )){
			if (query(current.getData( )))
				current = current.getLeft( );
			else
				current = current.getRight( );
		}
		System.out.print("My guess is " + current.getData( ) + ". ");
		if (!query("Am I right?"))
			learn(current);
		else
			System.out.println("I knew it all along!");
	}

	public static boolean query(String str) {
		String answer;
		System.out.print(prompt + " [Y or N]: ");
		answer = stdin.nextLine( ).toUpperCase( );
		while (!answer.startsWith("Y") && !answer.startsWith("N")){
			System.out.print("Invalid response. Please type Y or N: ");
			answer = stdin.nextLine( ).toUpperCase( );
		}
		return answer.startsWith("Y");	
	}

	public static BTNode<String> beginningTree() {
		BTNode<String> root;
		BTNode<String> child;

		//TODO
		// 1. Make root refer to a new node with the data "Array you a mammal?".
		// Both children initially null.
		final String ROOT_QUESTION = "Are you a mammal";
		root = new BTNode<String>(null, null, ROOT_QUESTION);
		
		//TODO
		// 2. Make child refer to a new node with the data "Are you bigger than a cat?".
		// Give it two leaves as children, with the data "Kangaroo" on the left
		// and "Mouse" on the right. Then activate root.setLeft(child);.
		final String LEFT_QUESTION = "Are you bigger than a cat?";
		final String ANIMAL1 = "Kangaroo";
		final String ANIMAL2 = "Mouse";
		child = new BTNode<String>(null, null, LEFT_QUESTION);
		child.setLeft(new BTNode<String>(null, null, ANIMAL1));
		child.setRight(new BTNode<String>(null, null, ANIMAL2));
		root.setLeft(child);

		//TODO
		// 3. Make child refer to a new node with the data "Do you live underwater?".
		// Give it two leaves as children, with data "Trout" on the left
		// and "Robin" on the right. Then activate root.setRight(child);
		final String RIGHT_QUESTION = "Do you live underwater?";
	        final String ANIMAL3 = "Trout";
		final String ANIMAL4 = "Robin";
		child = new BTNode<String>(null, null, RIGHT_QUESTION);
		child.setLeft(new BTNode<String>(null, null, ANIMAL3));
		child.setRight(new BTNode<String>(null, null, ANIMAL4));
		root.setRight(child);	
		
		return root;

	}
}
