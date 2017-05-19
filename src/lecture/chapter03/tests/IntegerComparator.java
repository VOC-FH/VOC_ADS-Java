package lecture.chapter03.tests;

import lecture.chapter03.IComparator;
import lecture.chapter03.IKey;

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
        Object int1 = data;
        Object int2 = ((IntegerKey) key).data;
        
        return compare(int1, int2);
    }
}
