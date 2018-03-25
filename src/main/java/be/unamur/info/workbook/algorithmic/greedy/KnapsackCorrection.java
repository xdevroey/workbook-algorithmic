package be.unamur.info.workbook.algorithmic.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Correction for the Knapsack problem.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class KnapsackCorrection extends Knapsack {
    
    private static final Logger LOG = LoggerFactory.getLogger(KnapsackCorrection.class);

    public KnapsackCorrection(double[] qtt, double[] val, double max) {
        super(qtt, val, max);
    }

    @Override
    protected void initBooty() {
        double capacity = max;
        int i = 0;
        bootyValue = 0;
        while (i < qtt.length && capacity > 0) {
            booty[i] = Math.min(capacity, qtt[i]);
            bootyValue = bootyValue + booty[i] * val[i];
            capacity = capacity - booty[i];
            i++;
        }
        while (i < qtt.length) {
            booty[i] = 0;
            i++;
        }
    }

}
