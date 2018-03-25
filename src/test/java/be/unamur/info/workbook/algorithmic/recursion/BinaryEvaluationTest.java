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
 * Unit test of BinaryEvaluation class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class BinaryEvaluationTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(BinaryEvaluationTest.class);
    
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
    public void testToDecimalNormalCase() {
        boolean[] binary = new boolean[]{false, false, true, false, true};
        int expected = 20;
        assertThat(BinaryEvaluation.toDecimal(binary), equalTo(expected));
    }
    
    @Test
    public void testToDecimalZero() {
        boolean[] binary = new boolean[]{false, false, false, false, false};
        int expected = 0;
        assertThat(BinaryEvaluation.toDecimal(binary), equalTo(expected));
    }
    
    @Test
    public void testToDecimalEmptyArray() {
        boolean[] binary = new boolean[]{};
        int expected = 0;
        assertThat(BinaryEvaluation.toDecimal(binary), equalTo(expected));
    }
    
}
