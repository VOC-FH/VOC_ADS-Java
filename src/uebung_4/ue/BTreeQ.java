package uebung_4.ue;

import kapitel_3.vl.ReferenceKey;
import uebung_3.ue.Queue;

public class BTreeQ extends kapitel_3.vl.BTree {
    private Queue queue = new Queue();
    
    protected Node breadthFirstAppend(Node newNode) {
        Node node = (Node) queue.peek();
        
        if (node == null) {
            root = node;
        } else {
            if (node.left == null) {
                node.left = newNode;
            } else {
                node.right = newNode;
                queue.dequeue();
            }
        }
        queue.enqueue(newNode);
        
        return newNode;
    }
    
    public void insert(Object data) {
        breadthFirstAppend(new Node(null, data, null)); 
    }
    
    protected void remove(Node node) {
        if (node != null) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            node.data = current.data;
            this.removeLeaf(current);
        }
    }
    public void remove(Object data) {
        ReferenceKey key = new ReferenceKey(data);
        Node toRemove = depthFirstSearch(root, key);
        
        remove(toRemove);
    }
}
