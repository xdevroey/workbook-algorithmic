package be.unamur.info.workbook.algorithmic.abstractdatatype;

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
 * Unit tests of ImmutableStack class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class ImmutableStackTest {

    private static final Logger LOG = LoggerFactory.getLogger(ImmutableStackTest.class);

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
    public void testStackMethods() {
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        // Using axiomatic specification to write test cases.
        assertThat(stack.empty(), is(true));
        assertThat(stack.size(), is(0));
        stack = stack.push(5);
        assertThat(stack.size(), is(1));
        assertThat(stack.top(), is(5));
        stack = stack.push(-8);
        assertThat(stack.size(), is(2));
        assertThat(stack.top(), is(-8));
        stack = stack.pop();
        assertThat(stack.size(), is(1));
        assertThat(stack.top(), is(5));        
    }
    
    @Test
    public void testImmutability() {
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        ImmutableStack<Integer> previous = stack;
        stack = stack.push(5);
        assertThat(stack, not(sameInstance(previous)));
        previous = stack;
        stack = stack.push(-8);
        assertThat(stack, not(sameInstance(previous)));
        previous = stack;
        stack = stack.pop();
        assertThat(stack, not(sameInstance(previous)));
    }

}
