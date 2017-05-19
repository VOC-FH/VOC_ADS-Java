package lecture.chapter03.generics;

public interface IComparator<T> {
    // -1: data1 < data2, 
    //  0: data1 = data2,
    // +1: data1 > data2
    int compare(T data1, T data2);

    // -1: data < key, 
    //  0: data = key, 
    // +1: data > key
    int compare(T data, IKey<T> key);
}
