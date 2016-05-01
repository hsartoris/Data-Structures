/**
   Tests the List class.
*/

public class TestList {

    public static void main(String[] args) {
	List<Integer> l = new LinkedList<Integer>();
	System.out.println("list = " + l);	    

	// insert 10 Integers
	for (int i= 1; i < 10; i++) {
	    l.prepend(new Integer(i));
	    System.out.println("list = " + l + " is of size " + l.size());	    	    
	}

	// look for some integers
	for (int i= -10; i < 10; i+=3 ) {
	    Integer found = l.find(new Integer(i));
	    if (found != null) {
		System.out.println("found: " + found);	    	    
	    }
	}
	

	// delete items
	for (int i= 0; i < 5; i++ ) {
	    int r = (int)(Math.random() * 20);
	    System.out.println("Removing: " + r);
	    if (null == l.remove(new Integer(r)))
		System.out.println("item not found: " + r);
	    else
		System.out.println("list = " + l);
	}
	




    }
    
}
