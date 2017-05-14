package kapitel_3.vl;

public class SearchTree extends BTree {
    protected IComparator comparator = null; // A comparator used to compare data sets
    
    public SearchTree(IComparator comparator) { // Create a SearchTree which uses
        this.comparator = comparator;           // a specific comparator
    }
    
    protected Node insert(Node current, Node newNode) { // Insert a data set
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
    
    public void insert(Object data) { // Trigger a insertion of a data set starting
        root = insert(root, new Node(null, data, null)); // at the root of the tree
    }
    
    protected Node binarySearch(Node current, IKey key) { // Binary search
        Node found = null;
        
        if (current != null) { // If the sub-tree is not empty
            if (key.matches(current.data)) { // Look at the root of this sup-tree. Does it
                found = current;             // contain the requested data set?
            } else {                         // No? Try to find it in an sub-tree
                switch (comparator.compare(current.data, key)) { // Where to search?
                case 1:                                 // Search on the left
                    found = binarySearch(current.left, key);  // Search to the left
                    break;
                case 0: case -1:                        // Search on the right
                    found = binarySearch(current.right, key); // Search to the right
                    break;
                }
            }
        }
        return found; // Return either null or the reference to the requested data set
    }

    public Object search(IKey key) { // Trigger searching in the search-tree
        Node found = binarySearch(root, key); // starting at the root
        
        return (found != null) ? found.data : null; // Return either null or the data set
    }
    
    protected static Node searchSmallest(Node node) { // Look for the smallest data set
        if (node != null) {                    // starting at the given node
            while(node.left != null) {         // Iterate to the leftmost node
                node = node.left;              // This is the smallest data set
            }
        }
        return node;
    }
    
    protected static Node searchLargest(Node node) { // Look for the largest data set
        if (node != null) {                   // starting at the given node
            while(node.right != null) {       // Iterate to the rightmost node
                node = node.right;            // This is the smallest data set
            }
        }
        return node;
    }
    
    protected static Node replaceRoot(Node toRemove) { // Replace a (sub)-root data set
        Node replacementNode = null;
        
        if (toRemove != null) {
            replacementNode = searchSmallest(toRemove.right);   // by exchanging its data
            if (replacementNode == null) {                      // set with an maximal one
                replacementNode = searchLargest(toRemove.left); // of the left or right
            }                                                   // sub-tree. Return the ref
            exchangeDatasets(toRemove, replacementNode);        // to the maximal node.
        }
        return (replacementNode != null) ? replacementNode : toRemove;
    }

    protected Node remove(Node toRemove) { // Remove a node
        if (toRemove != null) {               // Does the node exist?
            toRemove = replaceRoot(toRemove); // Exchanging its data set with an extreme
            removeLeaf(toRemove);		      // one of the right or left sub-tree         
        }									  // Then remove this node for real
        return toRemove;                      // Return actually removed node
    }
    
    public boolean remove(Object data) {           // Remove a data set
        ReferenceKey key = new ReferenceKey(data); // Create a ReferenceKey
        Node toRemove = binarySearch(root, key);   // Search for the controlling node
        toRemove = remove(toRemove);               // Remove the controlling node
        
        return toRemove != null;
    }
    
    private void updateParents(Node oldCurrentRoot, Node newCurrentRoot) {
        if (oldCurrentRoot.isLeftChild()) {               // After a rotation update also
            oldCurrentRoot.parent.left = newCurrentRoot;  // all parent-references so that
        } else if (oldCurrentRoot.isRightChild()) {       // the tree is correctly
            oldCurrentRoot.parent.right = newCurrentRoot; // connected again
        }
        newCurrentRoot.parent = oldCurrentRoot.parent;
        oldCurrentRoot.parent = newCurrentRoot;
        
        if (oldCurrentRoot == root) { // Is the oldCurrentRoot the root of the tree then
            root = newCurrentRoot;    // the newCurrentRoot becomes the root of the tree.
        }
    }
    
    protected Node rotateLeft(Node oldCurrentRoot) { // The rotation to the left
        Node newCurrentRoot = oldCurrentRoot.right;      // Determine the new current root
        
        oldCurrentRoot.right = newCurrentRoot.left;      // Let the left child of the new
        if (newCurrentRoot.left != null) {               // current root be the new right
            newCurrentRoot.left.parent = oldCurrentRoot; // child of the old current root
        }                                                // and proper update the parent
        newCurrentRoot.left = oldCurrentRoot; // The old current root becomes the left
                                              // child of the new current root
        updateParents(oldCurrentRoot, newCurrentRoot);   // Proper connect the balanced
                                                         // sub-tree to the whole tree
        return newCurrentRoot;                           // Deliver the new current root
    }
    
    
    protected Node rotateRight(Node oldCurrentRoot) { // The rotation to the right
        Node newCurrentRoot = oldCurrentRoot.left;        // Determine the new current root
        
        oldCurrentRoot.left = newCurrentRoot.right;       // Let the right child of the new
        if (newCurrentRoot.right != null) {               // current root be the new left
            newCurrentRoot.right.parent = oldCurrentRoot; // child of the old current root
        }                                                 // and proper update the parent
        newCurrentRoot.right = oldCurrentRoot; // The old current root becomes the right
                                               // child of the new current root
        updateParents(oldCurrentRoot, newCurrentRoot);    // Proper connect the balanced
                                                          // sub-tree to the whole tree
        return newCurrentRoot;                            // Deliver the new current root
    }
}
