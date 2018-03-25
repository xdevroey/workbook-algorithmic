package be.unamur.info.workbook.algorithmic.greedy;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.Arrays;

/**
 * This class represents a packaging problem where a set of N objects with a
 * weight W[i] (i between 0 incl and N excl) have to be packed in boxes with a
 * capacity C.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Packaging {

    /**
     * The weight of the N objects. Inv: weights.length = N, and for each i
     * between 0 and N: 0 &le; weights[i] &le; C, and weights sorted in
     * descending order.
     */
    protected final double[] weights;

    /**
     * The capacity C of the boxes. Inv: capacity > 0.
     */
    protected final double capacity;

    /**
     * The box assigned to each object. Inv: map == null or map.length = N, and
     * and for each i between 0 and N: 1 &le; map[i] &le; N.
     */
    protected int[] map;

    /**
     * INV:
     * <ul>
     * <li> capacity > 0 </li>
     * <li> for each i between [0;N[: 0 &le weights[i] &le; capacity</li>
     * <li> maps != null => (map.length == N and for each i between [0;N[: 1 &le
     * map[i] &le; N)</li>
     * </ul>
     */
    private boolean repOk() {
        boolean ok = capacity > 0;
        for (int i = 0; i < objectsCount() && ok; i++) {
            ok = ok && weights[i] >= 0 && weights[i] <= capacity;
        }
        if (ok && map != null) {
            ok = ok && map.length == objectsCount();
            for (int i = 0; i < map.length && ok; i++) {
                ok = ok && map[i] >= 1 && map[i] <= objectsCount();
            }
        }
        return ok;
    }

    /**
     * Creates a new Packaging problem instance.
     *
     * @requires capacity > 0, and for each i between 0 and weights.length: 0
     * &le; weights[i] &le; C, and weights sorted in descending order.
     * @modifies this
     * @effects this.N = weights.length, and this.weights = weights.copy(), and
     * this.capacity = capacity.
     */
    public Packaging(double[] weights, double capacity) {
        this.weights = Arrays.copyOf(weights, weights.length);
        this.capacity = capacity;
        checkArgument(repOk(), "One of the given arguments violate the class invariant representation!");
    }

    /**
     * Returns N.
     *
     * @effects return == N
     */
    public int objectsCount() {
        return weights.length;
    }

    /**
     *
     */
    protected void initMap() {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     *
     */
    public int packagesCount() {
        if (map == null) {
            initMap();
        }
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     *
     */
    public int getPackageIndex(int object) {
        if (map == null) {
            initMap();
        }
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
