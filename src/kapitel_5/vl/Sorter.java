package kapitel_5.vl;

public class Sorter {
    public static void bubbleSort(int a[]) {
        for (int m = 0; m < a.length - 1; m++) {     // Loop for all pairs of neighbours
            for (int i = 0; i < a.length - 1; i++) { // Compare-loop
                if (a[i] > a[i + 1]) {               // Sort criteria injured?
                    int tmp = a[i];                  // Exchange the neighbours
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                }
            }
        }
    }
    
    public static void bubbleSortOptimized(int a[]) {
        boolean sorted = false;
        
        do {
            sorted = true;                           // Postulate - sorted!
            for (int i = 0; i < a.length - 1; i++) { // Compare-loop
                if (a[i] > a[i + 1]) {               // Sort criteria injured?
                    int tmp = a[i];                  // Exchange the neighbours
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    sorted = false;                  // Not sorted!
                }
            }
        } while (!sorted);                           // Loop until sorted
    }
    
    public static void selectionSort(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {     // Loop over all but the last elements
            int min = i;                             // Postulate - minimum!
            for (int j = i + 1; j < a.length; j++) { // Loop over the remaining elements
                if (a[j] < a[min]) {                 // New minimum?
                    min = j;                         // Store the new minimum!
                }
            }
            int tmp = a[i];                          // Exchange the current element
            a[i] = a[min];                           // with the minimal one!
            a[min] = tmp;
        }
    }
    
    public static void insertionSort(int a[]) { // v = # Verschiebeoperationen
        for(int i = 0; i < a.length - 1; i++) {
            int h = a[i + 1];                   // n - 1 Zuweisungen
            int j = i;
            while (j >= 0 && a[j] > h) {        // n - 1 + v Vergleiche
                a[j + 1] = a[j];                // v Zuweisungen
                j = j - 1;
            }
            a[j + 1] = h;                       // n - 1 Zuweisungen
        }
    }
    
    public static void shellSort(int a[]) {
        int m = a.length / 2;                        // Calculate the starting-gap 
        while (m > 0) {                              // Do as long as the gap is not zero
            for (int i = 0; i < a.length - m; i++) { // Iterate over all partial lists
                int h = a[i + m];                    // Remember the value to insert
                int j = i;                           // into the already sorted list
                while (j >= 0 && a[j] > h) {         // As long as an element of the sorted
                    a[j + m] = a[j];                 // list is bigger than h move them
                    j = j - m;                       // back one position
                }
                a[j + m] = h;                        // Insert the new value at the correct
            }                                        // position
            m = m / 2;
        }
    }
    
    private static int[] merge(int[] a, int[] b) { // Merge two sorted arrays in a way
        int[] erg = new int[a.length + b.length];  // that the resulting array is also
                                                   // sorted.
        int aCounter = 0;
        int bCounter = 0;
        int ergCounter = 0;

        while (ergCounter < a.length + b.length) {
            if (aCounter >= a.length)
                erg[ergCounter++] = b[bCounter++];
            else if (bCounter >= b.length)
                erg[ergCounter++] = a[aCounter++];
            else if (a[aCounter] < b[bCounter])
                erg[ergCounter++] = a[aCounter++];
            else
                erg[ergCounter++] = b[bCounter++];
        }
        
        return erg;
    }
    
    private static int[] mergeSort(int a[], int start, int size) { // Sort an array using
        int[] sorted = null;                                       // the merge sort method
        
        if (size == 1) {                                           // A array with length
            sorted = new int[1];                                   // one is sorted by
            sorted[0] = a[start];                                  // definition
        } else {
            int leftSize = size / 2;                               // Calculate the length
            int rightSize = size - leftSize;                       // of the partial array
            int[] left = mergeSort(a, start, leftSize);            // Sort them with the
            int[] right = mergeSort(a, start + leftSize, rightSize); // merge sort itself
            sorted = merge(left, right); // Merge the two sorted partial array
        }
        
        return sorted; // Deliver the sorted (partial) list
    }
    
    public static int[] mergeSort(int[] a) { // Convenient interface to merge sort
        return mergeSort(a, 0, a.length);
    }
    
    private static  void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    private static void quickSort(int[] a, int left, int right) { // Quicksort an array
        if (left >= right) { return; } // Break the recursion if the array is empty 
        
        if (a[left] > a[right]) {      // Sort leftmost and rightmost element
            exchange(a, left, right);
        }
        
        if (left + 1 == right) { return; } // Break if the array consists
                                           // of two element only
        int k = (left + right) / 2; // Determine the center of the array
        
        if (a[left] > a[k]) {       // Sort the center and the leftmost element
            exchange(a, left, k); 
        }
        if (a[k] > a[right]) {      // Sort the center and the rightmost element
            exchange(a, k, right); 
        }
        
        if (left + 2 == right) { return; } // Break if the array consists
                                           // of three elements only
        int x = a[k];      // Let the (sorted) center of the array be the pivot-element
        int i = left + 1;  // Start index for reordering from the left and from the right
        int j = right - 1; // omit the already reordered leftmost and rightmost elements
        
        while (i <= j) { // Iterate, as long as there are elements to reorder
            while (i < right && a[i] <= x) { i = i + 1; } // Omit elements already
            while (j > left && a[j] >= x) { j = j - 1; }  // correctly placed
            if (i < j) {           // Are there still elements to reorder?
                exchange(a, i, j); // Yes - exchange them
                i = i + 1;
                j = j + 1;
            }
        }
        quickSort(a, left, j);  // Quicksort the left partial array
        quickSort(a, i, right); // Quicksort the right partial array
    }
    
    public static void quickSort(int[] a) { // Convenient interface to quick sort
        quickSort(a, 0, a.length - 1);
    }
}
