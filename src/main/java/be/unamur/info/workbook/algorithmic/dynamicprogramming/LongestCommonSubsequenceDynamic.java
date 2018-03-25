package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import static com.google.common.base.Preconditions.checkPositionIndex;
import java.util.List;

/**
 * This class contains methods to compute the longest common subsequences of two
 * sequences <i>a=a0,...,an-1</i> and <i>b=b0,...,bm-1</i> using dynamic
 * programming method. <i>L(i,j)</i> with
 * <i>0 &le; i &le; n</i> and <i>0 &le; j &le; m</i> represents the length of
 * the longest common subsequence of <i>a=a1,...,ai</i> and <i>b=b1,...,bj</i>.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 * @param <T> The type of the sequences
 */
public class LongestCommonSubsequenceDynamic<T> extends LongestCommonSubsequence<T> {

    /**
     * This enumeration represents the C(i,j) values stored in table c
     */
    protected enum Case {
        case1, case2, case3
    };

    /**
     * The values of the longest sub sequences. Inv: l[i][j] == L(i,j)
     */
    protected int[][] l;

    /**
     * The recursive case applied when computing the longest common subsequence.
     * Inv: c[i][j] == C(i,j)
     */
    protected Case[][] c;

    /**
     * INV:
     * <ul>
     * <li> l.length == n + 1 </li>
     * <li> forall i in [0;l.length]: l[i].length == m + 1</li>
     * <li> c.length == n</li>
     * <li> forall i in [0;c.length]: c[i].length == m</li>
     * </ul>
     *
     */
    @Override
    protected boolean repOk() {
        boolean ok = super.repOk();
        // Check l length
        ok = ok && l.length == getSizeA() + 1;
        for (int i = 0; i < l.length && ok; i++) {
            ok = ok && l[i].length == getSizeB() + 1;
        }
        // Check c length
        ok = ok && c.length == getSizeA() + 1;
        for (int i = 0; i < l.length && ok; i++) {
            ok = ok && l[i].length == getSizeB() + 1;
        }
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
    public LongestCommonSubsequenceDynamic(T[] a, T[] b) {
        super(a, b);
    }

    /**
     * Compute L(i,j) values and record them in this.
     *
     * @modifies this
     * @ensure L(i,j) and C(i,j) values have been computed and recorded in the
     * object.
     */
    protected void initMemory() {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public int longestSize(int lgtA, int lgtB) {
        checkPositionIndex(lgtA, getSizeA());
        checkPositionIndex(lgtB, getSizeB());
        //TODO: Build the method corresponding to the given specification using L table
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public List<T> longest(int lgtA, int lgtB) {
        checkPositionIndex(lgtA, getSizeA());
        checkPositionIndex(lgtB, getSizeB());
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
