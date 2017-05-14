package uebung_3.ue;

import kapitel_3.vl.IFIterator;
import kapitel_3.vl.IRIterator;

public class Queue {
    protected int size = 0;
    protected uebung_3.ue.DList list = new uebung_3.ue.DList();
    
    public void enqueue(Object data) {
        list.prepend(data);
        size++;
    }
    
    public Object peek() {
        Object data = null;

        IRIterator it = list.rIterator();
        if (it.hasPrevious()) {
            data = it.previous();
        }
        
        return data;
    }
    
    public Object dequeue() {
        Object data = peek();
        
        if (data != null) {
            list.reverseRemove(data);
            size--;
        }
        
        return data;
    }
    
    public void rewind() {
        IFIterator it = list.fIterator();
        if (it.hasNext()) {
            Object data = it.next();
            list.forwardRemove(data);
        }
    }
    
    public boolean empty() {
        return size == 0;
    }
}
