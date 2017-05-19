package lecture.chapter03;

public class Array {
    protected int size = 0;	// The number of components
    protected Object[] array = null;

    public Array(int size) {
        this.size = size;
        array = new Object[size];
    }

    public void add(int index, Object data) { // Store the dataset at the 
        array[index] = data;                  // position index in the Array.
    }

    public Object get(int index) { // Retrieve the dataset at the
        return array[index];       // position index.
    }

    public int size() { 
        return size; // Return the number of components.
    }
}