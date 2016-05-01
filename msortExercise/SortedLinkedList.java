/**
   Implement SortedList using circular linked list with sentinel head
   node.

   @author S. Anderson 
*/

public class SortedLinkedList <T extends Comparable<? super T>> extends LinkedListCirc<T>  {

    /**
       Insert obj into list that is sorted in ascending order.  
       Object placed just before items >= to it.
    */
    public void insert(T obj) {

		Node<T> curr = sentinel;
		sentinel.item = obj; // put obj in sentinel node
	
		// find position for obj
		while (obj.compareTo(curr.next.item) >  0) {
		    curr = curr.next;
		}
		// ASSERT: obj <= curr.next.item
		curr.next = new Node<T>(obj,curr.next);
		numItems++;
		sentinel.item = null; // clear sentinel
    }

    /**
       Sorts this list in place.
    */
    public void sort() {
		if (this.numItems < 2) return;
		// Detach the sentinel node
		Node<T> curr = this.sentinel.next;
		while (curr.next != sentinel) curr = curr.next; // find tail
		curr.next = null; 
		// sort
		Node<T> newhead = mergesort(this.sentinel.next,this.numItems);
		sentinel.next =  newhead;
		// TODO #3
		// reattach sentinel
		curr = newhead;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = sentinel;
    }

    // Print null-terminated list
    private void printlist(Node<T> hd) {
		while (hd != sentinel && hd != null) {
		    System.out.print(" " + hd.item);
		    hd = hd.next;
		}
		System.out.println();
    }

    /**
       MERGESORT
       POST Returns head of list sorted in ascending order.
    */
    private Node<T> mergesort(Node<T> l1, int n) {
	// tiny lists are sorted by definition
        if (n <= 1) return l1; 
        else {
		    printlist(l1);
		    Node<T> l2 = split(l1,n/2); //  l1 will have n/2 nodes
		    printlist(l1);
		    printlist(l2);
	
		    l1 = mergesort(l1,n/2);
		    l2 = mergesort(l2,n - n/2);
		    return merge(l1,l2);
        }
    }

    /**
       Merge l1 and l2 into one list.  Return its head node.
    */
    private Node<T> merge(Node<T> l1, Node<T> l2) {
    	Node<T> head;
    	if (l1.item.compareTo(l2.item) < 0) {
    		head = l1;
    		l1 = l1.next;
    	} else {
    		head = l2;
    		l2 = l2.next;
    	}
    	// this two step process strikes me as very stupid
    	/*
    	if (l1.item.compareTo(l2.item) < 0) {
    		head.next = l1;
    		l1 = l1.next;
    	} else {
    		head.next = l2;
    		l2 = l2.next;
    	}
    	Node<T> curr = head.next;
    	*/
    	Node<T> curr = head;
    	while(l1.next != null && l2.next != null) {
    		if (l1.item.compareTo(l2.item) < 0) {
        		curr.next = l1;
        		curr = curr.next;
        		l1 = l1.next;
        	} else {
        		curr.next = l2;
        		curr = curr.next;
        		l2 = l2.next;
        	}
    	}
    	while(l1 != null) {
    		curr.next = l1;
    		curr = curr.next;
    		l1 = l1.next;
    	}
    	while(l2 != null) {
    		curr.next = l2;
    		curr = curr.next;
    		l2 = l2.next;
    	}
    	
    	return head;
		// TODO #2
		// Draw a picture of merge.
		// Come up with an ordered list of what we need to do.
	}
	
	    /*
	      Removes first n nodes from list pointed at by head.
	      Returns pointer to this remaining list.  Detaches
	      last pointer in head's list by setting it to null.
	    */
	private Node<T> split(Node<T> head, int n) {
		/* returns last n
		Node<T> behind = head;
		Node<T> end = head;
		for (int i = 0; i < n; i++) {
			end = end.next;
		}
		while (end.next != null) {
			behind = behind.next;
			end = end.next;
		}
		end = behind.next;
		behind.next = null;
		return end;
		*/
		
		Node<T> curr = head;
		for (int i = 0; i < n-1; i++) {
			curr = curr.next;
		}
		Node<T> temp = curr.next;
		curr.next = null;
		return temp;
		// TODO #1
		// iterate over n-1 nodes
		// save new tail of first half
		// return new head of second half
    }



}
