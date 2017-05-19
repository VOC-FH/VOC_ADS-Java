package lecture.chapter03.generics;

public class Queue<T> {
    int size = 0;
    DList<T> list = new DList<T>();
    
    public void enqueue(T data) {
        list.prepend(data);
        size++;
    }
    
    public T dequeue() {
        IRIterator<T> it = list.rIterator();
        T data = null;
        
        if (it.hasPrevious()) {
            size--;
            data = it.previous();
            list.reverseRemove(data);
        }
        
        return data;
    }
    
    public T peek() {
        IRIterator<T> it = list.rIterator();
        T data = null;
        
        if (it.hasPrevious()) {
            data = it.previous();
        }
        
        return data;
    }
    
    public boolean empty() {
        return size == 0;
    }
}
