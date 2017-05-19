package lecture.chapter04.tests;

import lecture.chapter03.IComparator;
import lecture.chapter03.SearchTree;
import lecture.chapter03.tests.IntegerComparator;
import lecture.chapter03.utils.PGFTreePrinter;

public class TestSearchTree {
    public static void main(String[] args) {
        IComparator iComparator = new IntegerComparator();

        SearchTree searchTree = new SearchTree(iComparator);

        int[] array = {5, 3, 7, 4, 2, 6, 8};

        for(int i = 0; i < array.length; i++) {
            searchTree.insert(array[i]);
        }
        PGFTreePrinter ptp = new PGFTreePrinter(searchTree);
        
        System.out.println(ptp);
    }
}
