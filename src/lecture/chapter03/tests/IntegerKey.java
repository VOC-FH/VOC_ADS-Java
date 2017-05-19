package lecture.chapter03.tests;

import lecture.chapter03.IKey;

public class IntegerKey implements IKey {
    Integer data;
    
    public IntegerKey(int data) {
        this.data = data;
    }
    
    public boolean matches(Object data) { // Return true if the key matches a given object.
        if (data instanceof Integer) {
            return this.data.intValue() == ((Integer) data).intValue();
        } else if (data instanceof IntegerKey) {
            return this.data.intValue() == ((IntegerKey) data).data.intValue();
        }
        return false;
    }
    
    public void setKeyValue(int i) {
        data = i;
    }
}
