/**
 Linked list implementation of List interface using Iterator and circular list.

   LinkedListIterator zeroth( ): Return position to prior to first
   LinkedListIterator first( ):  Return first position
   void insert( x, p ): Insert x after current iterator position p

   LinkedListIterator find( x ): Return position that views x
   LinkedListIterator findPrevious( x ):  Return position prior to x

*/

public class LinkedListCirc<T> {
    protected Node<T> sentinel; // sentinel (never null, next field never null)
    protected int numItems; // number of items

    public LinkedListCirc() {
	sentinel = new Node<T>(null,null);
	sentinel.next = sentinel; // empty list has sentinel point to itself
	numItems = 0;
    }

    /**
       @return true if list is empty.
    */
    public boolean isEmpty() {
	return numItems == 0;
    }

    /**
       @return number of items in list
    */
    public int size() {
	return numItems;
    }


    /**
       @return remove first item of list and return it.
    */
    public T head() {
	if (isEmpty()) return null;
	T obj = sentinel.next.item;
	sentinel.next = sentinel.next.next;
	numItems--;
	return obj;
    }

/**
     * Return an iterator representing the node prior to the head.
     */
    public LinkedListIterator<T> zeroth( )
    {
        return new LinkedListIterator<T>( sentinel );
    }

    /**
     * Return an iterator representing the first node in the list.
     * This operation is valid for empty lists.
     */
    public LinkedListIterator<T> first( )
    {
	LinkedListIterator<T> iter = new LinkedListIterator<T>(sentinel.next);
	iter.setSentinel(sentinel); // iterator MUST know correct sentinel.
        return iter;
    }

    /**
     * Insert after p. 
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    public void insert( T x, LinkedListIterator<T> p )
    {
        if( p != null &&  p.current != null ) {
            p.current.next = new Node<T>( x, p.current.next );
	    numItems++;
	}
    }

    /**
     * Return iterator corresponding to the first node containing an item.
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public LinkedListIterator<T> findit( T x )
    {
	sentinel.item = x;  // put target into sentinel so that
	// the while loop will always terminate.
        Node<T> itr = sentinel.next;;
        while( !itr.item.equals( x ) )  itr = itr.next;
	// itr points to node containing target match
	LinkedListIterator<T> iterator = new LinkedListIterator<T>(itr); // we return an iterator,
	// but if the item was not found, it will be the sentinel and therefore invalid.
	iterator.setSentinel(sentinel);
	sentinel.item = null; // remove reference to user's object x just for for safety
	return iterator;
    }

    /**
       Remove first item equivalent to o.
       @param item T to remove from list.
       @return object in list that is equivalent using equals()
       returns null if no such object found
    */

    public T remove(T o) {
	sentinel.item = o; // will always find o
	Node<T> prev = sentinel;
	Node<T> curr = sentinel.next;
	while (!curr.item.equals(o)) {
	    prev = curr;
	    curr = curr.next;
	}
	// ASSERT: curr contains item and prev is previous node.
	sentinel.item = null; // reset sentinel for safety
	if (curr == sentinel) return null; // o was not found, perhaps because the list is empty
	prev.next = curr.next; // skip over the node containing o
	return curr.item;
    }


    /**
       Add item onto the end of the list.
       You cannot modify the list while using an iterator.
       @param item T to add to list
    */
    public void append(T item) {
	// I chose to let the sentinel become the new end of the list.
	// Why?
	// Because then I don't have to find the original tail that pointed
	// at the sentinal (that's O(n)).
	sentinel.item = item;
	sentinel.next = new Node<T>(null,sentinel.next);
	sentinel = sentinel.next;  // have to change the sentinel now.
	// I don't like to change the sentinel, but it's better than O(n) for 
	// a simple append, which is a common operation on lists.
	numItems++;
    }

    /**
       Add item onto the beginning of the list.
       You cannot modify the list while using an iterator.
       @param item T to add to list
    */
    public void prepend(T item) {
	sentinel.next = new Node<T>(item,sentinel.next);
	numItems++;
    }


    /**
       Removes all items from list.
    */
    public void clear() {
	numItems = 0;
	sentinel.next = sentinel;
    }

    public String toString() {
	String s = "(";
	Node<T> current = sentinel.next;
	while (current != sentinel) {
	    s += current.item + " ";
	    current = current.next;
	}
	s += ")";
	return s;
    }


  public static <T> void printList( LinkedListCirc<T> theList )
    {
        if( theList.isEmpty( ) )
            System.out.print( "Empty list" );
        else
        {
	    // This shows how to use the iterator.
            LinkedListIterator<T> itr = theList.first( );
            for( ; itr.isValid( ); itr.next( ) )
                System.out.print( itr.retrieve( ) + " " );
        }

        System.out.println( );
    }



 public static void main( String [ ] args )
    {
        LinkedListCirc<Integer> theList = new LinkedListCirc<Integer>( );
        LinkedListIterator<Integer> theItr;
        int i;
	
	System.out.println("==============================================");
	System.out.println("Should be the empty listl: ");
        theItr = theList.zeroth( );
        printList( theList );

	
	System.out.println("==============================================");
	System.out.println("Should be 3 1 ");
	theList.append(3);
	theList.append(1);
        printList( theList );
  
	System.out.println("==============================================");
	theList = new LinkedListCirc<Integer>( );  
        theItr = theList.zeroth( );

        System.out.println( "The insertions follow the sentinel node, so this is a prepend of 0, 1, ... ");
        for( i = 0; i < 10; i++ )
        {
            theList.insert( i , theItr );
            printList( theList );
            theItr.next( );
        }
        System.out.println( "Size was: " + theList.size( ) );

	System.out.println("==============================================");
	// Simultaneously use two iterators!
        System.out.println( "Using two iterators.  Should print null 2 0 3 1 4 2 5 ");
        LinkedListIterator<Integer> theItr1 = theList.zeroth();
        LinkedListIterator<Integer> theItr2 = theList.zeroth();
	for ( i = 0; i < 3; i++) theItr2.next();
	for (int j = 0; j < 4; j++) {
	    System.out.println(theItr1.retrieve());
	    System.out.println(theItr2.retrieve());
	    theItr1.next();
	    theItr2.next();
	}

	System.out.println("==============================================");
	// TODO: This doesn't work until you finish remove!
        System.out.println( "Testing Removal");
        for( i = 0; i <= 10; i += 2 ) {
            theList.remove( i );
	    System.out.println("After removing " + i);
	    printList(theList);
	}

        System.out.println( "Finished deletions" );

	System.out.println("==============================================");
        for( i = -1; i <= 11; i+=2 )
            if( theList.findit( i ).isValid())
                System.out.println( "Finds " + i );
	    else
                System.out.println( "Does not find  " + i );

        printList( theList );



    }



}
