
    /**
       Node for linked list.
    */

   public class Node<T> { 
       T item; // package access!
	Node<T> next; 
    
	public Node() { 
	    this(null,null);  // refers to constructor of the present class 
	} 
    
	/**
	   create new node containing an T e and pointing to
	   next node n.
	   @param e object in node
	   @param n next node in list
	*/
	public Node(T e, Node n) { 
	    item = e; 
	    next = n; 
	} 
    
	/**
	   Set item in node to T.
       
	*/
	public void  setItem(T newItem) { 
	    item = newItem; 
	} 


	/**
	   Set next pointer in the node.
	*/
	public void setNext(Node newNext) { 
	    next = newNext; 
	} 
    
	public T getItem() { 
	    return item; 
	} 
    
	public Node getNext() { 
	    return next; 
	} 
    

	public String toString() {
	    return  "[" + item.toString() + "," + super.toString() + "]";
	}
    } 
     
