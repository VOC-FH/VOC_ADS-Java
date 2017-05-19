package lecture.chapter03.generics;

public class SearchTree<T> extends BTree<T> {
    protected IComparator<T> comparator = null; // A comparator used to compare data sets
    
    public SearchTree(IComparator<T> comparator) { // Create a SearchTree which uses
        this.comparator = comparator;           // a specific comparator
    }
    
    protected Node<T> insert(Node<T> current, Node<T> newNode) { // Insert a data set
        if (current == null) { // If a sub-tree is empty
            current = newNode; // the new node becomes the new sub-tree
        } else {
            switch (comparator.compare(current.data, newNode.data)) { // Where to insert?
            case 1:                                           // Insert on the left
                current.left = insert(current.left, newNode); // The new node becomes the
                current.left.parent = current; // left sub-tree of current and current in
                break;                         // turn becomes the parent of the new node
            case 0: case -1:                                    // Insert at the right
                current.right = insert(current.right, newNode); // The new node becomes the
                current.right.parent = current; // right sub-tree of current and current in
                break;                          // turn becomes the parent of the new node
            }
        }
        return current; // Return the sub-tree
    }
    
    public void insert(T data) { // Trigger a insertion of a data set starting
        root = insert(root, new Node<T>(null, data, null)); // at the root of the tree
    }
    
    protected Node<T> binarySearch(Node<T> current, IKey<T> key) { // Binary search
        Node<T> found = null;
        
        if (current != null) { // If the sub-tree is not empty
            if (key.matches(current.data)) { // Look at the root of this sup-tree. Does it
                found = current;            // contain the requested data set?
            } else {                        // No? Try to find it in an sub-tree
                switch (comparator.compare(current.data, key)) { // Where to search?
                case 1:                                       // Search on the left
                    found = binarySearch(current.left, key);  // Search to the left
                    break;
                case 0: case -1:                              // Search on the right
                    found = binarySearch(current.right, key); // Search to the right
                    break;
                }
            }
        }
        return found; // Return either null or the reference to the requested data set
    }

    public T binarySearch(IKey<T> key) {    // Trigger searching in the search-tree
        Node<T> found = binarySearch(root, key); // starting at the root
        
        return (found != null) ? found.data : null; // Return either null or the data set
    }
    
    public T search(IKey<T> key) {
        return binarySearch(key);
    }
    
    protected static <T> Node<T> searchSmallest(Node<T> node) { // Look for the smallest data set
        if (node != null) {                    // starting at the given node
            while(node.left != null) {         // Iterate to the leftmost node
                node = node.left;              // This is the smallest data set
            }
        }
        return node;
    }
    
    protected static <T> Node<T> searchLargest(Node<T> node) { // Look for the largest data set
        if (node != null) {                   // starting at the given node
            while(node.right != null) {       // Iterate to the rightmost node
                node = node.right;            // This is the smallest data set
            }
        }
        return node;
    }
    
    protected static <T> Node<T> replaceRoot(Node<T> toRemove) {          // Replace a (sub)-root
        Node<T> replacementNode = null;
        
        if (toRemove != null) {
            replacementNode = searchSmallest(toRemove.right);   // by exchanging its data
            if (replacementNode == null) {                      // set with an maximal one
                replacementNode = searchLargest(toRemove.left); // of the left or right
            }                                                   // sub-tree. Return the ref
            exchangeDatasets(toRemove, replacementNode);        // to the maximal node.
        }
        return (replacementNode != null) ? replacementNode : toRemove;
    }

    protected void remove(Node<T> toRemove) {  // Remove a node
        if (toRemove != null) {             // Is there a node to remove? Exchanging its
            toRemove = replaceRoot(toRemove); // data set with an maximal one of the
            removeLeaf(toRemove);             // left or right sub-tree         
        }                                     // Then remove this node for real
    }
    
    public boolean remove(IKey<T> key) {            // Remove a data set assoc. with a key
        Node<T> toRemove = binarySearch(root, key); // Search for the controlling node
        remove(toRemove);                        // Remove the controlling node
        
        return toRemove != null;
    }
    
    public boolean remove(T data) {
        ReferenceKey refKey = new ReferenceKey(data);
        Node<T> toRemove = binarySearch(root, refKey);
        remove(toRemove);
        
        return toRemove != null;
    }
}
