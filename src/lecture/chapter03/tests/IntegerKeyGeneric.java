package lecture.chapter03.tests;

import lecture.chapter03.generics.IKey;

public class IntegerKeyGeneric implements IKey<Integer> {
    Integer data;
    
    public IntegerKeyGeneric(int data) {
        this.data = data;
    }
    
    public boolean matches(Integer data) { // Return true if the key matches a given object.
        return this.data.intValue() == data.intValue();
    }
    
    public void setKeyValue(int i) {
        data = i;
    }
}
