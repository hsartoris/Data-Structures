/**
   Interface for a list.

   @author Sven Anderson
 */


public interface List<T> {
    /**
       @returns head of list.  T is removed from list.
    */
    public T head();

    /**
       @returns tail of list.  T is removed from list.
    */
    public T tail();

    
    /**
       @ returns true if list is empty.
    */
    public boolean isEmpty(); 

    /**
       Adds obj to end of list.
    */
    public void append(T obj);

    /**
       Adds obj to head of list.
    */
    public void prepend(T obj);

    /**
     * Make the queue empty.
     */
    public void clear( );

    /**
       @returns num elements in list
    */
    public int size();
       
}
