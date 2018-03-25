package be.unamur.info.workbook.algorithmic.greedy;

import com.google.common.collect.Sets;
import java.util.Set;
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
 * Unit tests for Planning class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PlanningTest {

    private static final Logger LOG = LoggerFactory.getLogger(PlanningTest.class);

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
    public void testGetPerformancesEmpty() {
        int[] start = new int[]{};
        int[] end = new int[]{};
        Set<Integer> expected = Sets.newHashSet();
        Planning planning = new Planning(start, end);
        assertThat(planning.getPerformances(), equalTo(expected));
    }

    @Test
    public void testGetPerformancesNotEmpty() {
        int[] start = new int[]{1, 2, 4, 11, 10};
        int[] end = new int[]{3, 4, 5, 14, 15};
        Set<Integer> expected = Sets.newHashSet(0, 2, 3);
        Planning planning = new Planning(start, end);
        assertThat(planning.getPerformances(), equalTo(expected));
    }

    @Test
    public void testGetPerformancesFitsExactly() {
        int[] start = new int[]{0, 2, 4, 6, 8, 10};
        int[] end = new int[]{1, 3, 5, 7, 9, 11};
        Set<Integer> expected = Sets.newHashSet(0, 1, 2, 3, 4, 5);
        Planning planning = new Planning(start, end);
        assertThat(planning.getPerformances(), equalTo(expected));
    }

}
