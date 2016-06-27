package kapitel_3.vl;

public class AVLTree extends SearchTree {         // An AVLTree is a SearchTree
    protected static class AVLNode extends Node { // with an updated Node, storing also
        protected int balance = 0;                // a balance, the difference of the
                                                  // heights of its sub-trees
        public AVLNode(Node left, Object data, Node right) {
            super(left, data, right);
        }
    }
    
    public AVLTree(IComparator comparator) { // A comparator for the SearchTree
        super(comparator);
    }
    
    protected AVLNode rotateLeft(AVLNode oldCurrentRoot) {
        AVLNode newCurrentRoot = (AVLNode) super.rotateLeft(oldCurrentRoot);
        
        oldCurrentRoot.balance = oldCurrentRoot.balance - 1 // Update all balances
                - Math.max(0, newCurrentRoot.balance);      // according to the theory
        newCurrentRoot.balance = newCurrentRoot.balance - 1 
                + Math.min(0, oldCurrentRoot.balance);
        
        return newCurrentRoot;
    }
    
    protected AVLNode rotateRight(AVLNode oldCurrentRoot) {
        AVLNode newCurrentRoot = (AVLNode) super.rotateRight(oldCurrentRoot);
        
        oldCurrentRoot.balance = oldCurrentRoot.balance + 1 // Update all balances
                - Math.min(0, newCurrentRoot.balance);      // according to the theory
        newCurrentRoot.balance = newCurrentRoot.balance + 1 
                + Math.max(0, oldCurrentRoot.balance);
        
        return newCurrentRoot;
    }
    
    private AVLNode balanceLeftLoaded(AVLNode currentRoot) { // Balance a left-loaded
        AVLNode newCurrentRoot = null;                       // tree
        
        if (currentRoot.balance == -2) {                   // Is there anything to do?
            switch(((AVLNode) currentRoot.left).balance) { // Is the AVL-requirement
            case -1:                                       // injured?
                newCurrentRoot = rotateRight(currentRoot); // Yes, so let the balance of
                break;                                     // the left sub-tree chose
            case 0:                                        // one of the three possible
                newCurrentRoot = rotateRight(currentRoot); // cases and balance the tree
                break;                                     // according to the theory
            case +1:
                rotateLeft((AVLNode) currentRoot.left);
                newCurrentRoot = rotateRight(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    private AVLNode balanceRightLoaded(AVLNode currentRoot) { // Balance a right-loaded
        AVLNode newCurrentRoot = null;                        // tree
        
        if (currentRoot.balance == 2) {                     // Is there anything to do?
            switch(((AVLNode) currentRoot.right).balance) { // Is the AVL-requirement
            case +1:                                        // injured?
                newCurrentRoot = rotateLeft(currentRoot);   // Yes, so let the balance of
                break;                                      // the right sub-tree chose
            case 0:                                         // one of the three possible
                newCurrentRoot = rotateLeft(currentRoot);   // cases and balance the tree
                break;                                      // according to the theory
            case -1:
                rotateRight((AVLNode) currentRoot.right);
                newCurrentRoot = rotateLeft(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    private AVLNode balance(AVLNode currentRoot) { // If necessary try to balance the
        AVLNode newCurrentRoot = null;             // tree
        
        switch(currentRoot.balance) {              // The balance of the current root
        case -2:                                   // determines if the tree is left or
            newCurrentRoot = balanceLeftLoaded(currentRoot); // right loaded
            break;                                 // Accordingly balance a left loaded
        case +2:                                   // or a right loaded tree
            newCurrentRoot = balanceRightLoaded(currentRoot);
            break;
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    protected void grownBy(AVLNode node) {      // Message to all parents that a sub tree
        AVLNode parent = (AVLNode) node.parent; // indicated by the passed node has grown
                                                // due to the insertion of a new node. Stop
        if (parent != null) {                   // at the root (anchor of recursion)
            if (node.isLeftChild()) {           // Update the balance of the parent
                parent.balance--;               // according to the side on which the sub-
            } else if (node.isRightChild()) {   // tree has grown
                parent.balance++;
            }
            if (parent.balance == -2 || parent.balance == +2) { // If the AVL-requirement
                parent = balance(parent);       // is injured, balance the tree at the
            } else if (parent.balance != 0) {   // current node. If it's not injured, but
                grownBy(parent);                // the tree has grown, message this to the
            }                                   // parent of the parent
        }
    }

    public void insert(Object data) {                       // The overwritten insert-
        AVLNode newAVLNode = new AVLNode(null, data, null); // method reports the growth
        root = insert(root, newAVLNode);                    // of the tree up to the
        grownBy(newAVLNode);                                // parents
    }
    
    protected void shrunkBy(AVLNode node) {     // Message to all parents that a sub-tree
        AVLNode parent = (AVLNode) node.parent; // tree indicated by the passed node has
                                                // shrunken due to the removal of a node
        if (parent != null) {                   // Stop at the root (anchor of recursion)
            if (node.isLeftChild()) {           // Update the balance of the parent
                parent.balance++;               // according to the side on which the sub-
            } else if (node.isRightChild()) {   // tree has shrunken
                parent.balance--;
            }
            if (parent.balance == -2 || parent.balance == +2) { // If the AVL-requirement
                parent = balance(parent);       // is injured, balance the tree at the
            }                                   // current node.
            if (parent.balance == 0) {          // If the tree still has been shrunken,
                shrunkBy(parent);               // message this to the parent of the
            }                                   // parent
        }
    }
    
    protected Node remove(Node toRemove) { // The overwritten remove-method reports the
        if (toRemove != null) {               // contraction of a sub-tree
            toRemove = replaceRoot(toRemove); // up to the parents
            shrunkBy((AVLNode) toRemove);
            removeLeaf(toRemove);
        }
        return toRemove;
    }
    
    protected static boolean isAVLTree(AVLNode node) { // Check if the tree is a valid
        boolean answer = true;                         // AVL-Tree, e.g. the balances of
                                                       // the nodes reflects the real
        if (node != null) {                            // hight of its sub-trees
            if (height(node.right) - height(node.left) != node.balance) {
                answer= false;
            } else {
                answer = isAVLTree((AVLNode) node.right) 
                        && isAVLTree((AVLNode) node.left);
            }
        }
        return answer;
    }
    
    public boolean isAVLTree() {
        return isAVLTree((AVLNode) root);
    }
    
    protected static boolean isAVLBalanced(AVLNode node) { // Check if the tree is
        boolean answer = true;                             // correctly balanced to be a
                                                           // valid the AVL-requirement
        if (node != null) {
            if (node.balance < -1 || node.balance > 1) {
                answer = false;
            } else {
                answer = isAVLBalanced((AVLNode) node.right) 
                        && isAVLBalanced((AVLNode) node.left); 
            }
        }
        return answer;
    }
    
    public boolean isAVLBalanced() {
        return isAVLBalanced((AVLNode) root);
    }
}
