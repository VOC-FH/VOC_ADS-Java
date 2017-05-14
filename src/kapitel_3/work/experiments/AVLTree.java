package kapitel_3.work.experiments;

import kapitel_3.work.IComparator;
import kapitel_3.work.IKey;
import kapitel_3.work.SearchTree;

public class AVLTree extends SearchTree {
    private static final class AVLLayer {
        private Object data = null;
        private int balance = 0;
        
        public AVLLayer(Object data) {
            this.data = data;
        }
    }
    
    
    private static final class AVLKey implements IKey {
        private IKey key = null;
        
        public AVLKey(IKey key) {
            this.key = key;
        }
        
        @Override
        public boolean matches(Object aVLLayer) {
            return key.matches(((AVLLayer) aVLLayer).data);
        }
    }
    
    
    private static final class AVLComparator implements IComparator {
        private IComparator comparator = null;
        
        public AVLComparator(IComparator comparator) {
            this.comparator = comparator;
        }
        
        @Override
        public int compare(Object aVLLayer1, Object aVLLayer2) {
            return comparator.compare(((AVLLayer) aVLLayer1).data,
                    ((AVLLayer) aVLLayer2).data);
        }

        @Override
        public int compare(Object aVLLayer, IKey aVLKey) {
            return comparator.compare(((AVLLayer) aVLLayer).data, ((AVLKey) aVLKey).key);
        }
        
    }

    
    public AVLTree(IComparator comparator) {
        super(new AVLComparator(comparator));
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
        Node newCurrentRoot = oldCurrentRoot.right; // Determine the new current root
        
        oldCurrentRoot.right = newCurrentRoot.left;      // Let the left child of the new
        if (newCurrentRoot.left != null) {               // current root be the new right
            newCurrentRoot.left.parent = oldCurrentRoot; // child of the old current root
        }                                                // and proper update the parent
        newCurrentRoot.left = oldCurrentRoot; // The old current root becomes the left
                                              // child of the new current root
        ((AVLLayer) oldCurrentRoot.data).balance = ((AVLLayer) oldCurrentRoot.data).balance - 1 // Update all balances
                - Math.max(0, ((AVLLayer) newCurrentRoot.data).balance);      // according to the theory
        ((AVLLayer) newCurrentRoot.data).balance = ((AVLLayer) newCurrentRoot.data).balance - 1 
                + Math.min(0, ((AVLLayer) oldCurrentRoot.data).balance);

        updateParents(oldCurrentRoot, newCurrentRoot);   // Proper connect the balanced
                                                         // sub-tree to the whole tree
        return newCurrentRoot;                           // Deliver the new current root
    }
    
    protected Node rotateRight(Node oldCurrentRoot) { // The rotation to the right
        Node newCurrentRoot = oldCurrentRoot.left; // Determine the new current root
        
        oldCurrentRoot.left = newCurrentRoot.right;       // Let the right child of the new
        if (newCurrentRoot.right != null) {               // current root be the new left
            newCurrentRoot.right.parent = oldCurrentRoot; // child of the old current root
        }                                                 // and proper update the parent
        newCurrentRoot.right = oldCurrentRoot; // The old current root becomes the right
                                               // child of the new current root
        ((AVLLayer) oldCurrentRoot.data).balance = ((AVLLayer) oldCurrentRoot.data).balance + 1 // Update all balances
                - Math.min(0, ((AVLLayer) newCurrentRoot.data).balance);      // according to the theory
        ((AVLLayer) newCurrentRoot.data).balance = ((AVLLayer) newCurrentRoot.data).balance + 1 
                + Math.max(0, ((AVLLayer) oldCurrentRoot.data).balance);
        
        updateParents(oldCurrentRoot, newCurrentRoot);    // Proper connect the balanced
                                                          // sub-tree to the whole tree
        return newCurrentRoot;                            // Deliver the new current root
    }
    
    private Node balanceLeftLoaded(Node currentRoot) { // Balance a left-loaded
        Node newCurrentRoot = null;                    // tree
        
        if (((AVLLayer) currentRoot.data).balance == -2) {                   // Is there anything to do?
            switch(((AVLLayer) currentRoot.left.data).balance) {    // Is the AVL-requirement
            case -1:                                       // injured?
                newCurrentRoot = rotateRight(currentRoot); // Yes, so let the balance of
                break;                                     // the left sub-tree chose
            case 0:                                        // one of the three possible
                newCurrentRoot = rotateRight(currentRoot); // cases and balance the tree
                break;                                     // according to the theory
            case +1:
                rotateLeft(currentRoot.left);
                newCurrentRoot = rotateRight(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    private Node balanceRightLoaded(Node currentRoot) { // Balance a right-loaded
        Node newCurrentRoot = null;                     // tree
        
        if (((AVLLayer) currentRoot.data).balance == 2) {                   // Is there anything to do?
            switch(((AVLLayer) currentRoot.right.data).balance) {  // Is the AVL-requirement
            case +1:                                      // injured?
                newCurrentRoot = rotateLeft(currentRoot); // Yes, so let the balance of
                break;                                    // the right sub-tree chose
            case 0:                                       // one of the three possible
                newCurrentRoot = rotateLeft(currentRoot); // cases and balance the tree
                break;                                    // according to the theory
            case -1:
                rotateRight(currentRoot.right);
                newCurrentRoot = rotateLeft(currentRoot);
                break;
            }
        }
        return newCurrentRoot; // Deliver the new current root
    }
    
    private Node balance(Node currentRoot) { // If necessary try to balance the
        Node newCurrentRoot = null;          // tree
        
        switch(((AVLLayer) currentRoot.data).balance) { // The balance of the current root
        case -2:                      // determines if the tree is left or
            newCurrentRoot = balanceLeftLoaded(currentRoot); // right loaded
            break;                    // Accordingly balance a left loaded
        case +2:                      // or a right loaded tree
            newCurrentRoot = balanceRightLoaded(currentRoot);
            break;
        }
        return newCurrentRoot; // Deliver the new current root
    }

    protected void grownTo(Node node) { // Message to all parents that a sub
        Node parent = node.parent;     // tree indicated by the passed node has
                                              // grown due to the insertion of a new node
        if (parent != null) {                 // Stop at the root (anchor of recursion)
            if (node.isLeftChild()) {         // Update the balance of the parent
                ((AVLLayer) parent.data).balance--;             // according to the side on which the
            } else if (node.isRightChild()) { // sub-tree has grown
                ((AVLLayer) parent.data).balance++;
            }
            switch (((AVLLayer) parent.data).balance) { // Let the balance of the parent decide if
            case -2: case +2:         // the AVL-requirement is injured or not
                balance(parent);      // If injured, balance the tree at the
                break;                // parent node
            case -1: case +1:         // If it's not injured, but the tree is not
                grownTo(parent);      // perfectly balanced, report the growth of
                break;                // the tree up to the next parent
            }
        }
    }

    public void insert(Object data) {                 // The overwritten insert-
        Node node = new Node(null, new AVLLayer(data), null); // method reports the growth
        root = insert(root, node);              // of the tree up to the
        grownTo(node);                          // parents
    }
    
    protected void shrunkBy(Node node) {       // Message to all parents that a sub
        Node parent = node.parent; // tree indicated by the passed node has
                                                // shrunken due to the removal of a node
        if (parent != null) {                   // Stop at the root (anchor of recursion)
            if (node.isLeftChild()) {           // Update the balance of the parent
                ((AVLLayer) parent.data).balance++;               // according to the side on which the
            } else if (node.isRightChild()) {   // sub-tree has shrunken
                ((AVLLayer) parent.data).balance--;
            }
            switch (((AVLLayer) parent.data).balance) {           // Let the balance of the parent decide if
            case -2: case +2:                   // the AVL-requirement is injured or not
                Node newParent = balance(parent); // If injured, balance the tree at the
                if (((AVLLayer) newParent.data).balance == 0) {     // parent node and if the balanced
                    shrunkBy(newParent);          // tree is also shrunken report this
                }                                 // up to the next parent
                break;
            case 0:               // The tree is shrunken due the removal of a node but
                shrunkBy(parent); // the AVL-requirement is not injured. Thus, report the
                break;            // contraction up to the next parent
            }
        }
    }
    
    protected void remove(Node toRemove) { // The overwritten remove-method reports the
        if (toRemove != null) {            // contraction of a sub-tree up to the parents
            toRemove = replaceRoot(toRemove);
            shrunkBy(toRemove);
            removeLeaf(toRemove);
        }
    }
    
    public Object search(IKey key) {
        return binarySearch(new AVLKey(key));
    }
    
    protected static boolean isBalanceValid(Node node) { // Check if the tree is a valid
        boolean answer = true;                      // AVL-Tree, e.g. the balances of
                                                    // the nodes reflects the real
        if (node != null) {                         // hight of its sub-trees
            if (height(node.right) - height(node.left) != ((AVLLayer) node.data).balance) {
                answer= false;
            } else {
                answer = isBalanceValid(node.right) 
                        && isBalanceValid(node.left);
            }
        }
        return answer;
    }
    
    public boolean areBalancesValid() {
        return isBalanceValid(root);
    }
    
    protected static <T> boolean isAVLTree(Node node) { // Check if the tree is
        boolean answer = true;                          // correctly balanced to be a
                                                        // valid the AVL-requirement
        if (node != null) {
            if (((AVLLayer) node.data).balance < -1 || ((AVLLayer) node.data).balance > 1) {
                answer = false;
            } else {
                answer = isAVLTree(node.right) 
                        && isAVLTree(node.left); 
            }
        }
        return answer;
    }
    
    public boolean isAVLTree() {
        return isAVLTree(root);
    }
}
