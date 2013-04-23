package kapitel_3.tests;

import kapitel_3.vl.MaxHeap;
import kapitel_3.vl.PrintTree;

public class TestHeap {
	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap(new IntegerComparator());
		PrintTree printTree = new PrintTree(heap);
		
		for(int i = 30; i >= 0; i--) {
			heap.insert(i);
		}
		
 		System.out.println("End: " + heap.height() + ", IsHeap: " + heap.isHeap());

		for(int i = 50; i > 0; i--) {
			heap.insert(i);
		}

		System.out.println("End: " + heap.height() + ", IsHeap: " + heap.isHeap());
		
		System.out.println(printTree.treeToPGF());
		

		Object data = null;
		
		for (int i = 1; i < 70; i++) {
			data = heap.extractMax();
			System.out.println(data + ", Height: " + heap.height() + ", isHeap: " + heap.isHeap());
			System.out.println(printTree.treeToPGF());
			
		}
		System.out.println(printTree.treeToPGF());

		for(int i = 70; i < 90; i++) {
			heap.insert(i);
		}
		System.out.println(printTree.treeToPGF());
		
		do {
			data = heap.extractMax();
			System.out.println(data + ", Height: " + heap.height() + ", isHeap: " + heap.isHeap());
			System.out.println(printTree.treeToPGF());
		} while(data != null);
	}
}
