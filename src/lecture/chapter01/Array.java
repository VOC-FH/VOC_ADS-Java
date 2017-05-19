package lecture.chapter01;

public class Array {
    protected int size = 0;
    protected Object[] array = null;

    public Array(int size) {
        this.size = size; array = new Object[size];
    }

    public void set(int index, Object o) {
        array[index] = o;
    }

    public Object get(int index) { 
        return array[index];
    }

    public int size() { 
        return size; 
    }
}