package be.unamur.info.workbook.algorithmic.recursion;

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
 * Unit test of Minimum class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class MinimumTest {

    private static final Logger LOG = LoggerFactory.getLogger(MinimumTest.class);

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
    public void testMinPositiveValues() {
        int[] tab = new int[]{32, 5, 7, 3, 7, 9, 12, 30};
        int expected = 3;
        assertThat(Minimum.min(tab), equalTo(expected));
    }

    @Test
    public void testMinNegativeValues() {
        int[] tab = new int[]{32, 5, 7, 3, 7, 9, -12, 30};
        int expected = -12;
        assertThat(Minimum.min(tab), equalTo(expected));
    }

    @Test
    public void testDuplicateNegativeValues() {
        int[] tab = new int[]{32, 5, -12, 7, 3, 7, 9, -12, 30, -12};
        int expected = -12;
        assertThat(Minimum.min(tab), equalTo(expected));
    }

    @Test
    public void testAllTheSameValues() {
        int[] tab = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int expected = 0;
        assertThat(Minimum.min(tab), equalTo(expected));
    }

}
