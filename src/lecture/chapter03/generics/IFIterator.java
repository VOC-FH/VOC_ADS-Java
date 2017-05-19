package lecture.chapter03.generics;

public interface IFIterator<T> {
    boolean hasNext(); // Does a further object exist?
    T next();     // Return the next object.
}
