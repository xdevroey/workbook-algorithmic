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
 * Unit test of GreatestCommonDivisor class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class GreatestCommonDivisorTest {

    private static final Logger LOG = LoggerFactory.getLogger(GreatestCommonDivisorTest.class);

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
    public void testGreatestCommonDivisorA1B5() {
        int a = 1;
        int b = 5;
        int result = GreatestCommonDivisor.greatestCommonDivisor(a, b);
        assertThat(result, equalTo(1));
    }
    
    @Test
    public void testGreatestCommonDivisorA126B54() {
        int a = 126;
        int b = 54;
        int result = GreatestCommonDivisor.greatestCommonDivisor(a, b);
        assertThat(result, equalTo(18));
    }

    @Test
    public void testGreatestCommonDivisorA42B385() {
        int a = 42;
        int b = 385;
        int result = GreatestCommonDivisor.greatestCommonDivisor(a, b);
        assertThat(result, equalTo(7));
    }
    

}