package be.unamur.info.workbook.algorithmic.greedy;

import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 * This class represents the planning problem for a set of performances
 * P0,...,Pn happening during the same day. A performance Pi starts at start[i]
 * and ends at end[i]. This class will select the maximal set of performances
 * that may be performed the same day.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Planning {

    /**
     * The starting moment of each performance. Inv: start.length == end.length.
     */
    protected final int start[];

    /**
     * The ending moment of each performance. Inv: end.length == start.length,
     * and end is sorted in ascending order.
     */
    protected final int end[];

    /**
     * The performances planned for the day.
     */
    protected Set<Integer> performances;

    /**
     * INV:
     * <ul>
     * <li> start.length == n </li>
     * <li> end.length == n </li>
     * <li> for each i between [0;n-1[: end[i] &le; end[i+1]</li>
     * <li> performances != null => performances.size &le; n, and performances's elements are distinct</li>
     * </ul>
     */
    private boolean repOk() {
        boolean ok = start.length == end.length;
        for (int i = 0; i < end.length - 1 && ok; i++) {
            ok = ok && end[i] <= end[i + 1];
        }
        if(performances != null){
            ok = ok && performances.size() <= start.length;
        }
        return ok;
    }

    /**
     * Creates a nex planning problem instance.
     *
     * @requires start.length == end.length, and end sorted in ascending
     * order.
     * @modifies this
     * @effects this.n == start.length, and this.start = start, and this.end =
     * end.
     */
    public Planning(int[] start, int[] end) {
        this.start = start;
        this.end = end;
        checkArgument(repOk(), "One of the given arguments violate the class invariant representation!");
    }

    /**
     *
     */
    protected void iniPerformances() {
        performances = Sets.newHashSet();
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     *
     */
    public Set<Integer> getPerformances() {
        if (performances == null) {
            iniPerformances();
        }
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
}
