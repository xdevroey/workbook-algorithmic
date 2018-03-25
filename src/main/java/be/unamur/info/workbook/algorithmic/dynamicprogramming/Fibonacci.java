package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * This class computes Fibonacci sequence using dynamic programming.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Fibonacci {

    /**
     * Fibonacci singleton.
     */
    public static final Fibonacci SEQUENCE = new Fibonacci();

    /**
     * Private implementation for the singleton pattern.
     */
    private Fibonacci() {
    }

    /**
     * A map with the previously computed values of Fibonacci sequence.
     */
    private final Map<Integer, Integer> fiboMap = new HashMap();

    /**
     * Returns the n th term in Fibonacci sequence using memoisation.
     *
     * @requires n >= 0.
     * @modifies this
     * @effects return == n th term of Fibonacci sequence.
     */
    public static int fibonacciDescendentRecursion(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return fibonacciDescendentRecursion(n - 1) + fibonacciDescendentRecursion(n - 2);
        }
    }

    /**
     * Returns the n th term in Fibonacci sequence and update internal memory to
     * avoid multiple computations for the same n value.
     *
     * @requires n >= 0.
     * @modifies this.fiboMap
     * @effects return == this.fiboMap.get(n) == n th term of Fibonacci sequence
     * and this.fiboMap.contains(n).
     */
    public int fibonacciMemoised(int n) {
        // If the value has been previouslyh computed, returns the result
        if (fiboMap.containsKey(n)) {
            return fiboMap.get(n);
        } else {
            // Else, compute the value and put it in the map
            int fibo = fibonacciDescendentRecursion(n);
            fiboMap.put(n, fibo);
            return fibo;
        }
    }

    /**
     * Returns the n th term in Fibonacci sequence.
     *
     * @requires n >= 0.
     * @effects return == n th term of Fibonacci sequence.
     */
    public static int fibonacciAscendantIteration(int n) {
        if (n < 2) {
            return n;
        } else {
            int fi1 = 1;
            int fi2 = 0;
            for (int i = 2; i <= n; i++) {
                int tmp = fi1;
                fi1 = fi1 + fi2;
                fi2 = tmp;
            }
            return fi1;
        }
    }

    /**
     * Returns the n th term in Fibonacci sequence.
     *
     * @requires n >= 0.
     * @effects return == n th term of Fibonacci sequence.
     */
    private static int fibonacciAscendantRecursion(int i, int n, int fi1, int fi2) {
        if (i == n) {
            return fi2;
        } else {
            return Fibonacci.fibonacciAscendantRecursion(i + 1, n, fi1 + fi2, fi1);
        }
    }

    /**
     * Returns the n th term in Fibonacci sequence.
     *
     * @requires n >= 0.
     * @effects return == n th term of Fibonacci sequence.
     */
    public static int fibonacciAscendantRecursion(int n) {
        return Fibonacci.fibonacciAscendantRecursion(0, n, 1, 0);
    }

}
