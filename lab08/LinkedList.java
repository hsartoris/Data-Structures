/**
 * Implementation of List via singly linked nodes.
 * 
 * @author Sven Anderson 3/4/2002
 */

public class LinkedList implements List {
	Node head; // head of list
	Node tail; // tail of list

	/**
	 * Head and tail start of null.
	 */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * @returns true iff list is empty.
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * @returns head of list. Object is removed from list.
	 */
	public Object head() {
		if (isEmpty())
			throw new ListEmptyException();
		Object tmp = head.item;
		head = head.next;
		if (head == null)
			tail = null;
		return tmp;
	}

	/**
	 * @returns Tail of list. Object is removed from list.
	 */
	public Object tail() {
		if (isEmpty())
			throw new ListEmptyException();

		Object tmp = tail.item;

		// one item in list
		if (tail == head) {
			tail = head = null;
		} else {
			// reset tail to penultimate node
			tail = head;
			while (tail.next.next != null) {
				tail = tail.next;
			}
			tail.next = null; // remove ref to old tail
		}
		return tmp;
	}

	/**
	 * Adds obj to end of list.
	 */
	public void append(Object obj) {
		// List is empty, must create first node
		if (isEmpty()) {
			tail = head = new Node(obj, null);
		} else {
			Node newtail = new Node(obj, null);
			tail.next = newtail;
			tail = newtail;
		}
	}

	/**
	 * Adds obj to head of list.
	 */
	public void prepend(Object obj) {
		// List is empty, must create first node
		if (isEmpty()) {
			tail = head = new Node(obj, null);
		} else {
			Node newhead = new Node(obj, head);
			head = newhead;
		}
	}

	/**
	 * @return index of first occurence of obj. return -1 if obj not found.
	 */
	public int indexOf(Object obj) {
		int index = 0;

		if (isEmpty())
			return -1;

		Node current = head;
		while (current != null) {
			if (obj.equals(current.item))
				return index;
			index++;
			current = current.next;
		}

		return -1;
	}

	/**
	 * Enables access to entire list.
	 */
	public ListIterator getElements() {
		return new ListIterator(this);
	}

	/**
	 * Print entire list from head to tail.
	 */
	public void print() {
		Node tmp = head;
		while (tmp != null) {
			System.out.println(" " + tmp.item.toString());
			tmp = tmp.next;
		}

	}
}
