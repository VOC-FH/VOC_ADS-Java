package uebung_5.ue;

import kapitel_3.vl.IComparator;
import kapitel_3.vl.ReferenceKey;

public abstract class Heap extends uebung_4.ue.BTree {
    protected IComparator comparator = null;
    
    protected abstract int comparatorSign();
    
    protected int cs = comparatorSign();
    
    Node lastNode = null;
    
    public Heap(IComparator comparator) {
        this.comparator = comparator;
    }
    
    protected static void exchangeDatasets(Node node1, Node node2) {
        Object tmp = node1.data;
        node1.data = node2.data;
        node2.data = tmp;
    }
    
    private Node upHeap(Node node) {
        if (node.parent != null) {
            if (cs * comparator.compare(node.parent.data, node.data) == +1) {
                exchangeDatasets(node, node.parent);
                node = upHeap(node.parent);
            }
        }
        
        return node;
    }

    private Node downHeap(Node node) {
        Node child = node.left;
        
        if (node.left == null || node.right != null 
                && cs * comparator.compare(node.left.data, node.right.data) == +1) {
            child = node.right;
        }
        if (child != null && cs * comparator.compare(node.data, child.data) == +1) {
            exchangeDatasets(node, child);
            node = downHeap(child);
        }
        
        return node;
    }
    
    protected final void heapInsert(Node newNode) {
        breadthFirstAppend(newNode);
        
        upHeap(newNode);
    }
    
    public void insert(Object data) {
        Node newNode = new Node(null, data, null);
        
        heapInsert(newNode);
    }
    
    protected final void heapRemove(Node toRemove) {
        Object data = toRemove.data;
        
        breadthFirstRemove(toRemove);

        if (cs * comparator.compare(data, toRemove.data) == +1) {
            upHeap(toRemove);
        } else {
            downHeap(toRemove);
        }
    }
    
    public void remove(Object data) {
        ReferenceKey key = new ReferenceKey(data);
        Node toRemove = depthFirstSearch(root, key);
        
        heapRemove(toRemove);
    }
    
    public Object extractRoot() {
        Node toRemove = root;
        
        Object data = (toRemove != null) ? toRemove.data : null;

        heapRemove(toRemove);
        
        return data;
    }
}
