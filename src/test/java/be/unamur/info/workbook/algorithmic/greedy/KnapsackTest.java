package be.unamur.info.workbook.algorithmic.greedy;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * unit tests for Knapsack class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class KnapsackTest {

    private static final Logger LOG = LoggerFactory.getLogger(KnapsackTest.class);

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;

    };

    @Test
    public void GetBootyValueEmpty() {
        double[] qtt = new double[]{};
        double[] val = new double[]{};
        double max = 10.0;
        double expectedValue = 0.0;
        Knapsack problem = new Knapsack(qtt, val, max);
        assertThat(problem.getBootyValue(), equalTo(expectedValue));
    }

    @Test
    public void GetBootyValueNotEmpty() {
        double[] qtt = new double[]{2.0, 4.0, 50.0, 4.0};
        double[] val = new double[]{50.0, 100.0, 10.0, 70.0};
        double max = 11.0;
        double expectedValue = 2.0 * 50 + 4 * 100 + 4 * 70 + 10;
        Knapsack problem = new Knapsack(qtt, val, max);
        assertThat(problem.getBootyValue(), equalTo(expectedValue));
    }

    @Test
    public void GetBootyValueStarvation() {
        double[] qtt = new double[]{2.0, 4.0, 50.0, 4.0};
        double[] val = new double[]{50.0, 100.0, 10.0, 70.0};
        double max = 100.0;
        double expectedValue = 2.0 * 50 + 4 * 100 + 50 * 10 + 4 * 70;
        Knapsack problem = new Knapsack(qtt, val, max);
        assertThat(problem.getBootyValue(), equalTo(expectedValue));
    }

}
