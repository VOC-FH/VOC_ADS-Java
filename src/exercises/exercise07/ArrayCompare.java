package exercises.exercise07;
public class ArrayCompare {
    public static boolean arrayCompare(byte[] a1, byte[] a2) {
        int i = 0;
        while(i < a1.length && a1[i] == a2[i]) {
            i = i + 1;
        }
        return i == a1.length;
    }
}