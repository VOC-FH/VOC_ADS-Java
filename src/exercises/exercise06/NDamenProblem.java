package exercises.exercise06;

public class NDamenProblem {
    static int s = 0;
    
    static void printChessboard(boolean[][] array, int spalte, int zeile) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (j == spalte && i == zeile) {
                    System.out.print("? ");
                } else {
                    System.out.print((array[j][i] ? 0 : ".") + " ");
                }
            }
            System.out.println();
        }
    }
    
    static boolean checkPosition(boolean[][] array, int spalte, int zeile) {
        boolean ok = true;
        int dimension = array.length;

        // Zeile
        for (int i = 0; i < spalte && ok; i++) {
            if (array[i][zeile]) {
                ok = false;
            }
        }
        for (int i = spalte + 1; i < dimension && ok; i++) {
            if (array[i][zeile]) {
                ok = false;
            }
        }
        
        // Spalte
        for (int i = 0; i < zeile && ok; i++) {
            if (array[spalte][i]) {
                ok = false;
            }
        }
        for (int i = zeile + 1; i < dimension && ok; i++) {
            if (array[spalte][i]) {
                ok = false;
            }
        }
        
        // Hauptdiagonale
        for (int i = 1; zeile - i >= 0 && spalte - i >= 0 && ok; i++) {
            if (array[spalte - i][zeile - i]) {
                ok = false;
            }
        }
        for (int i = 1; zeile + i < dimension && spalte + i < dimension && ok; i++) {
            if (array[spalte + i][zeile + i]) {
                ok = false;
            }
        }
        
        // Nebendiagonale
        for (int i = 1; zeile - i >= 0 && spalte + i < dimension && ok; i++) {
            if (array[spalte + i][zeile - i]) {
                ok = false;
            }
        }
        for (int i = 1; zeile + i < dimension && spalte - i >= 0 && ok; i++) {
            if (array[spalte - i][zeile + i]) {
                ok = false;
            }
        }
        return ok;
    }
    
    static boolean setzeDame(boolean[][] array, int zeile) {
        boolean solved = false;
        
        if (zeile < array.length) {
            for (int spalte = 0; spalte < array.length && !solved; spalte++) {
//                System.out.println("Versuche Dame auf Zeile: " + (zeile + 1) + ", Spalte: " + (spalte + 1) + " ...");
//                printChessboard(array, spalte, zeile);
                if (checkPosition(array, spalte, zeile)) {
//                    System.out.println("... OK");
                    array[spalte][zeile] = true;
                    solved = setzeDame(array, zeile + 1);
                    if (!solved) {
                        array[spalte][zeile] = false;
                    }
                } else {
//                    System.out.println("... not OK");
                }
            }
            if (!solved) {
//                System.out.println("Zeile " + (zeile + 1) + ": Platzierung Dame nicht möglich: Keine weitere Teillösungsvariante verfügbar!");
            } else {
//                System.out.println("Zeile " + (zeile + 1) + ": Platzierung Dame möglich: Gültige Teillösung gefunden!"); 
            }
        } else {
//            solved = true;
            System.out.println("Solution: " + ++s);
            printChessboard(array, -1, -1);
        }
        return solved;
    }
    
    public static void main(String[] args) {
        int n = 5;
        
        boolean[][] array = new boolean[n][n];
        
        System.out.println("Result: " + setzeDame(array, 0));
        printChessboard(array, -1, -1);
    }
}
