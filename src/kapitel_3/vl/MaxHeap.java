package kapitel_3.vl;

public class MaxHeap extends Heap {
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
