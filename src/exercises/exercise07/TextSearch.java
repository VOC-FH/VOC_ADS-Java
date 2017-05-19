package exercises.exercise07;
public class TextSearch {
    public static boolean searchRecursive(char[] text, char[] pattern, int tPos, int count) {
        if (pattern.length == count) {
            return true;
        } else if (text.length - pattern.length < tPos) {
            return false;
        } else {
            if (text[tPos + count] == pattern[count]) {
                return searchRecursive(text, pattern, tPos, count + 1);
            } else {
                return searchRecursive(text, pattern, tPos + 1, 0);
            }
        }
    }
    
    public static boolean searchIterative(char[] text, char[] pattern) {
        int tIndex = 0;
        while (tIndex <= text.length - pattern.length) {
            int pIndex = 0;
            while (pIndex < pattern.length && text[tIndex + pIndex] == pattern[pIndex]) {
                pIndex++;
            }
            if (pIndex == pattern.length) {
                return true;
            }
            tIndex++;
        }
        return false;
    }
}
