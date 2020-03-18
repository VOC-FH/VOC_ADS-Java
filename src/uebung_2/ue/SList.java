package uebung_2.ue;

import kapitel_3.vl.IKey;
import kapitel_3.vl.ReferenceKey;

public class SList extends kapitel_3.vl.SList {
    protected Node searchForLastNode() {
        Node current = head;
        
        while(current != null && current.next != null) {
            current = current.next;
        }
        
        return current;
    }
    
    public void append(Object data) {
        Node lastNode = searchForLastNode();
        Node newNode = new Node(data, null);
        
        if (lastNode == null) {
            head = newNode;
        } else {
            lastNode.next = newNode;
        }
    }
    
    public boolean insert(IKey key, Object data) {
        Node previousNode = search(head, key);
        
        if (previousNode != null) {
            previousNode.next = new Node(data, previousNode.next);
        }
        
        return previousNode != null;
    }
    
    public boolean insert(Object prev, Object data) {
        ReferenceKey referenceKey = new ReferenceKey(prev);
        Node previousNode = search(head, referenceKey);
        
        if (previousNode != null) {
            previousNode.next = new Node(data, previousNode.next);
        }
        
        return previousNode != null;
    }
    
    public SList searchAll(IKey key) {
        SList allList = new SList();
        
        for (Node current = head; current != null; current = current.next) {
            if (key.matches(current.data)) {
                allList.prepend(current.data);
            }
        }
        
        return allList;
    }
}
