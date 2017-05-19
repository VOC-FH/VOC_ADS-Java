package lecture.chapter03.generics;

public class MinHeap<T> extends Heap<T> {
    public MinHeap(IComparator<T> comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return +1;
    }
    
    public Object extractMin() {
        return extractRoot();
    }
}
