package lecture.chapter03.generics;

public class Array<T> {
    protected int size = 0; // The number of components
    protected T[] array = null;

    @SuppressWarnings("unchecked")
    public Array(int size) {
        this.size = size;
        array = (T[]) new Object[size];
    }

    public void add(int index, T data) { // Store the dataset at the 
        array[index] = data;                  // position index in the Array.
    }

    public T get(int index) { // Retrieve the dataset at the
        return array[index];       // position index.
    }

    public int size() { 
        return size; // Return the number of components.
    }
}
