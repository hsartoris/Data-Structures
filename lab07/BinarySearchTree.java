/**
   Implements a BinarySearchTree (BST).  Given a node r, all nodes in
   the left subtree of r are less than r.  All nodes in the right
   subtree of r are greater than or equal to r.

   Only objects that extend Comparable can be stored in the tree.

   @author: 

  */



public class BinarySearchTree <T extends Comparable<? super T>>  extends LinkedBinaryTree<T> {
    
	//can't use generics in a static method without also specifying on the method
	public static <T extends Comparable<? super T>> boolean test(BinarySearchTree<T> t1, BinarySearchTree<T> t2) {
		return testRecurse(t1.root, t2.root) && t1.size == t2.size;
	}
	
	private static <T extends Comparable<? super T>> boolean testRecurse(BTNode<T> t1, BTNode<T> t2) {
		if (t1 == null && t2 == null) return true;
		if (t2 == null || t2 == null) return false;
		if (t1.element().compareTo(t2.element()) != 0) return false;
		return testRecurse(t1.left(), t2.left()) && testRecurse(t1.right(), t2.right());
	}
	
	public int size; // I like keeping track... also it's useful
    public BinarySearchTree() { super(); root = null; size = 0;}

    /**
       Adds an element to the tree.

       @param o an element that can be compared to others in the tree.
    */
    public void addElement(T o) {
    	if (root == null) { root = new BTNode<T>(o,null,null); size++; }
    	else addElement(root,o);
    }

    /* 
       Helper method for adding element
       @param t root node of tree to which o is added.  Must NOT be null.
       @param o object to be inserted
     */
     private void addElement(BTNode<T> t,T o) {

	    // o less than t.element
	    if (o.compareTo(t.element) < 0) {
	    	// add to left subtree
	    	if (t.left == null) { t.left = new BTNode<T>(o,null,null); size++; }
	    	else addElement(t.left,o);
	    } else {
			// if o >= t, add to right subtree
			if (t.right == null) { t.right = new BTNode<T>(o,null,null); size++; }
			else addElement(t.right,o);
	    }
    }

    /**
       Return element that matches
    */

    public T  getElement(T o) {
	if (o == null) return null;
	else return getElement(root,o);
    }

    private T getElement(BTNode<T> t, T o) {
	if (t == null ) return null;
	if ( (t.element).compareTo(o) == 0)
	    return (T) t.element;
	else if ( o.compareTo( (t.element)) < 0)
	    return getElement(t.left,o);
	else
	    return getElement(t.right,o);	    

    }


    /**
       Remove matching element and return it.
       @returns First object in tree that matches.  Returns null
       if no match found.
    */
    public T  removeElement(T o) {
		if (root == null || o == null) return null;
		else return removeElement(root,o);
    }

    private T removeElement(BTNode<T> t, T o) {
		// nothing to remove from empty tree
		if (t == null) return null;
		
		// Find the node that is to be removed.
		//	BTNode remnode = findmatch(BTNode t, o);
	
		BTNode<T> parentofp = null;
		BTNode<T> p = t;
		
		while (p != null) {
		    int comp = o.compareTo( ((T) p.element));
		    if (comp == 0) break;
		    // move to child of p
		    parentofp = p;
	
		    if (comp < 0) 
			p = p.left;
		    else 
			p = p.right;
		}
		// INVARIANT: p is the node to delete.  parentofp is its parent
		if (p == null) return null; // no match found
		/*
		  3 cases
		  1. p is a leaf
		  2. p has one child
		  e. p has two children
		*/
		System.out.println("found " + p.element);
		
		if (p.left == null && p.right == null) // p is leaf
		    removeLeaf(p,parentofp);
		else if (p.left == null || p.right == null)  // p has one subtree
		    removeOneSubtree(p,parentofp);
		else
		    removeTwoSubtrees(p,parentofp);
		size--;
		return (T) p.element;
    }

    /*
      Remove a leaf.  The node p must be a leaf
      and pp must be its parent node.
      If p is root, then root is set to null.
    */
    private void removeLeaf(BTNode<T> p, BTNode<T> pp) {
		if (p == root) { 
		    root = null; 
		} else {
			if (p == pp.left) pp.left = null;
	    else pp.right = null;
	}
    }	

    /**
       Remove p when it has only one subtree.
    */
    private void removeOneSubtree(BTNode<T> p, BTNode<T> pp) {
    	BTNode<T> c;
    	if (p.left != null) c = p.left;
    	else c = p.right;
    	
    	if (pp.left.equals(p)) {
    		pp.setLeft(c);
    	} else {
    		pp.setRight(c);
    	}
    } 


    /**
       Remove p when it has two subtrees.  In this case we replace
       material in p with smallest element in p's left subtree.
    */
    private void removeTwoSubtrees(BTNode<T> p, BTNode<T> pp) {
    	BTNode<T> pc = p;
		BTNode<T> c = p.right();
		while (c.left() != null) {
			pc = c;
			c = c.left();
		}
		removeOneSubtree(c, pc);
		p = c;
	} 
	
	    /**
	       Return node that contains match. 
	       Returns null if no match found.
	    */
	private BTNode<T> findmatch(BTNode<T> t, T target) {
		BTNode<T> parentofp = null;
		BTNode<T> p = t;
		
		while (p != null) {
		    int comp = target.compareTo( ((T) p.element));
	
		    // move to child of p
		    parentofp = p;
	
		    if (comp == 0) break;
		    else if (comp < 0) 
			p = p.left;
		    else 
			p = p.right;
		}	    
		return p;
    }
    
    /* 
       Returns height of tree.  Returns -1 if tree has no node. 
       sets heights in all encountered nodes.
    */
//    private int height(BTNode<T> t) {
//		if (t == null) return -1;
//		return 0;
//    }


    /**
       Find heights of all nodes in tree.
    */
    public void computeNodeHeights() {
    	super.computeNodeHeights(); //just call the super class; already has this functionality
    }


    /*
      visit adds string version of o onto the string representation
      of the tree.
    */
    public void visit(T o) {
	System.out.print(o + " ");
    }
    
    public int avgDepth() {
    	//is keeping a size var cheating?
    	return totalDepth(root) / size;
    }
    
    private int totalDepth(BTNode<T> t) {
    	if (t == null) return 0;
    	return totalDepth(t.left()) + t.height + totalDepth(t.right());
    }

}
