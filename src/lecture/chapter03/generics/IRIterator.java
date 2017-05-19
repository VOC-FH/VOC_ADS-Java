package lecture.chapter03.generics;

public interface IRIterator<T> { // The Backward-iterator interface
    boolean hasPrevious(); // Does a prior object exist?
    T previous();     // Return the prior object.
}
