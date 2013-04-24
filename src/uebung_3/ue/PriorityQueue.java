package uebung_3.ue;

import kapitel_3.vl.IKey;
import kapitel_3.vl.IRIterator;

public class PriorityQueue {
	MyDList list = new MyDList();
	
	protected static class PriorityProxy {
		int prio;
		Object data;
		
		public PriorityProxy(Object data, int prio) {
			this.data = data;
			this.prio = prio;
		}
	}
	
	protected static class PriorityProxyKey implements IKey {
	    int prio;
	    
	    public PriorityProxyKey(int prio) {
	        this.prio = prio;
	    }
	    
        public boolean matches(Object data) {
            return ((PriorityProxy) data).prio <= prio;
        }
	}

	public void enqueue(Object data, int prio) {
		PriorityProxy prevProxy = (PriorityProxy) list.forwardSearch(new PriorityProxyKey(prio));
		PriorityProxy newProxy = new PriorityProxy(data, prio);
		
		if (prevProxy != null) {
		    list.insertBefore(prevProxy, newProxy);
		} else {
			list.append(newProxy);
		}
	}
	
	public Object dequeue() {
		Object data = null;
		IRIterator it = list.rIterator();
		
		if (it.hasPrevious()) {
			data = it.previous();
			list.reverseRemove(data);
		}
		
		return (data != null) ? ((PriorityProxy) data).data : null;
	}
}
