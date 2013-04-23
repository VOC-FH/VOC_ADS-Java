package kapitel_3.tests;

import kapitel_3.vl.IKey;

public class IntegerKey implements IKey {
	Integer data;
	
	public IntegerKey(int data) {
		this.data = data;
	}
	
	public boolean equals(Object data) { // Return true if the key matches a given object.
        System.out.println(data);
	    if (data instanceof Integer) {
            System.out.println("Integer");
	        return this.data.intValue() == ((Integer) data).intValue();
	    } else if (data instanceof IntegerKey) {
	        System.out.println("IntegerKey: " + ((IntegerKey) data).data.intValue() + ", this Data: " + this.data.intValue());
	        return this.data.intValue() == ((IntegerKey) data).data.intValue();
	    }
	    return false;
	}
}
