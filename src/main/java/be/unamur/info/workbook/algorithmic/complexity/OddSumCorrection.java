package be.unamur.info.workbook.algorithmic.complexity;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class OddSumCorrection {

    public static int oddSumIterative(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) { // T = k ∗ n
            res = res + 2 * i - 1; // T = k 
        }
        return res;
    }

    public static int oddSumRecursive(int n) {
        if (n == 0) {
            return 0; // T= k
        } else {
            return 2 * n - 1 + oddSumRecursive(n - 1); // T = T(n−1) + k }}
        }
    }
    
}
