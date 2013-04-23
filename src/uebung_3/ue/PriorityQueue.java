package uebung_3.ue;

import kapitel_3.vl.IFIterator;
import kapitel_3.vl.IRIterator;

public class PriorityQueue {
	MyDList list = new MyDList();
	
	protected static class Priority {
		int prio;
		Object data;
		
		Priority(Object data, int prio) {
			this.data = data;
			this.prio = prio;
		}
	}

	public void enqueue(Object data, int prio) {
		IFIterator it = list.fIterator();
		boolean inserted = false;
		
		while (it.hasNext() && !inserted) {
			Priority current = (Priority) it.next();
			if (current.prio <= prio) {
				Priority priority = new Priority(data, prio);
				inserted = list.insertBefore(current, priority);
			}
		}
		
		if (!inserted) {
			list.append(new Priority(data, prio));
		}
	}
	
	public Object dequeue() {
		Object data = null;
		IRIterator it = list.rIterator();
		
		if (it.hasPrevious()) {
			data = it.previous();
			list.reverseRemove(data);
		}
		
		return (data != null) ? ((Priority) data).data : null;
	}
}
