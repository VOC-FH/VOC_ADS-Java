package kapitel_3.tests;

import kapitel_3.vl.IComparator;
import kapitel_3.vl.IKey;

public class IntegerComparator implements IComparator {
	public int compare(Object data1, Object data2) {
		int int1 = (Integer) data1;
		int int2 = (Integer) data2;
		
		if (int1 < int2) {
			return -1;
		} else if (int1 == int2) {
			return 0;
		} else { 
			return 1;
		}
	}

	public int compare(Object data, IKey key) {
		int int1 = (Integer) data;
		int int2 = (Integer) ((IntegerKey) key).data;
		
		if (int1 < int2) {
			return -1;
		} else if (int1 == int2) {
			return 0;
		} else { 
			return 1;
		}
	}
}
