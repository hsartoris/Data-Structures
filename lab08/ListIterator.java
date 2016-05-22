


public class ListIterator {
    Node current;
    LinkedList list;

    /**
       List l must not be null.
    */
    public ListIterator(LinkedList l) {
	list = l;
	current = l.head;
    }

    /**
       Must first use hasNext().
    */
    public Object next() {
	Node tmp = current;
	current = current.next;
	return tmp.item;
    }

    public boolean hasNext() {
	return (current != null);
    }


}
