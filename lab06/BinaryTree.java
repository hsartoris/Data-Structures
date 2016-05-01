/**
   ADT for Binary Trees.  

   How will we determine what visit() method is applied during
   traversals?  Users will want to do different things for each
   traversal, so visit() needs to be a parameter that the user
   selects.
 */

public interface BinaryTree<T> {

    public boolean isEmpty();
    public int height();
    public T root();

    public void preOrder();
    public void inOrder();
    public void postOrder();

    public void addElement(T o); // insert o into tree
    public boolean getElement(T o); // true if o is in tree
}
