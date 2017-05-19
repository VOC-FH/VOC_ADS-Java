package lecture.chapter03.generics;

public class Stack<T> {
    private SList<T> list = new SList<T>();
    private int size = 0;
    
    public void push(T data) {
        list.prepend(data);
        size++;
    }
    
    public T pop() {
        T data = null;
        
        IFIterator<T> it = list.iterator();
        
        if (it.hasNext()) {
            size--;
            data = it.next();
            list.remove(data);
        }
        return data;
    }
    
    public T top() {
        T data = null;
        
        IFIterator<T> it = list.iterator();
        
        if (it.hasNext()) {
            data = it.next();
        }
        return data;
    }
    
    public boolean empty() {
        return size == 0;
    }
}
