package kapitel_3.vl;

import uebung_3.ue.Queue;

public class BTree {
	protected Node root = null;	// The root of the tree
	
	protected static class Node {
		public Node parent = null;	// Reference to the parent node
		public Node right = null;	// Reference to the right child-node
		public Node left = null;	// Reference to the left child-node
		public Object data = null;	// Reference to the stored data set
		
		public Node(Node left, Object data, Node right) { // Construct a new node by
			this.data = data;					          // storing the data set
			this.left = left;					          // and refer to the left and
			this.right = right;					          // right child-node
		}
		
		boolean isLeftChild() {
			return parent != null && parent.left == this;
		}
		
		boolean isRightChild() {
			return parent != null && parent.right == this;
		}
	}
	
	private static void depthFirstPreOrder(Node current, IWorker worker) { // Traverse
		if (current != null) {						   // the tree pre-order depth-first
			worker.work(current.data);				   // Process the data set
			depthFirstPreOrder(current.left, worker);  // Recursive call with left child
			depthFirstPreOrder(current.right, worker); // Recursive call with right child
		}
	}

	public void depthFirstPreOrder(IWorker worker) { // Trigger a depth-first pre-order
		depthFirstPreOrder(root, worker);			 // traversal starting at the root
	}                                                // of the tree
	
	private static void depthFirstInOrder(Node current, IWorker worker) { // Traverse
		if (current != null) {						  // the tree in-order depth-first
			depthFirstInOrder(current.left, worker);  // Recursive call with left child
			worker.work(current.data); 				  // Process the data set
			depthFirstInOrder(current.right, worker); // Recursive call with right child
		}
	}

	public void depthFirstInOrder(IWorker worker) { // Trigger a depth-first in-order
		depthFirstInOrder(root, worker);            // traversal starting at the root
	}												// of the tree
	
	private static void depthFirstPostOrder(Node current, IWorker worker) { // Traverse
		if (current != null) {							// the tree post-order depth-first
			depthFirstPostOrder(current.left, worker);  // Recursive call with left child
			depthFirstPostOrder(current.right, worker); // Recursive call with right child
			worker.work(current.data);                  // Process the data set
		}
	}
	
	public void depthFirstPostOrder(IWorker worker) { // Trigger a depth-first post-order
		depthFirstPostOrder(root, worker);            // traversal starting at the root 
	}												  // of the tree
	
	public void breadthFirst(IWorker worker) {     // Traverse the tree breadth-first
		Queue queue = new Queue();			       // The helper-queue
		
		if (root != null) {
			queue.enqueue(root);					   // Enqueue the root of the tree
		}
		while (!queue.empty()) {                   // Iterate as long as queue is not empty
			Node current = (Node) queue.dequeue(); // Fetch a node from the queue
			if (current.left != null) {            // If the left child of this node exists
				queue.enqueue(current.left);       // enqueue this child
			}
			if (current.right != null) {           // If the right child of the node exists
				queue.enqueue(current.right);      // enqueue it also
			}
			worker.work(current.data);             // Process the data set
		}
	}
	
	private static Object depthFirstPreOrderSearch(Node current, IKey key) { // Search for
		Object data = null; // a data set based on the depth-first pre-order traversal
		
		if (current != null) { // We have not found the data set by reaching the bottom
			if (key.equals(current.data)) { // If the current node stores the requested
				data = current.data;        // data set remember its reference
			} else { // If the current node didn't store the requested data set
				data = depthFirstPreOrderSearch(current.left, key); // search the left
				if (data == null) { // If the data set wasn't found at the left try to find
					data = depthFirstPreOrderSearch(current.right, key); // it at the right
				}
			}
		}
		return data; // Return either null or the reference to the requested data set 
	}

	public Object depthFirstPreOrderSearch(IKey key) { // Trigger the depth-first pre-order
		return depthFirstPreOrderSearch(root, key);    // search process
	}
	
	private static Object depthFirstInOrderSearch(Node current, IKey key) { // Search for
		Object data = null; // a data set based on the depth-first pre-order traversal
		
		if (current != null) { // We have not found the data set by reaching the bottom
			data = depthFirstInOrderSearch(current.left, key); // Search at the left side
			if (data == null && key.equals(current.data)) { // Not found but current stores
				data = current.data; // the requested data set, store its reference
			} else if (data == null) { // Still not found - search at the right
					data = depthFirstInOrderSearch(current.right, key);
			}
		}
		return data; // Return either null or the reference to the requested data set
	}

	public Object depthFirstInOrderSearch(IKey key) { // Trigger the depth-first in-order
		return depthFirstInOrderSearch(root, key);    // search process
	}
	
	private static Object depthFirstPostOrderSearch(Node current, IKey key) { // Search for
		Object data = null; // a data set based on the depth-first pre-order traversal
		
		if (current != null) { // we have not found the data set by reaching the bottom
			data = depthFirstPostOrderSearch(current.left, key); // Search at the left side
			if (data == null) { // Not found? Than search also on the
				data = depthFirstPostOrderSearch(current.right, key); // right side
				if (data == null && key.equals(current.data)) { // Not found on the left
					data = current.data; // side but in current - remember the reference.
				}
			}
		}
		return data; // Return either null or the reference to the requested data set
	}
	
	public Object depthFirstPostOrderSearch(IKey key) { // Trigger the depth-first
		return depthFirstPostOrderSearch(root, key);    // post-order search process
	}
	
	public Object breadthFirstSearch(IKey key) {   // Search for a data set based on the
		Object data = null;                        // breadth-first traversal
		Queue queue = new Queue();                 // The helper-queue
		
		queue.enqueue(root);                       // Enqueue the root-node of the tree
		  										   // Proceed while the queue is not
		while (!queue.empty() && data == null) {   // empty and the data set was not found
			Node current = (Node) queue.dequeue(); // Fetch a node from the queue
			if (key.equals(current.data)) {        // Is this the node of the requested
				data = current.data;               // data set? Yes - store its reference
			} else {                               // No - enqueue the left and right
				if (current.left != null) {        // child-nodes, but only if they exist 
					queue.enqueue(current.left);
				}
				if (current.right != null) {
					queue.enqueue(current.right);
				}
			}
		}
		return data; // Return either null or the reference to the requested data set
	}
	
	protected void removeLeaf(Node toRemove) { // Remove a leaf or a half-leave from
		if (toRemove.isLeftChild()) {          // the tree.
			toRemove.parent.left = null;
			if (toRemove.right != null) {
				toRemove.parent.left = toRemove.right;
				toRemove.right.parent = toRemove.parent;
			} else if (toRemove.left != null) {
				toRemove.parent.left = toRemove.left;
				toRemove.left.parent = toRemove.parent;
			}
		} else if (toRemove.isRightChild()) {
			toRemove.parent.right = null;
			if (toRemove.left != null) {
				toRemove.parent.right = toRemove.left;
				toRemove.left.parent = toRemove.parent;
			} else if (toRemove.right != null) {
				toRemove.parent.right = toRemove.right;
				toRemove.right.parent = toRemove.parent;
			}
		} else {
			root = null;
		}
	}

	protected static void exchangeDatasets(Node node1, Node node2) {
		if (node1 != null && node2 != null) {
			Object data = node2.data;
			node2.data = node1.data;
			node1.data = data;
		}
	}
	
	protected static int height(Node node) { // Calculate the height of the tree 
		int height = 0;
		
		if (node != null) {
			int heightLeft = height(node.left);   // Calculate the height of the left
			int heightRight = height(node.right); // and right subtrees recursively
			  				// The resulting height is the height of the higher subtree 
			height = Math.max(heightLeft, heightRight) + 1; // plus one
		}
		return height; // Return the result
	}
	
	public int height() {    // Trigger the calculation of the
		return height(root); // height of the tree, starting at the root of the tree
	}
	
	protected static int depth(Node node) { // Calculate the depth of a node
		int depth = -1; // Set depth to -1, the result when no valid node was passed
		
		if (node != null) {         // Is the passed node valid?
			do {                    // Yes, thus loop as long as we reach the root
				depth++;            // For each edge increment depth
				node = node.parent; // Jump to the next level upwards
			} while (node != null); // Terminate the loop when the root node is reached
		}
		return depth;
	}
	
	private class BTreeIterator implements IFIterator { // Iterator, based on breadth-first
		private Queue queue = new Queue();              // A helper-queue
		
		public BTreeIterator(Node startNode) { // New iterator, starting at startNode
			if (startNode != null) {
				queue.enqueue(startNode);      // Enqueue the startNode
			}
		}
		
		public boolean hasNext() { // Is there a next data set in the tree
			return !queue.empty(); // This is the case if the queue is not empty
		}

		public Object next() { // Fetch the next data set from the tree
			Node current = (Node) queue.dequeue(); // Request a node from the queue
			if (current.left != null)         // If the left node exists
				queue.enqueue(current.left);  // enqueue it
			if (current.right != null)        // If the right node also exists
				queue.enqueue(current.right); // enqueue it also
			return current.data;              // Return the dequeued data set
		}
	}
	
	public IFIterator iterator() {      // Factory method for an IFIterator
		return new BTreeIterator(root); // Create a new iterator starting at the root
	}
}
