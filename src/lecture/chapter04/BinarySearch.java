package lecture.chapter04;

public class BinarySearch {
    public static int binarySearch(int[] array, int key) {
        int min = 0;
        int max = array.length - 1;
        while(min <= max) {
            int mid = (min + max) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                min = mid + 1;
            } else if (array[mid] > key) {
                max = mid - 1;
            }
        }
        return -1;
    }
}
