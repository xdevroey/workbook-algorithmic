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
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class LucasTest {

    private static final Logger LOG = LoggerFactory.getLogger(LucasTest.class);
    
    private static final double ERROR = 0.000001;

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
    public void testPowerX1N0() {
        double x = 1.0;
        int n = 0;
        double result = Lucas.power(x, n);
        assertThat(result, closeTo(Math.pow(x, n), ERROR));
    }
    
    @Test
    public void testPowerX42N1() {
        double x = 4.2;
        int n = 1;
        double result = Lucas.power(x, n);
        assertThat(result, closeTo(Math.pow(x, n), ERROR));
    }
    
    @Test
    public void testPowerX0N4() {
        double x = 0.0;
        int n = 4;
        double result = Lucas.power(x, n);
        assertThat(result, closeTo(Math.pow(x, n), ERROR));
    }    

    @Test
    public void testPowerX42N6() {
        double x = 4.2;
        int n = 6;
        double result = Lucas.power(x, n);
        assertThat(result, closeTo(Math.pow(x, n), ERROR));
    }    

    @Test
    public void testPowerX34N5() {
        double x = 3.4;
        int n = 5;
        double result = Lucas.power(x, n);
        assertThat(result, closeTo(Math.pow(x, n), ERROR));
    }

}
