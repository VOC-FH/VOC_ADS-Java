package uebung_5.ue;

import kapitel_3.vl.IComparator;

public class MaxHeap extends uebung_5.ue.Heap {
    public MaxHeap(IComparator comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return -1;
    }

    public Object extractMax() {
        return extractRoot();
    }
}
