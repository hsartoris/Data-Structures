import java.util.*;
import java.io.*;


/**
   This application permits the user to interactively construct, view,
   and destroy a binary tree.  This BT permits duplicate keys.
   @author: S. Anderson
*/
public class RunBT {

    public static void main(String[] args) {
	Random rand = new Random();
	LinkedBinaryTree tree = new LinkedBinaryTree<Integer>();  // the tree
	BufferedReader stdin = 
	    new BufferedReader(new InputStreamReader(System.in));
	char menuchoice = 'h'; // menu choice character entered by user
	String helpmsg = "Menu: a (add node), r (num rand to add), f (find node), n (new tree), " +
	    "p (print inorder), d (delete node), s (save tree), l (load tree), h (help) q (quit)";
	boolean quit = false; // quit the application?

	BTDisplay display = new BTDisplay(tree);
	String inputString;
	String errorMsg = "Improper syntax; please use h for help";

	while (true) {
	    
	    try {
		// get input from user
		System.out.print("Select>> ");
		inputString = stdin.readLine();
		menuchoice = inputString.charAt(0);

		// take appropriate action
		switch (menuchoice) {

		case 'a': // add a node
			int i;
			if (inputString.length() > 2) {
				if (inputString.charAt(1) != ' ') {
					System.out.println(errorMsg);
					break;
				}
				i = Integer.parseInt(inputString.substring(2));
			} else {
				System.out.print("Enter integer to add: ");
				i = Integer.parseInt(stdin.readLine());
			}
		    tree.addElement(new Integer(i));
		    display.repaint();
		    break;
		case 'r': // add random nodes
			if (inputString.length() > 2) {
				if (inputString.charAt(1) != ' ') {
					System.out.println(errorMsg);
					break;
				}
				i = Integer.parseInt(inputString.substring(2));
			} else {
				System.out.print("Enter number of nodes to add: ");
				i = Integer.parseInt(stdin.readLine());
			}
		    for (; i > 0; i--) {
			tree.addElement(new Integer( (int) (100*Math.random())));
		    }
		    display.repaint();
		    break;
		case 's': // save tree in "savedtree.txt"
		    tree.save("savedtree.txt");
		    break;
		case 'l': // load tree from "savedtree.txt"
		    System.out.print("Not implemented.");
		    break;
		case 'f': // find an object in a node
			int fi;
		    //
			if (inputString.length() > 2) {
				if (inputString.charAt(1) != ' ') {
					System.out.println(errorMsg);
					break;
				}
				fi = Integer.parseInt(inputString.substring(2));
			} else {
				System.out.print("Enter integer to find: ");
				fi = Integer.parseInt(stdin.readLine());
			}
		    if (tree.getElement(new Integer(fi)) == false) 
			System.out.println("Didn't find it.");
		    else
			System.out.println("Found it.");
		    break;
		case 'n': // new tree, new display too
		    tree = new LinkedBinaryTree();
		    System.out.println("Tree deleted.  New tree!.");
		    display.close();
		    display = new BTDisplay(tree);
		    display.repaint();
		    break;
		case 'p': // print tree
		    System.out.print("Nodes in order: ");  tree.inOrder();
		    System.out.println();
		    System.out.print("Nodes in pre-order: ");  tree.preOrder();
		    System.out.println();
		    System.out.print("Nodes in post-order: ");  tree.postOrder();
		    System.out.println();
		    break;
		case 'd': // remove node from tree
		    System.out.print("Not implemented.");
		    break;
		case 'h': // print help message
		    System.out.println(helpmsg);
		    break;
		case 'q': // quit
		    System.out.println("Quitting.");
		    quit = true;
		    display.quit(); // close display
		    break;
		default: 
		    System.out.println("Menu choice not recognized.");
		    System.out.println(helpmsg);
		}	    
		if (quit) break; // quit the app.
	    }
	    catch (IOException e) {
		System.out.println("Read error.  Try again.");
	    }
	    catch (Exception e2) {
		System.out.println("General error.  Try again.");
	    }


	}
    }
}
