package kapitel_3.vl;

public class SList {
	protected Node head = null; // The head of the list.
	
	protected static class Node { // Single chained nodes - a recursive data structure.
		public Node next = null;		  // Reference to the next node.
		public Object data = null;		  // Reference to the stored data set.
		
		public Node(Object data, Node next) { // Construct a new node by
			this.data = data;		          // storing the data set,
			this.next = next;                 // and refer the next node.
		}
	}

	public void prepend(Object data) { // Store a data set at the beginning of the list.
		head = new Node(data, head);   // Do this by prepending a node to the list.
	}
	
	public Object search(IKey key) { // Search for an object matching a given key.
		Node current = head;        // current references one node at a time.
		
		while(current != null && !key.equals(current.data)) {  // Iterate for all nodes 
			  						// in the list but interrupt if the object is found.
			current = current.next; // Not found! Jump to the next node.
		}	
		                            // If found return the object else return null.
		return (current != null) ? current.data : null;
	}
	
	private void removeNextNode(Node prev) { // Remove the next node of the passed one.
		if (prev == null && head != null) {  // Shall we delete the first node?
			head = head.next;                // Yes, thus delete it!
		} else if (prev != null && prev.next != null){ // Is there a node to delete?
			prev.next = prev.next.next;                // Yes, thus delete it!
		}
	}
	
	private Node searchForPrevNode(Object data) { // Search for the node prior to the
		Node current = head;                      // node storing the passed object.
		Node prev = null;
		
		while(current != null && current.data != data) { // Iterate for all nodes in
			prev = current;                               // the list as long as a node
			current = current.next;                       // stores the passed object.
		}            // Return null if the list is empty or the searched node is head.
		return prev; // Return the last node if the searched node is not in the list.
	}
	
	public void remove(Object data) {        // Remove the node storing the object.
		Node prev = searchForPrevNode(data); // Search for the node prior to the one
		                                     // to bee removed.
		removeNextNode(prev); // Remove the desired node next to prev.
	}
	
	private static class SListIterator implements IFIterator { // An iterator for
		    // retrieving all data sets of the list. It's a ForwardIterator because 
		    // we just can go in forward direction in a single linked list.
		private Node current = null; // Reference to a specific current node.
		
		public SListIterator(Node start) { // Create a new Iterator-Object
			current = start;               // and initialize node to start from.
		}
		
		public boolean hasNext() {  // Is there a further data set in the list?
			return current != null; // If yes return true else return false.
		}
		
		public Object next() {  	    // Fetch the next data set from the list.
			Object data = current.data; // Retrieve the data set from the current node,
			current = current.next;   	// move on to the next node, and
			return data;				// return the data set.
		}
	}
	
	public IFIterator iterator() {      // Create a new Iterator 
		return new SListIterator(head); // and let it start from the head of the list.
	}	
}
