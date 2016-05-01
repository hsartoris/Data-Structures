
public class ListEmptyException extends RuntimeException {
    public static final long serialVersionUID = 0L;
    public ListEmptyException() {
	super();
    }

    public ListEmptyException(String msg) {
	super(msg);
    }

}
