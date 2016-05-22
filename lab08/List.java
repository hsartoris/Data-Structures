/**
   Interface for a list.

   @author Sven Anderson 3/4/2002
 */



public interface List {

    /**
       @returns head of list.  Object is removed from list.
    */
    public Object head();

    /**
       @returns Tail of list. Object is removed from list.
    */
    public Object tail();
    
    /**
       @ returns true if list is empty.
    */
    public boolean isEmpty(); 

    /**
       Adds obj to end of list.
    */
    public void append(Object obj);

    /**
       Adds obj to head of list.
    */
    public void prepend(Object obj);

    /**
       Return iterator to access complete list.
    */
    public ListIterator getElements();
    

    /**
       @return index of first occurence of obj.
       return -1 if obj not found.
    */
    public int indexOf(Object obj);

}
