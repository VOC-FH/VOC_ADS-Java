package exercises.exercise07.tests;

import exercises.exercise07.TextSearch;

public class TestTextSearch {
    public static void main(String[] args) {
        char[] text = "Hallo, das ist ein Text".toCharArray();
        char[] pattern = "ext".toCharArray();
        
        System.out.println(TextSearch.searchRecursive(text, pattern, 0, 0));
        System.out.println(TextSearch.searchIterative(text,pattern));
    }
}
