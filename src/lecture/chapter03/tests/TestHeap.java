package lecture.chapter03.tests;

import lecture.chapter03.MinHeap;
import lecture.chapter03.utils.PGFTreePrinter;

public class TestHeap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(new IntegerComparator());

        PGFTreePrinter ptp = new PGFTreePrinter(heap);
        
        for(int i = 30; i >= 0; i--) {
            heap.insert(i);
        }
        
        System.out.println("End: " + heap.height() + ", IsHeap: " + heap.isHeap());

        for(int i = 50; i > 0; i--) {
            heap.insert(i);
        }

        System.out.println("End: " + heap.height() + ", IsHeap: " + heap.isHeap());
        
        System.out.println(ptp);
        

        Object data = null;
        
        for (int i = 1; i < 70; i++) {
            data = heap.extractMin();
            System.out.println(data + ", Height: " + heap.height() + ", isHeap: " + heap.isHeap());
            System.out.println(ptp);
            
        }
        System.out.println(ptp);

        for(int i = 70; i < 90; i++) {
            heap.insert(i);
        }
        System.out.println(ptp);
        /*
        do {
            data = heap.extractMax();
            System.out.println(data + ", Height: " + heap.height() + ", isHeap: " + heap.isHeap());
            System.out.println(ptp);
        } while(data != null); */
    }
}
