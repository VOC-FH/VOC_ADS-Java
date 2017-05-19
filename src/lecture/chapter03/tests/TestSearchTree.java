package lecture.chapter03.tests;

import java.util.Random;

import lecture.chapter03.IComparator;
import lecture.chapter03.IFIterator;
import lecture.chapter03.SearchTree;

public class TestSearchTree {
    public static void main(String[] args) {
        IComparator integerComparator = new IntegerComparator();

        SearchTree searchTree = new SearchTree(integerComparator);

        final int MAX = 10000;

        Random rand = new Random();
        for (int i = 0; i < MAX; i++) {
            System.out.println("+++++++++++++++++++++++++");
            int n = rand.nextInt(100000);
            System.out.println("Inserting number " + i + ": " + n);
            searchTree.insert(n);
            System.out.println("Height: " + searchTree.height());
        }

        System.out.println();
        
        Object toRemove = null;
        int counter = 0;
        do {
            IFIterator it = searchTree.iterator();
            toRemove = null;
            if (it.hasNext()) {
                toRemove = it.next();
                counter++;
                System.out.println("Removing Nr " + counter + ": " + toRemove);
                searchTree.remove(toRemove);
            }
        } while(toRemove != null);
        
        
    }
}
