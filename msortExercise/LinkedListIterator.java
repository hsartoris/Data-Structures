
// LinkedListIterator class; maintains "current position"
// This version is for a circular linked list with sentinel node.
//
// CONSTRUCTION: Package visible only, with a Node
//
// ******************PUBLIC OPERATIONS*********************
// void next( )        --> Next
// boolean isValid( )     --> True if at valid position in list
// AnyType retrieve       --> Return item in current position

/**
 *    Linked list implementation of the list iterator
 *    using a sentinel node.
 */
public class LinkedListIterator<AnyType>
{
    Node<AnyType> current;    // Current position, pkg access!!
    Node<AnyType> sentinel;    // Sentinel

    /**
     * Construct the list iterator
     * @param theNode any node in the linked list.
     */
    LinkedListIterator( Node<AnyType> theNode )
    {
        current = theNode;
	sentinel = theNode; // assume theNode is also the sentinel
    }

    /**
     * Test if the current position is a valid position in the list.
     * @return true if the current position is valid.
     */
    public boolean isValid( )
    {
	//return current != null && current != sentinel;
	// current CANNOT be null
	return current != sentinel;
    }

    /**
     * Return the item stored in the current position.
     * @return the stored item or null if the current position
     * is not in the list.
     */
    public AnyType retrieve( )
    {
	return isValid( ) ? current.item : null;
    }

    /**
     * Next the current position to the next node in the list.
     * If the current position is null, then do nothing.
     */
    public void next( )
    {
	// The test is no longer necessary and prevents
	// us from advancing from the sentinel node
	//	    if( isValid( ) ) 
	current = current.next;
    }

    public void setSentinel(Node<AnyType> s) {
	this.sentinel = s;
    }

}