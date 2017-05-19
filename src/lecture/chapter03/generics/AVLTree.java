package lecture.chapter03.generics;

public class AVLTree<T> extends SearchTree<T> {
    protected static class Node<T> extends BTree.Node<T> { // with an updated Node, storing also
        public int balance = 0;                             // a balance, the difference of the
                                                     // height of its sub-trees
        public Node(Node<T> left, T data, Node<T> right) {
            super(left, data, right);
        }
    }
    
    public AVLTree(IComparator<T> comparator) { // A comparator for the SearchTree
        super(comparator);
    }
    
    private void updateParents(Node<T> oldCurrentRoot, Node<T> newCurrentRoot) {
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
    
    private Node<T> rotateLeft(Node<T> oldCurrentRoot) { // The rotation to the left
        Node<T> newCurrentRoot = (Node<T>) oldCurrentRoot.right; // Determine the new current root
        
        oldCurrentRoot.right = newCurrentRoot.left;      // Let the left child of the new
        if (newCurrentRoot.left != null) {               // current root be the new right
            newCurrentRoot.left.parent = oldCurrentRoot; // child of the old current root
        }                                                // and proper update the parent
        newCurrentRoot.left = oldCurrentRoot; // The old current root becomes the left
                                              // child of the new current root
        oldCurrentRoot.balance = oldCurrentRoot.balance - 1 // Update all balances
                - Math.max(0, newCurrentRoot.balance);      // according to the theory
        newCurrentRoot.balance = newCurrentRoot.balance - 1 
                + Math.min(0, oldCurrentRoot.balance);

        updateParents(oldCurrentRoot, newCurrentRoot);   // Proper connect the balanced
                                                         // sub-tree to the whole tree
        return newCurrentRoot;                           // Deliver the new current root
    }
    
    private Node<T> rotateRight(Node<T> oldCurrentRoot) { // The rotation to the right
        Node<T> newCurrentRoot = (Node<T>) oldCurrentRoot.left; // Determine the new current root
        
        oldCurrentRoot.left = newCurrentRoot.right;       // Let the right child of the new
        if (newCurrentRoot.right != null) {               // current root be the new left
            newCurrentRoot.right.parent = oldCurrentRoot; // child of the old current root
        }                                                 // and proper update the parent
        newCurrentRoot.right = oldCurrentRoot; // The old current root becomes the right
                                               // child of the new current root
        oldCurrentRoot.balance = oldCurrentRoot.balance + 1 // Update all balances
                - Math.min(0, newCurrentRoot.balance);      // according to the theory
        newCurrentRoot.balance = newCurrentRoot.balance + 1 
                + Math.max(0, oldCurrentRoot.balance);
        
        updateParents(oldCurrentRoot, newCurrentRoot);    // Proper connect the balanced
                                                          // sub-tree to the whole tree
        return newCurrentRoot;                            // Deliver the new current root
    }
    
    private Node<T> balanceLeftLoaded(Node<T> currentRoot) { // Balance a left-loaded
        Node<T> newCurrentRoot = null;                    // tree
        
        if (currentRoot.balance == -2) {                   // Is there anything to do?
            switch(((Node<T>) currentRoot.left).balance) {    // Is the AVL-requirement
            case -1:                                       // injured?
                newCurrentRoot = rotateRight(currentRoot); // Yes, so let the balance of
                break;                                     // the left sub-tree chose
            case 0:                                        // one of the three possible
                newCurrentRoot = rotateRight(currentRoot); // cases and balance the tree
                break;                                     // according to the theory
            case +1:
                rotateLeft((Node<T>) currentRoot.left);
                newCurrentRoot = rotateRight(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    private Node<T> balanceRightLoaded(Node<T> currentRoot) { // Balance a right-loaded
        Node<T> newCurrentRoot = null;                     // tree
        
        if (currentRoot.balance == 2) {                   // Is there anything to do?
            switch(((Node<T>) currentRoot.right).balance) {  // Is the AVL-requirement
            case +1:                                      // injured?
                newCurrentRoot = rotateLeft(currentRoot); // Yes, so let the balance of
                break;                                    // the right sub-tree chose
            case 0:                                       // one of the three possible
                newCurrentRoot = rotateLeft(currentRoot); // cases and balance the tree
                break;                                    // according to the theory
            case -1:
                rotateRight((Node<T>) currentRoot.right);
                newCurrentRoot = rotateLeft(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    protected Node<T> balance(Node<T> currentRoot) { // If necessary try to balance the
        Node<T> newCurrentRoot = null;          // tree
        
        switch(currentRoot.balance) { // The balance of the current root
        case -2:                      // determines if the tree is left or
            newCurrentRoot = balanceLeftLoaded(currentRoot); // right loaded
            break;                    // Accordingly balance a left loaded
        case +2:                      // or a right loaded tree
            newCurrentRoot = balanceRightLoaded(currentRoot);
            break;
        }
        return newCurrentRoot; // Deliver the new current root
    }

    protected void grownTo(Node<T> node) { // Message to all parents that a sub
        Node<T> parent = (Node<T>) node.parent;     // tree indicated by the passed node has
                                              // grown due to the insertion of a new node
        if (parent != null) {                 // Stop at the root (anchor of recursion)
            if (node.isLeftChild()) {         // Update the balance of the parent
                parent.balance--;             // according to the side on which the
            } else if (node.isRightChild()) { // sub-tree has grown
                parent.balance++;
            }
            switch (parent.balance) { // Let the balance of the parent decide if
            case -2: case +2:         // the AVL-requirement is injured or not
                balance(parent);      // If injured, balance the tree at the
                break;                // parent node
            case -1: case +1:         // If it's not injured, but the tree is not
                grownTo(parent);      // perfectly balanced, report the growth of
                break;                // the tree up to the next parent
            }
        }
    }

    public void insert(T data) {                 // The overwritten insert-
        Node<T> newAVLNode = new Node<T>(null, data, null); // method reports the growth
        root = insert(root, newAVLNode);              // of the tree up to the
        grownTo(newAVLNode);                          // parents
    }
    
    protected void shrunkBy(Node<T> node) {       // Message to all parents that a sub
        Node<T> parent = (Node<T>) node.parent; // tree indicated by the passed node has
                                                // shrunken due to the removal of a node
        if (parent != null) {                   // Stop at the root (anchor of recursion)
            if (node.isLeftChild()) {           // Update the balance of the parent
                parent.balance++;               // according to the side on which the
            } else if (node.isRightChild()) {   // sub-tree has shrunken
                parent.balance--;
            }
            switch (parent.balance) {           // Let the balance of the parent decide if
            case -2: case +2:                   // the AVL-requirement is injured or not
                Node<T> newParent = balance(parent); // If injured, balance the tree at the
                if (newParent.balance == 0) {     // parent node and if the balanced
                    shrunkBy(newParent);          // tree is also shrunken report this
                }                                 // up to the next parent
                break;
            case 0:               // The tree is shrunken due the removal of a node but
                shrunkBy(parent); // the AVL-requirement is not injured. Thus, report the
                break;            // contraction up to the next parent
            }
        }
    }
    
    protected void remove(Node<T> toRemove) { // The overwritten remove-method reports the
        if (toRemove != null) {            // contraction of a sub-tree up to the parents
            toRemove = (Node<T>) replaceRoot(toRemove);
            shrunkBy((Node<T>) toRemove);
            removeLeaf(toRemove);
        }
    }
    
    protected static <T> boolean isBalanceValid(Node<T> node) { // Check if the tree is a valid
        boolean answer = true;                      // AVL-Tree, e.g. the balances of
                                                    // the nodes reflects the real
        if (node != null) {                         // hight of its sub-trees
            if (height(node.right) - height(node.left) != node.balance) {
                answer= false;
            } else {
                answer = isBalanceValid((Node<T>) node.right) 
                        && isBalanceValid((Node<T>) node.left);
            }
        }
        return answer;
    }
    
    public boolean areBalancesValid() {
        return isBalanceValid((Node<T>) root);
    }
    
    protected static <T> boolean isAVLTree(Node<T> node) { // Check if the tree is
        boolean answer = true;                          // correctly balanced to be a
                                                        // valid the AVL-requirement
        if (node != null) {
            if (node.balance < -1 || node.balance > 1) {
                answer = false;
            } else {
                answer = isAVLTree((Node<T>) node.right) 
                        && isAVLTree((Node<T>) node.left); 
            }
        }
        return answer;
    }
    
    public boolean isAVLTree() {
        return isAVLTree((Node<T>) root);
    }
}
