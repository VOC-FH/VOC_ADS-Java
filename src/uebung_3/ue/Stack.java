package uebung_3.ue;

import kapitel_3.vl.IFIterator;

public class Stack {
    protected int size = 0;
    protected kapitel_3.vl.SList list = new kapitel_3.vl.SList();
    
    public void push(Object data) {
        list.prepend(data);
        size++;
    }
    
    public Object peek() {
        Object data = null;
        
        IFIterator it = list.iterator();
        if (it.hasNext()) {
            data = it.next();
        }
        
        return data;
    }
    
    public Object pop() {
        Object data = peek();
        
        if (data != null) {
            list.remove(data);
            size--;
        }
        
        return data;
    }
    
    public boolean empty() {
        return size == 0;
    }
}
