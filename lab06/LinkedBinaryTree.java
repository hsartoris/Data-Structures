/**
   Implementation of binary tree interface.
   Elements are inserted randomly.
   
 */

/*
 * inOrder:  BFHGPRSTWYZ
 * preOrder: PFBHGSRYTWZ
 * postOrder:BGHFPRWTZYS
 */

import java.io.PrintWriter;

public class LinkedBinaryTree<T> implements BinaryTree<T>,Visitable<T> {
    protected BTNode<T> root; // root of the tree
    
    // Trees always have a valid root node.
    // An empty tree simply has its element set to null.
    public LinkedBinaryTree() {
	root = new BTNode<T>(null,null,null);
    }

    public LinkedBinaryTree(T o) {
	root = new BTNode<T>(o,null,null);
    }

    public T root() {
	if (root == null) return null;
	else return root.element;
    }

    public boolean isEmpty() {
	return root == null;
    }


    /**
       Add an element to the tree into a random location.
    */
    public void addElement(T o) {
    	if (root.element == null) root.element = o;
    	else addElement(root,o);
    }


    /**
       Perform random insertion of object into tree.
    */
    private void addElement(BTNode<T> t, T o) {
    	if (t.left() == null && t.right() == null) {
    		if (Math.random() < .5) t.setLeft(new BTNode<T>(o));
        	else t.setRight(new BTNode<T>(o));
    	} else if (t.left() == null) t.setLeft(new BTNode<T>(o));
    	else if (t.right() == null) t.setRight(new BTNode<T>(o));
    	else if (Math.random() < .5) addElement(t.left(), o);
    	else addElement(t.right(), o);
    }



    /**
       Return true iff o is to be found in the tree.
       Uses "equals" to test.  For Integers, equals 
       means objects have same int value.
    */
    public boolean getElement(T o) {
    	if (root.element == null) return false;
    	return getElement(root, o);
    }
    
    private boolean getElement(BTNode<T> n, T o) {
    	if (n == null) return false;
    	if (n.element.equals(o)) return true;
    	return (getElement(n.left(), o) || getElement(n.right(), o));
    }



    public int height() {
		return height(root);
    }

    /* 
       Returns height of tree.  Returns -1 if tree has no node. 
       sets heights in all encountered nodes.
    */
    private int height(BTNode<T> t) {
    	if (t == null) return -1;
    	int leftHeight = height(t.left()) + 1;
    	int rightHeight = height(t.right()) + 1;
    	if (leftHeight > rightHeight) {
    		t.height = leftHeight;
    		return leftHeight;
    	}
    	t.height = rightHeight;
    	return rightHeight;
    }

    /**
       Find heights of all nodes in tree.
    */
    public void computeNodeHeights() {
        height(root);
    }


    
    /**
       Preorder traversal.
    */
    public void preOrder() {
    	preOrder(root);
    }
    
    public void preOrder(BTNode<T> t) {
    	if (t == null) return;
    	visit(t.element());
    	preOrder(t.left());
    	preOrder(t.right());
    }

    /**
       Inorder traversal.
    */
    public void inOrder() {
    	inOrder(root);
    }
    
    private void inOrder(BTNode<T> t) {
    	if (t == null) return;
    	inOrder(t.left());
    	visit(t.element());
    	inOrder(t.right());
    }

    /**
       Postorder traversal.
    */
    public void postOrder() {
		postOrder(root);
    }
    
    private void postOrder(BTNode<T> t) {
    	if (t == null) return;
    	postOrder(t.left());
    	postOrder(t.right());
    	visit(t.element());
    }

    /*
      Action to perform when node is visited by iterator.
    */
    public void visit(T o) {
	System.out.print(o + " ");
    }

    /**
       Save items stored in tree's nodes, one
       per line.
    */
    public void save(String fname) {
	// TODO
    }

    
    /******************************************************************/
    /* Graphics and display methods. */
    /******************************************************************/

    /**
       Return map containing keys.  Map is used
       to generate graphical representation of tree.
    */
    public Object[][] getMap() {
	int maxsize = 50;
	int maxrow = height(root)+1; // maximum height + 1
	int maxcol = (int) Math.pow(2.0,maxrow); //max cols in binary tree
	

	Object[][] map = new Object[20][20];
	// fill array with keys from tree nodes.
	maxcol = drawTree(root,map,0,0,maxrow);

	Object[][] newmap = new Object[maxrow][maxcol];
	for (int i = 0; i < newmap.length; i++) {
	    for (int j = 0; j < newmap[i].length; j++)
		newmap[i][j] = map[i][j];
	}
	return newmap;
    }


    /**
       Draw a binary search tree to stdout. (text)
    */
    public void showTree() {
	// map holds pointers to all objects in the tree
	// cannot handle more than 400 nodes on a page.
	Object[][] map = new Object[20][20];
	int maxcol = 0;
	int maxrow = height(root)+1;
	// fixed field width for each node and for each blank
	final String padding = "   "; 

	System.out.println("Tree height is " + (maxrow-1));

	// fill array with keys from tree nodes.
	maxcol = drawTree(root,map,0,maxcol,maxrow);

	// use keys in map to place 
	for (int row = 0; row < maxrow; row++) {
	    for (int col = 0; col < maxcol; col++) {
		// if no node, print padding
		if (map[row][col] == null) System.out.print(padding);
		// if there is a node print it in a field of 
		//length padding.length
		else padPrint(map[row][col],padding.length());
	    }
	    System.out.println();
	}
    }

    /* print padding around strings */
    private void padPrint(Object o, int padlen) {
	String s = o.toString();
	System.out.print(s);
	int len = s.length();
	while (len++ < padlen) {
	    System.out.print(' ');
	}

    }


    /*
      Position tree nodes in a 2D array by first position left subtree,
      then node, then right subtree.
      @returns col, the maximum column used.
     */
    private int drawTree(BTNode root, Object[][] map,
				 int row, int col, int maxrow) {
	// stop if leaf node or if map dimensions exceeded
	if (root == null) return col;
	if ((maxrow+1) >= map.length || 
	    (col+1) >= map[0].length) {
	    System.out.println("WARNING. Tree could not be completely printed.");
	    return col; 
	}
	// maximum row (level) containing a node
	if (row > maxrow) maxrow = row; 
	
	col = drawTree(root.left,map,row+1,col,maxrow);
	map[row][col] = ((Object) root.element);
	col++;
	col = drawTree(root.right,map,row+1,col,maxrow);	
	return col;
    }

    /**
       Update drawing positions in tree prior to draw.
    */
    public void updatePositions() {
	positionNodes(root,0,0);
    }

    /**
       Update x,y in each node.
       y is the row = node depth.
       x is the col = #preceding nodes in left subtree + 1
    */
    private int positionNodes(BTNode r, int row, int col) {
	// stop if leaf node
	if (r == null) return col;
	
	// set position left subtree nodes
	col = positionNodes(r.left,row+1,col); 

	// set position of this node
	r.y = row;  // 
	r.x = col;
	col++;
	// set pos of right subtree nodes
	col = positionNodes(r.right,row+1,col); 
	return col;
    }



}

