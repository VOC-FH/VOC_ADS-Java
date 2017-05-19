package lecture.chapter03.generics;

public interface IKey<T> {
    boolean matches(T data); // Return true if the key matches a given object.
}
