package exercises.exercise07;
public class Algorithms {
    public static int simpleAlgorithm(int x, int n) {
        int p = 1;
        for (int i = 1; i <= n; i++) {
            p = p * x;
        }
        return p;
    }
    
    public static int quickAlgorith(int x, int n) {
        int p = 1;	
        while (n > 0) {
            if (n % 2 != 0) {
                p = p * x;
            }
            n = n / 2;
            x = x * x;
        }
        return p;
    }
}
