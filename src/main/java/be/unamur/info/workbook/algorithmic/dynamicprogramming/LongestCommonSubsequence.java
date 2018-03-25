package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import static com.google.common.base.Preconditions.*;
import java.util.List;

/**
 * This class contains methods to compute the longest common subsequences of two
 * sequences <i>a=a0,...,an-1</i> and <i>b=b0,...,bm-1</i>. <i>L(i,j)</i> with
 * <i>0 &le; i &le; n</i> and <i>0 &le; j &le; m</i> represents the length of
 * the longest common subsequence of <i>a=a1,...,ai</i> and <i>b=b1,...,bj</i>.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 * @param <T> The type of the sequences
 */
public class LongestCommonSubsequence<T> {

    /**
     * The first sequence of length <i>n</i>.
     */
    protected final T[] a;

    /**
     * The second sequence of length <i>m</i>.
     */
    protected final T[] b;

    /**
     * INV: a not null and b not null;
     */
    protected boolean repOk() {
        boolean ok = a != null && b != null;
        return ok;
    }

    /**
     * Creates a new LongestCommonSubsequence object with the two given
     * sequences.
     *
     * @requires a not null and b not null
     * @modifies this
     * @effects this.a == a and this.b == b
     * @throws NullPointerException if a or b is null
     */
    public LongestCommonSubsequence(T[] a, T[] b) {
        checkNotNull(a);
        checkNotNull(b);
        this.a = a;
        this.b = b;
    }

    /**
     * Returns the size of the first sequence.
     *
     * @effects return == this.a.length
     */
    public int getSizeA() {
        return a.length;
    }

    /**
     * Returns the size of the second sequence.
     *
     * @effects return == this.b.length
     */
    public int getSizeB() {
        return b.length;
    }

    /**
     * Returns the length of the longest subsequence between a[1..lgtA] and
     * b[1..lgtB].
     *
     * @requires 0 &le; lgtA &le; getSizeA() and 0 &le; lgtB &le; getSizeB()
     * @effects return == L(lgtA,lgtB)
     * @throws IndexOutOfBoundsException If lgtA or lgtA is out of bounds.
     */
    public int longestSize(int lgtA, int lgtB) {
        checkPositionIndex(lgtA, getSizeA());
        checkPositionIndex(lgtB, getSizeB());
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Returns the length of the longest subsequence between a and b.
     *
     * @effects return == L(n,m)
     */
    public int longestSize() {
        return longestSize(a.length, b.length);
    }

    /**
     * Returns the the longest subsequence between a[1..lgtA] and b[1..lgtB].
     *
     * @requires 0 &le; lgtA &le; getSizeA() and 0 &le; lgtB &le; getSizeB()
     * @effects return == longest subsequence between a[1..maxLgtA] and
     * b[1..lgtB]
     * @throws IndexOutOfBoundsException If lgtA or lgtA is out of bounds.
     */
    public List<T> longest(int lgtA, int lgtB) {
        checkPositionIndex(lgtA, getSizeA());
        checkPositionIndex(lgtB, getSizeB());
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Returns the longest subsequence between a and b.
     *
     * @effects return == longest subsequence between a[1..maxLgtA] and
     * b[1..lgtB]
     */
    public List<T> longest() {
        return longest(a.length, b.length);
    }

}
