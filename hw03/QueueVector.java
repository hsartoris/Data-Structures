import java.util.Vector;

public class QueueVector<AnyType> extends Vector<AnyType> implements Queue<AnyType> {
    // Implement a Queue, based on the Vector. Insertions happen at the end (high position numbers)
    // and removals at the front (low position numbers).
    
    public void enqueue( AnyType x ) {
        add(x);
    }
    
    public AnyType getFront( ) {
        return (AnyType)get(0);
    }
    
    public AnyType dequeue( ) {
        AnyType returnThis = remove(0);
        return returnThis;
    }
    
    // boolean isEmpty( );  // this is inherited directly
    
    public void makeEmpty( ) {
        clear();
    }


}
