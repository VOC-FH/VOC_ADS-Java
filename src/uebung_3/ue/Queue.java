package uebung_3.ue;

import kapitel_3.vl.DList;
import kapitel_3.vl.IRIterator;

public class Queue {
	int size = 0;
	DList list = new DList();
	
	public void enqueue(Object data) {
		list.prepend(data);
		size++;
	}
	
	public Object dequeue() {
		IRIterator it = list.rIterator();
		Object data = null;
		
		if (it.hasPrevious()) {
			size--;
			data = it.previous();
			list.reverseRemove(data);
		}
		
		return data;
	}
	
	public Object peek() {
		IRIterator it = list.rIterator();
		Object data = null;
		
		if (it.hasPrevious()) {
			data = it.previous();
		}
		
		return data;
	}
	
	public boolean empty() {
		return size == 0;
	}
}
