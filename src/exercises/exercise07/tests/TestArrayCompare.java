package exercises.exercise07.tests;

import exercises.exercise07.ArrayCompare;

public class TestArrayCompare {
    public static void main(String[] args) {
        byte[] a1 = new byte[10];
        byte[] a2 = new byte[10];
    
        a1[9] = 34;
        System.out.println(ArrayCompare.arrayCompare(a1, a2));
    }
}
