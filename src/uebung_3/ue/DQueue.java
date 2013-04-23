package uebung_3.ue;

import kapitel_3.vl.IFIterator;

public class DQueue extends Queue {
	public void enqueueReverse(Object data) {
		list.append(data);
		size++;
	}
	
	public Object dequeueReverse() {
		IFIterator it = list.fIterator();
		Object data = null;
		
		if (it.hasNext()) {
			size--;
			data = it.next();
			list.forwardRemove(data);
		}
		
		return data;
	}
	
	public Object peekReverse() {
		IFIterator it = list.fIterator();
		Object data = null;
		
		if (it.hasNext()) {
			data = it.next();
		}
		
		return data;
	}
}
