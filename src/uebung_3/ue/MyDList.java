package uebung_3.ue;

import kapitel_3.vl.DList;

public class MyDList extends DList {
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
		Node current = forwardSearchNode(prev);
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
		Node current = forwardSearchNode(next);
		insertBefore(current, data);
		
		return current != null;
	}
}
