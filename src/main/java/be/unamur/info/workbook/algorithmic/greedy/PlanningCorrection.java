package be.unamur.info.workbook.algorithmic.greedy;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PlanningCorrection extends Planning {

    public PlanningCorrection(int[] start, int[] end) {
        super(start, end);
    }

    @Override
    protected void iniPerformances() {
        performances = Sets.newHashSet();
        if (start.length == 0) {
            return;
        }
        int last = 0;
        performances.add(last);
        for (int i = 1; i < start.length; i++) {
            if(start[i] > end[last]){
                performances.add(i);
                last = i;
            }
        }
    }

    @Override
    public Set<Integer> getPerformances() {
        if (performances == null) {
            iniPerformances();
        }
        return this.performances;
    }

}
