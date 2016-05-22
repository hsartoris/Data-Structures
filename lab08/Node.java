


class Node {
    Object item;
    Node next;
    
    public Node() {
	this(null,null);
    }
    
    public Node(Object e, Node n) {
	item = e;
	next = n;
    }
}

