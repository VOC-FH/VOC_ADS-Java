package kapitel_3.vl;

public class DList {
	protected Node head = null; // The head of the list.
	protected Node tail = null; // The tail of the list.
	
	protected static class Node { // Double chained nodes - a recursive data structure.
		public Node next = null;          // Reference to the next node.
		public Node prev = null;		  // Reference to the previous node.
		public Object data = null;		  // Reference to the stored data set.
		
		public Node(Node prev, Object data, Node next) { // Construct a new node by
			this.data = data;					         // storing the data set,
			this.next = next;					         // and refer the next and
			this.prev = prev;					         // previous node.
		}
	}

	public void prepend(Object data) { // Store a data set at the beginning of the list.
		head = new Node(null, data, head); // Do this by prepending a new node.
		if (tail == null) {        		   // If this is the first node in the list
			tail = head;           		   // then this node is the tail as well.
		} else {		           		   // Are there already nodes in the list?
			head.next.prev = head; 		   // Yes, thus enchain them consistently!
		}
	}
	
	public void append(Object data) { // Store a data set at the end of the list.
		tail = new Node(tail, data, null); // Do this by appending a new node.
		if (head == null) {   	   		   // If this is the first node in the list
			head = tail; 		   		   // then this node is the head as well.
		} else {         		   		   // Are there already nodes in the list?
			tail.prev.next = tail; 		   // Yes, thus enchain them consistently!
		}
	}
	
	public Object forwardSearch(IKey key) { // Forward search for an object matching
		Node current = head;               // a given key.
		
		while(current != null && !key.equals(current.data)) { // Iterate for all nodes 
									// in the list but interrupt if the object is found.
			current = current.next; // Not found! Jump to the next node.
		}   						// If found return the object otherwise return null.
		return (current != null) ? current.data : null;
	}

	public Object reverseSearch(IKey key) { // Reverse search for an object matching
		Node current = tail;               // a given key.
		
		while(current != null && !key.equals(current.data)) { // Iterate for all nodes 
									// in the list but interrupt if the object is found.
			current = current.prev; // Not found! Jump to the previous node.
		}							// If found return the object otherwise return null.		
		return (current != null) ? current.data : null;
	}
	
	private void removeNode(Node toRemove) { // Remove the passed node.
		if (toRemove != null) {              // Is there a node to remove?
			if (toRemove == head) {          // Is the node to remove the first node?
				head = toRemove.next;        // Yes, thus let head refer the second node.
			} else {					     // Is there a node before the node to remove?
				toRemove.prev.next = toRemove.next; // Yes, thus enchain it consistently.
			}
			if (toRemove == tail) {          // Is the node to remove the last node?
				tail = toRemove.prev;        // Yes, thus let tail refer the last but one.
			} else {					     // Is there a node behind the node to remove?
				toRemove.next.prev = toRemove.prev; // Yes, thus enchain it consistently.
			}
		}
	}
	
	protected Node forwardSearchNode(Object data) { // Forward search for the specific
		Node current = head;				// node referring the passed data object.
		
		while(current != null && data != current.data) { // Iterate for all nodes
			current = current.next; // but interrupt if the node has been found.
		}
		
		return current; // Return the found node or null otherwise.
	}
	
	public void forwardRemove(Object data) { // Forward remove the node referring data.
		Node toRemove = forwardSearchNode(data); // Search for the node to be removed.
		
		removeNode(toRemove); // Remove the desired node.
	}
	
	protected Node reverseSearchNode(Object data) { // Reverse search for the specific
		Node current = tail;                // node referring the passed data object.
		
		while(current != null && data != current.data) { // Iterate for all nodes
			current = current.prev; // but interrupt if the node has been found.
		}
		
		return current; // Return the found node or null otherwise.
	}
	
	public void reverseRemove(Object data) { // Reverse remove the node referring data.
		Node toRemove = reverseSearchNode(data); // Search for the node to be removed.
		
		removeNode(toRemove); // Remove the desired node.
	}
	
	private static class DListIterator extends IBIterator { // An iterator
			// for retrieving all data sets of the list. It's a bidirectional iterator
	    	// because we can go forward and backward in a double linked list.
		Node currentForward = null; // Forward reference to a specific current node.
		Node currentReverse = null; // Reverse reference to a specific current node.
		
		public DListIterator(Node start) { // Create a new Iterator-Object
			currentForward = currentReverse = start; // and initialize the start-node.
		}
		
		public boolean hasNext() {         // Is there a further data set in the list?
			return currentForward != null; // If yes return true else return false.
		}

		public boolean hasPrevious() {     // Is there a further data set in the list?
			return currentReverse != null; // If yes return true else return false.
		}
		
		public Object next() {  	              // Fetch the next data set.
			Object data = currentForward.data;    // Retrieve the data set
			currentForward = currentForward.next; // and move on to the next node.
			// Update currentReverse in a way to stay consistent.
			currentReverse = (currentForward != null) ? currentForward : currentReverse;
			return data;
		}
		
		public Object previous() {  	          // Fetch the previous data set.
			Object data = currentReverse.data;    // Retrieve the data set
			currentReverse = currentReverse.prev; // and move on to the next node.
			// Update currentForward in a way to stay consistent.
			currentForward = (currentReverse != null) ? currentReverse: currentForward;
			return data;
		}
	}

	public IFIterator fIterator() {     // Create a new forward iterator 
		return new DListIterator(head); // and let it start from the tail of the list.
	}
	
	public IRIterator rIterator() {     // Create a new reverse iterator 
		return new DListIterator(tail); // and let it start from the tail of the list.
	}
}
