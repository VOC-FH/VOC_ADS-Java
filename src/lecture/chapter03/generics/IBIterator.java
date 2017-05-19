package lecture.chapter03.generics;

public abstract class IBIterator<T>  implements IFIterator<T>, IRIterator<T> {
    // Try to cast a forward iterator to a bidirectional iterator.

    public static <T> IBIterator<T> bidirectionalIterator(IFIterator<T> iterator) {
        return (iterator instanceof IBIterator<?>) ? (IBIterator<T>) iterator : null;
    }
    
    // Try to cast a reverse iterator to a bidirectional iterator.
    public static <T> IBIterator<T> bidirectionalIterator(IRIterator<T> iterator) {
        return (iterator instanceof IBIterator<?>) ? (IBIterator<T>) iterator : null;
    }
}
