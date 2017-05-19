package lecture.chapter03;

public class MinHeap extends Heap {
    public MinHeap(IComparator comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return +1;
    }
    
    public Object extractMin() {
        return extractRoot();
    }
}
