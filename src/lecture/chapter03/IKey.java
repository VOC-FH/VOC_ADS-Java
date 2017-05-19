package lecture.chapter03;

public interface IKey {    // Interface Key. This is for searching etc.
    boolean matches(Object data); // Return true if the key matches a given object.
}