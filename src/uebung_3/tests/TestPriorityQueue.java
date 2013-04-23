package uebung_3.tests;

import uebung_3.ue.PriorityQueue;
import uebung_3.ue.Stack;

public class TestPriorityQueue {
	public static void main(String[] args) {
		PriorityQueue queue = new PriorityQueue();
		
		queue.enqueue(1, 3);
		queue.enqueue(2, 4);
		queue.enqueue(3, 2);
		queue.enqueue(4, 3);
		queue.enqueue(5, 4);
		
		System.out.println((Integer) queue.dequeue());
		System.out.println((Integer) queue.dequeue());
		System.out.println((Integer) queue.dequeue());
		System.out.println((Integer) queue.dequeue());
		System.out.println((Integer) queue.dequeue());
		System.out.println((Integer) queue.dequeue());
		
		Stack stack = new Stack();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		System.out.println("===================");
		System.out.println((Integer) stack.pop());
		System.out.println((Integer) stack.pop());
		System.out.println((Integer) stack.pop());
		System.out.println((Integer) stack.pop());
		System.out.println((Integer) stack.pop());
		System.out.println((Integer) stack.pop());
	}
}
