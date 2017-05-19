package lecture.chapter03.generics;

public class BTree<T> {
    protected Node<T> root = null; // The root of the tree
    
    protected static class Node<T> {
        public Node<T> parent = null;  // Reference to the parent node
        public Node<T> right = null;   // Reference to the right child-node
        public Node<T> left = null;    // Reference to the left child-node
        public T data = null;  // Reference to the stored data set
        
        public Node(Node<T> left, T data, Node<T> right) { // Construct a new node by
            this.data = data;                             // storing the data set
            this.left = left;                             // and refer to the left and
            this.right = right;                           // right child-node
        }
        
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }
        
        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }
        
        public boolean isRoot() {
            return parent == null;
        }
        
        public boolean isLeaf() {
            return left == null && right == null;
        }
        
        public boolean isHalfLeaf() {
            return left == null && right != null || left != null && right == null;
        }
    }
    
    protected class ReferenceKey implements IKey<T> {
        T data = null;
        
        public ReferenceKey(T data) {
            this.data = data;
        }
        
        public boolean matches(T data) {
            return this.data == data;
        }
        
    }
    
    private static <T> void depthFirstPreOrder(Node<T> current, IWorker<T> worker) { // Traverse
        if (current != null) {                         // the tree pre-order depth-first
            worker.work(current.data);                 // Process the data set
            depthFirstPreOrder(current.left, worker);  // Recursive call with left child
            depthFirstPreOrder(current.right, worker); // Recursive call with right child
        }
    }

    public void depthFirstPreOrder(IWorker<T> worker) { // Trigger a depth-first pre-order
        depthFirstPreOrder(root, worker);            // traversal starting at the root
    }                                                // of the tree
    
    private static <T> void depthFirstInOrder(Node<T> current, IWorker<T> worker) { // Traverse
        if (current != null) {                        // the tree in-order depth-first
            depthFirstInOrder(current.left, worker);  // Recursive call with left child
            worker.work(current.data);                // Process the data set
            depthFirstInOrder(current.right, worker); // Recursive call with right child
        }
    }

    public void depthFirstInOrder(IWorker<T> worker) { // Trigger a depth-first in-order
        depthFirstInOrder(root, worker);            // traversal starting at the root
    }                                               // of the tree
    
    private static <T> void depthFirstPostOrder(Node<T> current, IWorker<T> worker) { // Traverse
        if (current != null) {                          // the tree post-order depth-first
            depthFirstPostOrder(current.left, worker);  // Recursive call with left child
            depthFirstPostOrder(current.right, worker); // Recursive call with right child
            worker.work(current.data);                  // Process the data set
        }
    }
    
    public void depthFirstPostOrder(IWorker<T> worker) { // Trigger a depth-first post-order
        depthFirstPostOrder(root, worker);            // traversal starting at the root 
    }                                                 // of the tree
    
    public void breadthFirst(IWorker<T> worker) {     // Traverse the tree breadth-first
        Queue<Node<T>> queue = new Queue<Node<T>>();                 // The helper-queue
        
        if (root != null) {
            queue.enqueue(root);                   // Enqueue the root of the tree
        }
        while (!queue.empty()) {                   // Iterate as long as queue is not empty
            Node<T> current = queue.dequeue(); // Fetch a node from the queue
            if (current.left != null) {            // If the left child of this node exists
                queue.enqueue(current.left);       // enqueue this child
            }
            if (current.right != null) {           // If the right child of the node exists
                queue.enqueue(current.right);      // enqueue it also
            }
            worker.work(current.data);             // Process the data set
        }
    }
    
    protected static <T> Node<T> depthFirstPreOrderSearch(Node<T> current, IKey<T> key) { // Search for
        Node<T> foundNode = null; // a data set based on the depth-first pre-order traversal
        
        if (current != null) { // We have not found the data set by reaching the bottom
            if (key.matches(current.data)) { // If the current node stores the requested
                foundNode = current;        // data set remember its reference
            } else { // If the current node didn't store the requested data set
                foundNode = depthFirstPreOrderSearch(current.left, key); // search the left
                if (foundNode == null) { // If the data set wasn't found at the left try to find
                    foundNode = depthFirstPreOrderSearch(current.right, key); // it at the right
                }
            }
        }
        return foundNode; // Return either null or the reference to the requested data set 
    }

    public T depthFirstPreOrderSearch(IKey<T> key) { // Trigger the depth-first pre-order
        Node<T> foundNode = depthFirstPreOrderSearch(root, key);
        return foundNode != null ? foundNode.data : null;    // search process
    }
    
    protected static <T> Node<T> depthFirstInOrderSearch(Node<T> current, IKey<T> key) { // Search for
        Node<T> foundNode = null; // a data set based on the depth-first pre-order traversal
        
        if (current != null) { // We have not found the data set by reaching the bottom
            foundNode = depthFirstInOrderSearch(current.left, key); // Search at the left side
            if (foundNode == null && key.matches(current.data)) { // Not found but current stores
                foundNode = current; // the requested data set, store its reference
            } else if (foundNode == null) { // Still not found - search at the right
                    foundNode = depthFirstInOrderSearch(current.right, key);
            }
        }
        return foundNode; // Return either null or the reference to the requested data set
    }

    public T depthFirstInOrderSearch(IKey<T> key) { // Trigger the depth-first in-order
        Node<T> foundNode = depthFirstInOrderSearch(root, key);
        return foundNode != null ? foundNode.data : null;    // search process
    }
    
    protected static <T> Node<T> depthFirstPostOrderSearch(Node<T> current, IKey<T> key) { // Search for
        Node<T> foundNode = null; // a data set based on the depth-first pre-order traversal
        
        if (current != null) { // we have not found the data set by reaching the bottom
            foundNode = depthFirstPostOrderSearch(current.left, key); // Search at the left side
            if (foundNode == null) { // Not found? Than search also on the
                foundNode = depthFirstPostOrderSearch(current.right, key); // right side
                if (foundNode == null && key.matches(current.data)) { // Not found on the left
                    foundNode = current; // side but in current - remember the reference.
                }
            }
        }
        return foundNode; // Return either null or the reference to the requested data set
    }
    
    public T depthFirstPostOrderSearch(IKey<T> key) { // Trigger the depth-first
        Node<T> foundNode = depthFirstPostOrderSearch(root, key);
        return foundNode != null ? foundNode.data : null;    // post-order search process
    }
    
    public T search(IKey<T> key) {
        return depthFirstPreOrderSearch(key);
    }
    
    public T breadthFirstSearch(IKey<T> key) {   // Search for a data set based on the
        T data = null;                        // breadth-first traversal
        Queue<Node<T> > queue = new Queue<Node<T> >();                 // The helper-queue
        
        if (root != null) {
            queue.enqueue(root);                   // Enqueue the root-node of the tree
        }                                          // Proceed while the queue is not
        while (!queue.empty() && data == null) {   // empty and the data set was not found
            Node<T> current = queue.dequeue(); // Fetch a node from the queue
            if (key.matches(current.data)) {        // Is this the node of the requested
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
    
    protected void removeLeaf(Node<T> toRemove) { // Remove a leaf or a half-leave from
        if (toRemove.isLeftChild()) {             // the tree.
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

    protected static <T> void exchangeDatasets(Node<T> node1, Node<T> node2) {
        if (node1 != null && node2 != null) {
            T data = node2.data;
            node2.data = node1.data;
            node1.data = data;
        }
    }
    
    protected static <T> int height(Node<T> node) { // Calculate the height of the tree 
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
    
    protected static <T> int depth(Node<T> node) { // Calculate the depth of a node
        int depth = -1; // Set depth to -1, the result when no valid node was passed
        
        if (node != null) {         // Is the passed node valid?
            do {                    // Yes, thus loop as long as we reach the root
                depth++;            // For each edge increment depth
                node = node.parent; // Jump to the next level upwards
            } while (node != null); // Terminate the loop when the root node is reached
        }
        return depth;
    }
    
    private class BTreeIterator implements IFIterator<T> { // Iterator, based on breadth-first
        private Queue<Node<T>> queue = new Queue<Node<T>>();              // A helper-queue
        
        public BTreeIterator(Node<T> startNode) { // New iterator, starting at startNode
            if (startNode != null) {
                queue.enqueue(startNode);      // Enqueue the startNode
            }
        }
        
        public boolean hasNext() { // Is there a next data set in the tree
            return !queue.empty(); // This is the case if the queue is not empty
        }

        public T next() { // Fetch the next data set from the tree
            Node<T> current = queue.dequeue(); // Request a node from the queue
            if (current.left != null)         // If the left node exists
                queue.enqueue(current.left);  // enqueue it
            if (current.right != null)        // If the right node also exists
                queue.enqueue(current.right); // enqueue it also
            return current.data;              // Return the dequeued data set
        }
    }
    
    public IFIterator<T> iterator() {      // Factory method for an IFIterator
        return new BTreeIterator(root); // Create a new iterator starting at the root
    }
}
