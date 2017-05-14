package uebung_4.ue;
        
import kapitel_3.vl.ReferenceKey;

public class BTree extends kapitel_3.vl.BTree {
    private Node lastInserted = null;
    
    protected final Node searchForNextParent() {
        Node traveler = lastInserted;
        
        if (traveler != null) {
            if (traveler.isLeftChild()) {
                traveler = traveler.parent;
            } else {
                while (traveler.isRightChild()) {
                    traveler = traveler.parent;
                }
                if (traveler.isLeftChild()) {
                    traveler = traveler.parent.right;
                }
                while (traveler.left != null) {
                    traveler = traveler.left;
                }
            }
        }
        
        return traveler;
    }
    
    protected final void breadthFirstAppend(Node newNode) {
        Node parent = searchForNextParent();
        
        if (parent == null) {
            root = newNode;
        } else if (parent.left == null) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;
        
        lastInserted = newNode;
    }
    
    public void insert(Object data) {
        Node newNode = new Node(null, data, null);
        
        breadthFirstAppend(newNode);
    }
    
    protected final Node searchForLastButOne() {
        Node traveler = lastInserted;
        
        if (traveler.isRightChild()) {
            traveler = traveler.parent.left;
        } else if (traveler.isLeftChild()){
            while(traveler.isLeftChild()) {
                traveler = traveler.parent;
            }
            if (traveler.isRightChild()) {
                traveler = traveler.parent.left;
            }
            while (traveler.right != null) {
                traveler = traveler.right;
            }
        } else {
            traveler = null;
        }
        
        return traveler;
    }
    
    protected final void breadthFirstRemove(Node toRemove) {
        if (toRemove != null && lastInserted != null) {
            toRemove.data = lastInserted.data;
            Node lastButOne = searchForLastButOne();
            removeLeaf(lastInserted);
            lastInserted = lastButOne;
        } 
    }
    
    public void remove(Object data) {
        ReferenceKey key = new ReferenceKey(data);
        Node toRemove = depthFirstSearch(root, key);
        
        breadthFirstRemove(toRemove);
    }
}
