/**
   Classes that support the Visitable interface must
   provide a visit method that takes an object parameter.  The 
   visit method can modify or display the object parameter and thereby change it
   in whatever traversal is applied to the current data type.
*/
public interface Visitable<AnyType> {
    public void visit(AnyType o);
}
