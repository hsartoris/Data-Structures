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
    }

    /**
       Sorts this list.
    */
    public void sort() {
	mergeSort(this);
    }

    /**
       MERGESORT
       POST List l is sorted in ascending order.
    */
    private void mergeSort(SortedLinkedList<T> l) {
	
	// tiny lists are sorted by definition
        if (l.size() <= 1) return;

        // 2 or more nodes in l
        else {
	    LinkedListCirc lists = split(l);

            SortedLinkedList<T> list1 = (SortedLinkedList) lists.head();
            SortedLinkedList<T> list2 = (SortedLinkedList) lists.head();

            // INVAR: All nodes of list l placed into list1 and list2.
            //        l is empty.

            // Sort list1 and list2
            mergeSort(list1);
            mergeSort(list2);
            // Put list1 and list2 back together into l.
            merge(list1,list2,l);
        }
    }



    /* 
       Split the list into two lists.  Return as a list containing two lists.
       POST: Returns a list containing the two lists.
       Warning: The original list is destroyed!
     */
    private  LinkedListCirc split(SortedLinkedList<T> l) {
	// split the list into two new lists: list1 and list2
	SortedLinkedList<T> list1 = new SortedLinkedList();
	SortedLinkedList<T> list2 = new SortedLinkedList();


	// TODO: Divide the nodes between list1 and list2, so that each has 
	// half.  Use list methods like isEmpty(), head(), and append().
	// Make sure the idea for your algorithm is clear enough
	// for another person to easily understand!









	// INVARIANT: All nodes of list l have been placed into list1 and list2, so 
	// it is empty.


	LinkedListCirc lists = new LinkedListCirc(); // a variable used to store two halves of list.
	// TODO: append the two lists onto lists.
	// Use list methods like isEmpty(), head(), and append().


	return lists;
    } // end split
    

    /**
       Merge two lists.  Items are merged to preserve ascending order
       in the final output, l.
       @param l1 one sorted list
       @param l2 the second sorted list
       @param lst the list that is created from the merger of l1 and l2.  lst should be an empty list
       Warning l1 and l2 are destroyed.
    */

    private void merge(SortedLinkedList<T> l1,SortedLinkedList<T> l2,SortedLinkedList<T> lst) {

	// TODO: Loop over l1 and l2, building up lst.
	// You must preserved the order so that lst is sorted.
	// Use list methods like isEmpty(), head(), and append().
	// Make sure the idea for your algorithm is clear enough
	// for another person to easily understand!


    } // end merge



} // end class SortedLinkedList
