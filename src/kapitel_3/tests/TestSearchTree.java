package kapitel_3.tests;

import java.util.Random;

import kapitel_3.work.IComparator;
import kapitel_3.work.SearchTree;

public class TestSearchTree {
	public static void main(String[] args) {
		IComparator integerComparator = new IntegerComparator();

		SearchTree searchTree = new SearchTree(integerComparator);

		final int MAX = 20000;

		Random rand = new Random();
		for (int i = 0; i < MAX; i++) {
			System.out.println("+++++++++++++++++++++++++");
			int n = rand.nextInt(100000);
			System.out.println("Inserting number " + i + ": " + n);
			searchTree.insert(n);
			System.out.println("Height: " + searchTree.height());
		}

		System.out.println();
	}
}
