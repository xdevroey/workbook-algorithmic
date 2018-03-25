package be.unamur.info.workbook.algorithmic.dynamicprogramming;

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
 * Unit test of PairTest class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class PairTest {

    private static final Logger LOG = LoggerFactory.getLogger(PairTest.class);

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
    public void testSettersGetters() {
        Object o1 = new Object();
        Object o2 = new Object();
        Pair<Object, Object> pair = new Pair(o1,o2);
        assertThat(pair.getFirst(), equalTo(o1));
        assertThat(pair.getSecond(), equalTo(o2));
        // Setters
        pair.setFirst(o2);
        pair.setSecond(o1);
        assertThat(pair.getFirst(), equalTo(o2));
        assertThat(pair.getSecond(), equalTo(o1));
    }
    
    @Test
    public void testEquals() {
        Object o1 = new Object();
        Object o2 = new Object();
        Pair<Object, Object> pair1 = new Pair(o1,o2);
        Pair<Object, Object> pair2 = new Pair(o1,o2);
        assertThat(pair1, equalTo(pair2));
        assertThat(pair1.hashCode(), equalTo(pair2.hashCode()));
    }
    
    @Test
    public void testHashCode() {
        Object o1 = new Object();
        Object o2 = new Object();
        Pair<Object, Object> pair1 = new Pair(o1,o2);
        Pair<Object, Object> pair2 = new Pair(o1,o2);
        assertThat(pair1.hashCode(), equalTo(pair2.hashCode()));
    }
    
    

}