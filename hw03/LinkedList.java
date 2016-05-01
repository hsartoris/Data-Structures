/**
   Implementation of List via singly linked nodes.

   @author Sven Anderson 3/4/2002, revised 3/25/2016

*/
import java.util.*;

public class LinkedList<T> implements List<T> {
    protected Node<T> head; // head of list
    protected Node<T> tail; // tail of list
    protected int size; // size of list

    /**
       Head and tail start of null.
     */
    public LinkedList() {
	head = null;
	tail = null;
	size = 0;
    }

    /**
       @returns true iff list is empty.
    */
    public boolean isEmpty() {
	return size == 0;
    }

    /**
       Make list empty.
    */
    public void clear() {
	head = tail = null;
	size = 0;
    }


    /**
       @return # elements in list
    */
    public int size() {
	return this.size;
    }

    /**
       @returns head of list.  Object is removed from list.
    */
    public T head() {
	if (isEmpty()) throw new ListEmptyException();
	T tmp = head.item;
	head = head.next;
	if (head == null) tail = null;
	size--;
	return tmp;
    }


    /**
       @returns tail of list.  Object is removed from list.
    */
    public T tail() {
	if (isEmpty()) throw new ListEmptyException();
	T tmp = tail.item;
	if (size() == 1) { // was only one item
	    head = tail = null;
	    size = 0;
	}
	else {
	    Node<T> curr = head;
	    while (curr.next != tail) {
		curr = curr.next;
	    }
	    tail = curr; // update tail (pretty slow)
	    tail.next = null;
	    size--;
	}
	return tmp;
    }


    /**
       Return head w/o removing it.
     */
    public T peek() {
	if (isEmpty()) throw new ListEmptyException("Num items: " + this.size);
	return head.item;
    }

    /**
       Adds obj to end of list.
    */
    public void append(T obj) {
	// List is empty, must create first node
	if (isEmpty()) {
	    tail = head = new Node<T>(obj,null);
	}
	else {
	    Node<T> newtail = new Node<T>(obj,null);
	    tail.next = newtail;
	    tail = newtail;
	}
	this.size++;
    }

    /**
       Adds obj to head of list.
    */
    public void prepend(T obj) {
	// List is empty, must create first node
	if (isEmpty()) {
	    tail = head = new Node<T>(obj,null);
	}
	else {
	    Node<T> newhead = new Node<T>(obj,head);
	    head = newhead;
	}
	this.size++;
    }


    /**
       Print entire list from head to tail.
    */
    public void print() {
	Node<T> tmp = head;
	while (tmp != null) {
	    System.out.println(" " + tmp.item.toString());
	    tmp = tmp.next;
	}

    }


  // Inner class for a node
    protected class Node<T> {
        protected T item;
        protected Node<T> next;
	
        public Node() {
            this(null,null);
        }
	
        public Node(T e, Node<T> n) {
            item = e;
            next = n;
        }
    }

}
