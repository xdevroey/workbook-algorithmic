package be.unamur.info.workbook.algorithmic.greedy;

import static com.google.common.base.Preconditions.*;

/**
 * This class represents classical Knapsack problem with continuous quantities
 * (objects are splittable). There are n different resources, each one has a
 * value val[i] and is available in a quantity qtt[i] (with i between 0 and n -
 * 1 incl.). The sack has a capacity max.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class Knapsack {

    /**
     * The available quantites of each resource. Inv: qtt.length == n, and for
     * each i between [0;n[, qtt[i] >= 0.
     */
    protected double[] qtt;
    /**
     * The value of each resource, distinct from each other and sorted in
     * descending order. Inv: val.length == n, and for each i between [0;n[,
     * val[i] >= 0.
     */
    protected double[] val;
    /**
     * The maximal capacity of the sack. Inv: max >= 0.
     */
    protected double max;

    /**
     * The quantity of each resource in the sack. Inv: booty.length == n, and
     * for each i between [0;n[, 0 &le; booty[i] &le; qtt[i], and sum(booty[i]
     * for i between [0;n[) &le; max
     */
    protected double[] booty;
    /**
     * The value of the resources in the sack. Inv: bootyValue = sum(booty[i] *
     * val[i] for i between [0;n[).
     */
    protected double bootyValue;

    /**
     * INV:
     * <ul>
     * <li> n>=0 </li>
     * <li> max>=0 </li>
     * <li> qtt.length == n </li>
     * <li> val.length == n </li>
     * <li> booty.length == n </li>
     * <li> for each i between [0;n[: qtt[i] >= 0 and val[i] >= 0 and 0 &le;
     * booty[i] &le; qtt[i]</li>
     * <li> bootyValue = sum(booty[i] * val[i] for i between [0;n[)</li>
     * <li> sum(booty[i] for i between [0;n[) &le; max</li>
     * <li> val sorted in descending order</li>
     * <li> for each i,j between [0;n[: i != j, val[i] != val[j]</li>
     * </ul>
     */
    private boolean repOk() {
        boolean ok = qtt.length == val.length; // Lengths must be the same
        for (int i = 0; i < qtt.length && ok; i++) { // values must be higher or equal to 0
            ok = ok && qtt[i] >= 0 && val[i] >= 0;
        }
        for (int i = 0; i < val.length - 1 && ok; i++) { // value must be sorted descending and different 
            ok = val[i] > val[i + 1];
        }
        if (ok && booty != null) {
            ok = ok && booty.length == qtt.length; // If there is a booty, lengths must be the same
            double cumul = 0;
            for (int i = 0; i < booty.length && ok; i++) {
                ok = ok && booty[i] <= qtt[i]; // booty must be less than available quantity 
                cumul = cumul + booty[i];
            }
            ok = ok && cumul <= max; // Cumul of booties must not be higher than max capacity
        }
        for (int i = 0; i < val.length - 1 && ok; i++) {
            ok = ok && val[i] > val[i + 1];
        }
        return ok;
    }

    /**
     * Builds a new instance of the Knapsack problem wiht the given quantities,
     * values, and max capacity.
     *
     * @requires qtt and val not null, max >= 0, qtt.length = val.length, all
     * elements of qtt and val higher or equal to 0.
     * @modifies this
     * @effects this.n == val.length, this.max == max, this.qtt and this.val
     * equals to the result of sorting val descending and preserving order in
     * qtt.
     */
    public Knapsack(double[] qtt, double[] val, double max) {
        this.qtt = qtt;
        this.val = val;
        this.max = max;
        booty = new double[qtt.length];
        for (int i = 0; i < booty.length; i++) {
            booty[i] = 0;
        }
        bootyValue = 0;
        qsort(this.val, this.qtt, 0, this.val.length - 1);
        checkArgument(repOk(), "One of the given arguments violate the class invariant representation!");
        initBooty();
    }

    /**
     *
     */
    protected void initBooty() {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     *
     */
    public double getBootyValue() {
        return bootyValue;
    }

    // Quick sort implementation to sort values in descending order.
    private void qsort(double[] val, double[] qtt, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(val, qtt, left, right);
        if (left < index - 1) {
            qsort(val, qtt, left, index - 1);
        }
        if (index < right) {
            qsort(val, qtt, index, right);
        }
    }

    private int partition(double[] val, double[] qtt, int left, int right) {
        int i = left, j = right;
        double pivot = val[(left + right) / 2];
        while (i <= j) {
            while (val[i] > pivot) {
                i++;
            }
            while (val[j] < pivot) {
                j--;
            }
            if (i <= j) {
                swap(val, i, j);
                swap(qtt, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void swap(double[] val, int i, int j) {
        double tmp = val[i];
        val[i] = val[j];
        val[j] = tmp;
    }

}
