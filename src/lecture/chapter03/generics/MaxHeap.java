package lecture.chapter03.generics;

public class MaxHeap<T> extends Heap<T> {
    public MaxHeap(IComparator<T> comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return -1;
    }

    public Object extractMax() {
        return extractRoot();
    }
}
