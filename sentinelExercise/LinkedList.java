/**
   Implementation of List via circularly linked nodes and sentinel node.

   A sentinel node is placed at the end and its next field points to
   the head of the list.

   @author Sven Anderson
 */


public class LinkedList<T> implements List<T> {
    private final Node<T> sentinel = new Node<T>(); // created once and for all here

    // The sentinel's item can be used as temporary storage, so it 
    // may not be null.

    // The node prior to sentinel.  
    //When the list is empty tail
    // must point to sentinel
    private Node<T> tail; 

    private int numItems; // number of items in list

    /**
       Construct an empty list.
    */
    public LinkedList() {
	tail = sentinel;
	sentinel.next = sentinel;
	numItems = 0;
    }

    /**
       @ returns true if list is empty.
    */
    public boolean isEmpty(){
	return numItems == 0;
    }


    /**
       @ returns number of elements in list
    */
    public int size() {
	return numItems;
    }


    /**
       @returns head of list.  T is NOT removed from list.
       Returns null if list is empty
    */
    public T headPeek() {
    	if (sentinel != sentinel.next) {
    		return sentinel.next.item;
    	}
    	return null;
    }

    /**
       @returns Tail of list. T is NOT removed from list.
       Returns null if list is empty
    */
    public T tailPeek() {
    	sentinel.item = null;
    	return tail.item;
    }
    


    /**
       Adds obj to end of list.
       If obj is null it is not appended.
    */
    public void append(T obj) {
    	if (obj != null) {
    		Node<T> newNode = new Node<T>(obj, sentinel);
    		tail.next = newNode;
    		tail = newNode;
    		numItems++;
    	}
    }

    /**
       Adds obj to head of list.
       If obj is null it is not prepended.
    */
    public void prepend(T obj) {
    	if (obj != null && !isEmpty()) {
    		sentinel.next = new Node<T>(obj, sentinel.next);
    		numItems++;
    	} else append(obj);
    }

    
    /**
       @returns head of list.  T is removed from list.
       Returns null if head is empty.
    */
    public T head() {
		if (isEmpty()) return null;
		
		sentinel.item = sentinel.next.item; // store the obj to return
		sentinel.next = sentinel.next.next;
		numItems--;
		if (numItems == 0) tail = sentinel;
		return sentinel.item;
    }

    /**
       @returns Tail of list. T is removed from list.
    */
    public T tail() {

	if (isEmpty()) return null;
	
	sentinel.item = tail.item; // store returned obj
	Node<T> oldtail = tail;

	// Reset tail
	tail = sentinel;
	while (tail.next != oldtail) {
	    tail = tail.next;
	}
	// INVAR: tail points to node before previous tail
	tail.next = sentinel;
	numItems--;

	return sentinel.item;
    }

    

    /**
       @return object in list
       Returns null if object not found in list.
    */
    public T find(T obj) {
    	sentinel.item = obj;
    	Node<T> cur = sentinel.next;
    	while (!cur.item.equals(obj)) cur = cur.next;
    	if (cur != sentinel) return cur.item;
    	return null;
    }



    /**
       Remove first equivalent object from list.
       @return object that is removed from list.
       Returns null if indicated object is not in list.
    */
    public T remove(T obj) {
	Node<T> prev = sentinel;
	Node<T> curr = sentinel.next;
	sentinel.item = obj;



	return sentinel.item;
    }


    
    /**
       @return string representation of the list

    */
    public String toString() {
	StringBuffer sb = new StringBuffer("( ");
	
	Node<T> curr = sentinel.next;
	while (curr != sentinel) {
	    sb.append(curr.item.toString() + " ");
	    curr = curr.next;
	}

	sb.append(" )");
	return sb.toString();
    }

    
 ////////////////// Inner class for a node /////////////////////////
    class Node<T> {
        private T item;
        private Node<T> next;
	
        public Node() {
            this(null,null);
        }

        public Node(T e, Node<T> n) {
            item = e;
            next = n;
        }
    } // end of Node class



} // end of LinkedList class
