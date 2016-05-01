/** 
    Maintains an ordered list of events.  The events are ordered from
    earliest to latest.
    @author: Hayden Sartoris

  */
import java.util.*;
import java.util.Iterator;

public class EventList extends LinkedList<Event> {

    public EventList() {
	super();
    }

    /**
       Get next event at head of queue.
    */
    public Event getEvent() {
	return head();
    }

    class ElistIterator implements Iterator<Event> {
    	private Node<Event> current = new Node<Event>(null, head); // so as to just move right into getting next etc
    	public ElistIterator() {
    		
    	}
    	
		public boolean hasNext() {
			if (current.next != null) {
				return true;
			}
			return false;
		}

		public Event next() throws NoSuchElementException {
			//need to figure out how to return the head once
			current = current.next;
			if (current == null) {
				throw new NoSuchElementException();
			}
			return current.item;
		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
    }
    
    public Iterator<Event> iterator() {
    	return new ElistIterator();
    }

    /**
       Insert event e into the event list according 
       to e's timestamp.  
    */
    public void insert(Event e) {
    	/* Four basic cases: insert at 1. beginning 2. middle* 3. end 4. empty list
    	 * Middle is really two: equivalent element is either present or not.
    	 * Also if the list is only 1 element; that also threw me off initially
    	 * 
    	 */
    	Node<Event> prev;
    	Node<Event> curr = head;
    	if (size == 0) {
    		// case empty list
    		append(e);
    		return;
    	}
    	if (e.compareTo(head.item) < 0) {
    		// case beginning of list
    		// head = new Node<Event>(e, head);
    		prepend(e);
    		return;
    	}
    	int processed = 0;
    	while (size - processed > 0) {
    		prev = curr;
    		curr = curr.next;
    		processed++;
    		if (processed == size) {
    			// case end of list
    			append(e);
    			break;
    		} else if (e.compareTo(curr.item) < 1) {
     			// case somewhere in the middle
     			Node<Event> newNode = new Node<Event>(e, curr);
     			prev.next = newNode;
     			break;
     		}
    	}
    }



    public String toString() {
	Node<Event> curr = head;
	String tmp = "";
	while (curr != null) {
	    tmp = tmp + " " + curr.item;
	    curr = curr.next;
	}
	return tmp;
    }

    /**
       @param el EventList to check.  Outputs error if any two items
       in the list are not in ascending order.
    */

    
    
    static void checkOrder(EventList el) {
	if (el.size() < 2) return; // nothing to compare
	Iterator<Event> iter = el.iterator();
	Event e1 = iter.next();
	while (iter.hasNext()) {
	    Event e2 = iter.next();
	    if (e1.compareTo(e2) > 0) System.out.println("Error in order of list.");
	    e1 = e2; // advance the pointer
	}
    }


    /**
       Test the event list.
    */
    public static void main(String[] args) {
		EventList elist = new EventList();
		for (int i = 1; i < 25; i++) {
			if (i != 13) {
				elist.append(new Event(i));
			}
		}
		elist.insert(new Event(0)); // case beginning of list
		
		elist.insert(new Event(13)); //	one of these two is pointless
		elist.insert(new Event(15)); // depending on how you look at it
		
		elist.insert(new Event(26)); // case end of list
		System.out.println(elist);
		checkOrder(elist);
		elist.clear();
		elist.insert(new Event(1)); // case empty list
		elist.insert(new Event(2));
		System.out.println(elist);
		elist.tail();
		elist.insert(new Event(1));
		System.out.println(elist);
		elist.head();
		elist.head();
		elist.insert(new Event(1));
		System.out.println(elist);
	
		// TODO: Add full testing!

    }

}
