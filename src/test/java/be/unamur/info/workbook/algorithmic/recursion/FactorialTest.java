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
 * Unit test of Factorial class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class FactorialTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(FactorialTest.class);
    
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
    public void testFact7() {
        int n = 7;
        int expected = 7 * 6 * 5 * 4 * 3 * 2;
        assertThat(Factorial.fact(n), equalTo(expected));
    }
    
    @Test
    public void testFact0() {
        int n = 0;
        int expected = 1;
        assertThat(Factorial.fact(n), equalTo(expected));
    }

    @Test
    public void testTerminalFact7() {
        int n = 7;
        int expected = 7 * 6 * 5 * 4 * 3 * 2;
        assertThat(Factorial.terminalFact(n), equalTo(expected));
    }
    
    @Test
    public void testTerminalFact0() {
        int n = 0;
        int expected = 1;
        assertThat(Factorial.terminalFact(n), equalTo(expected));
    }
    
}
