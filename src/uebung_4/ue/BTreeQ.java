package uebung_4.ue;

import kapitel_3.vl.IFIterator;
import kapitel_3.vl.ReferenceKey;
import uebung_3.ue.Queue;

public class BTreeQ extends kapitel_3.vl.BTree {
    private BQueue queue = new BQueue();
    
    protected static class BQueue extends Queue {
        public void renqueue(Object data) {
            list.append(data);
            size++;
        }
        
        public Object rpeek() {
            Object data = null;

            IFIterator it = list.fIterator();
            if (it.hasNext()) {
                data = it.next();
            }
            
            return data;
        }
        
        public Object rdequeue() {
            Object data = rpeek();
            
            if (data != null) {
                list.forwardRemove(data);
                size--;
            }
            
            return data;
        }
    }
    
    protected Node breadthFirstAppend(Node newNode) {
        Node node = (Node) queue.peek();
        
        if (node == null) {
            root = newNode;
        } else {
            if (node.left == null) {
                node.left = newNode;
            } else {
                node.right = newNode;
                queue.dequeue();
            }
            newNode.parent = node;
        }
        queue.enqueue(newNode);
        
        return newNode;
    }
    
    public void insert(Object data) {
        breadthFirstAppend(new Node(null, data, null)); 
    }
    
    protected void breadthFirstRemove(Node node) {
        Node toRemove = (Node) queue.rdequeue();
        
        if (toRemove == root) {
            root = null;
        } else if (toRemove != null) {
            node.data = toRemove.data;
            if (toRemove.isRightChild()) {
                queue.renqueue(toRemove.parent);
            }
            this.removeLeaf(toRemove);
        }
    }
    
    public void remove(Object data) {
        ReferenceKey key = new ReferenceKey(data);
        Node node = depthFirstSearch(root, key);
        
        this.breadthFirstRemove(node);
    }
}
