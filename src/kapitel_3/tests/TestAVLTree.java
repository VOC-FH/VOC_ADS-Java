package kapitel_3.tests;

import kapitel_3.vl.AVLTree;
import kapitel_3.vl.IComparator;
import kapitel_3.vl.PrintTree;

public class TestAVLTree {
	public static void main(String[] args) {
		IComparator integerComparator = new IntegerComparator();
		
		AVLTree avlTree = new AVLTree(integerComparator);
		PrintTree printTree = new PrintTree(avlTree);
		printTree.addFormat(4, "[rectangle, inserted node]", "[inserted node]");
        printTree.addFormat(9, "[removed node]", "[removed node]");
        printTree.addFormat(8, "[removed node]", "[removed node]");
        printTree.addFormat(10, "[removed node]", "[removed node]");
        
//        printTree.addFormat(7, "[removed node]", "[inserted node]");
	
		final int MAX = 14;

//		Random rand = new Random();
		for (int i = 0; i < MAX; i++) {
//			int n = rand.nextInt(100000);
			System.out.println("Inserting number " + i + ": " + i);
			avlTree.insert(i);
		}
		System.out.println("Height: " + avlTree.height());
		System.out.println("IsAVLTree: " + avlTree.isAVLTree());
		System.out.println("ISBalanced: " + avlTree.isAVLBalanced());

		System.out.println(printTree.treeToPGF());
	/*	
		Object data = null;

		int i = 0;
		do {
			i++;
			IFIterator it = avlTree.iterator();
			if (it.hasNext()) {
				data = it.next();
				System.out.println("Get: " + data + ", i = " + i);
				System.out.println("IsAVLTree: " + avlTree.isAVLTree());
				System.out.println("ISBalanced: " + avlTree.isAVLBalanced());
				avlTree.remove(data);
			} else {
				data = null;
			}
		} while(data != null);

*/
//		System.out.println(PrintTree.treeToQTree(avlTree));
/*		
		IntegerKey key = new IntegerKey(0);
		
		for (int i = 0; i < MAX; i++) {
			key.data = i;
			avlTree.remove(key);
			System.out.println("Remove: " + i);

//			avlTree.breadthFirst(new PrintWorker());
		
			System.out.println("Height: " + avlTree.height());
			System.out.println("IsAVLTree: " + avlTree.isAVLTree());
			System.out.println("ISBalanced: " + avlTree.isAVLBalanced());
		}
*/

/*
//		int[] array = {6, 2, 8, 1, 4, 7, 3, 5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
		for (int i = 0; i < array.length; i++) {
			avlTree.insert(array[i]);
		}
		
		avlTree.breadthFirst(new PrintWorker());

		System.out.println("Height: " + avlTree.height());
		System.out.println("IsAVLTree: " + avlTree.isAVLTree());
		System.out.println("ISBalanced: " + avlTree.isAVLBalanced());
	
		IntegerKey key = new IntegerKey(7);
//		avlTree.remove(key);
	
		for (int i = 0; i < array.length; i++) {
			key.data = array[i];
			avlTree.remove(key);

			avlTree.breadthFirst(new PrintWorker());
		
			System.out.println("Height: " + avlTree.height());
			System.out.println("IsAVLTree: " + avlTree.isAVLTree());
			System.out.println("ISBalanced: " + avlTree.isAVLBalanced());
		}
		
		System.out.println("End");
*/
	}
}
