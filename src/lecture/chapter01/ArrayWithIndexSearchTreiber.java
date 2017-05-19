package lecture.chapter01;

public class ArrayWithIndexSearchTreiber {
    public static void main(String[] args) {
        ArrayWithIndexSearch array = new ArrayWithIndexSearch(10);

        for (int i = 0; i < 10; i++) {
            array.set(i, new Long(100 + i));
        }

        Long key = new Long(102);
        int index = array.indexSearch(key);

        System.out.println("Index of " + key + ": " + index);
    }
}
