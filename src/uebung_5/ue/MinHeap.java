package uebung_5.ue;

import kapitel_3.vl.IComparator;

public class MinHeap extends uebung_5.ue.Heap {
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
