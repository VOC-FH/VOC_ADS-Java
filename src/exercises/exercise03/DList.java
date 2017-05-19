package exercises.exercise03;

import lecture.chapter03.ReferenceKey;

public class DList extends lecture.chapter03.DList {
    protected void insertAfter(Node current, Object data) {		
        if (current != null) {
            Node newNode = new Node(current, data, current.next);
            if (current.next == null) {
                tail = newNode;
            }
            current.next = newNode;
            if (newNode.next != null) {
                newNode.next.prev = newNode;
            }
        }
    }
    
    public boolean insertAfter(Object prev, Object data) {
        ReferenceKey key = new ReferenceKey(prev);
        Node current = forwardSearch(head, key);
        insertAfter(current, data);
        
        return current != null;
    }
    
    protected void insertBefore(Node current, Object data) {
        if (current != null) {
            Node newNode = new Node(current.prev, data, current);
            if (current.prev == null) {
                head = newNode;
            }
            current.prev = newNode;
            if (newNode.prev != null) {
                newNode.prev.next = newNode;
            }
        }
    }
    
    public boolean insertBefore(Object next, Object data) {
        ReferenceKey key = new ReferenceKey(next);
        Node current = forwardSearch(head, key);
        insertBefore(current, data);
        
        return current != null;
    }
}
