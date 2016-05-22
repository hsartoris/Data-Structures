/**
   Class to support nodes in a binary tree.  BTNode's data has package
   access.  Out-of-package classes must use public methods to use the
   tree.  
   @author: S. Anderson
*/

public class BTNode<T> { 
    // Note: these all have package access!
    T element; // data
    BTNode<T> left; // left subtree
    BTNode<T> right; // right subtree
    int x, y; // x and y coords for drawing
    int height; // height of node

    public BTNode() {
	this(null,null,null);
    }

    public BTNode(T o) {
	this(o,null,null);
    }

    public BTNode(T o, BTNode<T> l, BTNode<T> r) {
	left = l;
	right = r;
	element = o;
    }


    public T element() { return element; }
    public void setElement(T o) { element = o;}
    public BTNode<T> left() { return left; }
    public BTNode<T> right() { return right; }

    public void setLeft(BTNode<T> v) { left = v; }
    public void setRight(BTNode<T> v) { right = v; }
}
    
