package be.unamur.info.workbook.algorithmic.complexity;

import java.util.stream.IntStream;
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
 * Unit test of OddSum class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class OddSumTest {

    private static final Logger LOG = LoggerFactory.getLogger(OddSumTest.class);

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
    public void testOddSumIterative9() {
        int n = 9;
        int result = OddSum.oddSumIterative(n);
        assertThat(result, equalTo(oracle(n)));
    }
    
    @Test
    public void testOddSumIterative1() {
        int n = 1;
        int result = OddSum.oddSumIterative(n);
        assertThat(result, equalTo(oracle(n)));
    }
    
    @Test
    public void testOddSumIterative658() {
        int n = 42;
        int result = OddSum.oddSumIterative(n);
        assertThat(result, equalTo(oracle(n)));
    }
        
    @Test
    public void testOddSumRecursive9() {
        int n = 9;
        int result = OddSum.oddSumRecursive(n);
        assertThat(result, equalTo(oracle(n)));
    }
    
    @Test
    public void testOddSumRecursive1() {
        int n = 1;
        int result = OddSum.oddSumRecursive(n);
        assertThat(result, equalTo(oracle(n)));
    }
    
    @Test
    public void testOddSumRecursive658() {
        int n = 42;
        int result = OddSum.oddSumRecursive(n);
        assertThat(result, equalTo(oracle(n)));
    }
    
    private int oracle(int n){
        return IntStream.rangeClosed(1, n).map((x)->(x*2-1)).sum();
    }

}