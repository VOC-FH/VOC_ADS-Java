package uebung_3.ue;

import kapitel_3.vl.IKey;
import kapitel_3.vl.IRIterator;

public class PriorityQueue {
    protected uebung_3.ue.DList list = new uebung_3.ue.DList();
    
    protected static class PriorityProxy {
        private int priority;
        private Object data;
        
        protected PriorityProxy(Object data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }
    
    protected static class PriorityProxyKey implements IKey {
        private int prio;
        
        protected PriorityProxyKey(int prio) {
            this.prio = prio;
        }
        
        public boolean matches(Object data) {
            return ((PriorityProxy) data).priority <= prio;
        }
    }

    public void insert(Object data, int priority) {
        PriorityProxy prevProxy = (PriorityProxy) list.forwardSearch(new PriorityProxyKey(priority));
        PriorityProxy newProxy = new PriorityProxy(data, priority);
        
        if (prevProxy != null) {
            list.insertBefore(prevProxy, newProxy);
        } else {
            list.append(newProxy);
        }
    }
    
    public Object extractMin() {
        Object data = null;
        IRIterator it = list.rIterator();
        
        if (it.hasPrevious()) {
            data = it.previous();
            list.reverseRemove(data);
        }
        
        return (data != null) ? ((PriorityProxy) data).data : null;
    }
}
