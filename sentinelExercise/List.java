/**
   Interface for a list.
   @author Sven Anderson 3/27/2003
 */


public interface List<T> {

    /**
       @ returns true if list is empty.
    */
    public boolean isEmpty(); 


    /**
       @ returns number of elements in list
    */
    public int size(); 


    /**
       @returns head of list.  T is NOT removed from list.
    */
    public T headPeek();

    /**
       @returns Tail of list. T is NOT removed from list.
    */
    public T tailPeek();
    

    /**
       @returns head of list.  T is removed from list.
    */
    public T head();

    /**
       @returns Tail of list. T is removed from list.
    */
    public T tail();
    


    /**
       Adds obj to end of list.
       If obj is null it is not appended.
    */
    public void append(T obj);

    /**
       Adds obj to head of list.
       If obj is null it is not prepended.
    */
    public void prepend(T obj);

    /**
       @return object in list
       Returns null if object not found in list.
    */
    public T find(T obj);

    /**
       Remove first equivalent object from list.
       @return object that is removed from list.
       Returns null lif indicated object is not in list.
    */
    public T remove(T obj);

    /**
       @return string representation of the list
    */
    public String toString();

}
