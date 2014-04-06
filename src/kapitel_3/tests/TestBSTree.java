package kapitel_3.tests;

import kapitel_3.work.SearchTree;
import kapitel_3.work.PGFTree;
import kapitel_3.vl.IComparator;
//import kapitel_3.vl.IKey;

public class TestBSTree {
	public static void main(String[] args) {
		IComparator pgfComparator = PGFTree.comparator(new IntegerComparator());
		
		SearchTree sTree = new SearchTree(pgfComparator);
		PGFTree pgfTree = new PGFTree(sTree);

		int[] a = {3, 4, 1, 5, 6, 2, 7};
		for (int i = 0; i < a.length; i++) {
//			int n = rand.nextInt(100000);
//			System.out.println("Inserting number " + i + ": " + i);
			sTree.insert(pgfTree.pgfProxy(a[i]));
		}
		
//		System.out.println("Height: " + avlTree.height());
//		System.out.println("IsAVLTree: " + avlTree.isAVLTree());
//		System.out.println("ISBalanced: " + avlTree.isAVLBalanced());

		System.out.println(pgfTree.treeToPGF());
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
