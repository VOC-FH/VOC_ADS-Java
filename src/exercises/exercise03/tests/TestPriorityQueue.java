package exercises.exercise03.tests;

import exercises.exercise03.PriorityQueue;
import exercises.exercise03.Stack;

public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        
        queue.insert(1, 3);
        queue.insert(2, 4);
        queue.insert(3, 2);
        queue.insert(4, 3);
        queue.insert(5, 4);
        
        System.out.println((Integer) queue.extractMin());
        System.out.println((Integer) queue.extractMin());
        System.out.println((Integer) queue.extractMin());
        System.out.println((Integer) queue.extractMin());
        System.out.println((Integer) queue.extractMin());
        System.out.println((Integer) queue.extractMin());
        
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
