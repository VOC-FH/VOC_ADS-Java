package kapitel_4.tests;

import kapitel_3.tests.IntegerComparator;
import kapitel_3.vl.IComparator;
import kapitel_3.vl.PrintTree;
import kapitel_3.vl.SearchTree;

public class TestSearchTree {
	public static void main(String[] args) {
		IComparator iComparator = new IntegerComparator();

		SearchTree searchTree = new SearchTree(iComparator);

		int[] array = {5, 3, 7, 4, 2, 6, 8};

		for(int i = 0; i < array.length; i++) {
		    searchTree.insert(array[i]);
		}
		PrintTree pt = new PrintTree(searchTree);
		
		System.out.println(pt.treeToPGF());
	}
}
