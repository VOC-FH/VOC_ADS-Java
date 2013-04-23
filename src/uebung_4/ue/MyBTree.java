package uebung_4.ue;

import uebung_3.ue.Queue;
import kapitel_3.vl.BTree;

public class MyBTree extends BTree {
	private Queue queue = new Queue();
	
	protected Node breadthFirstAppend(Object data) {
		Node newNode = new Node(null, data, null);
		
		if (queue.empty()) {
			root = newNode;
		} else {
			Node parent = (Node) queue.peek();
			newNode.parent = parent;
			if (parent.left == null) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
				queue.dequeue();
			}
		}
		queue.enqueue(newNode);
		
		return newNode;
	}
	
	public void insert(Object data) {
		breadthFirstAppend(data);	
	}
}
