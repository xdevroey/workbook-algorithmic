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
 * Unit tests for Stacks class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class StacksTest {

    private static final Logger LOG = LoggerFactory.getLogger(StacksTest.class);

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
    public void testReverse() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        Stack<Integer> result;
        int[] values = new int[]{5, 9, -78, 42, 34};
        for (int i : values) {
            stack = stack.push(i);
        }
        result = s.reverse(stack);
        for (int i : values) {
            assertThat(result.top(), equalTo(i));
            result = result.pop();
        }
    }

    @Test
    public void testReverseEmpty() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        Stack<Integer> result;
        result = s.reverse(stack);
        assertThat(result.empty(), is(true));
    }

    @Test
    public void testZipSameSize() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> first = new ImmutableStack<>();
        ImmutableStack<Integer> second = new ImmutableStack<>();
        Stack<Integer> result;
        int[] firstValues = new int[]{5, 9, -78, 42, 34};
        int[] secondValues = new int[]{-9, 58, 42, 27, 12};
        for (int i : firstValues) {
            first = first.push(i);
        }
        for (int i : secondValues) {
            second = second.push(i);
        }
        result = s.zip(first, second);
        while (!(first.empty() && second.empty())) {
            assertThat(result.top(), equalTo(first.top()));
            result = result.pop();
            first = first.pop();
            assertThat(result.top(), equalTo(second.top()));
            result = result.pop();
            second = second.pop();
        }
    }

    @Test
    public void testZipFirstLonger() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> first = new ImmutableStack<>();
        ImmutableStack<Integer> second = new ImmutableStack<>();
        Stack<Integer> result;
        int[] firstValues = new int[]{5, 9, -78, 42, 34};
        int[] secondValues = new int[]{-9, 58};
        for (int i : firstValues) {
            first = first.push(i);
        }
        for (int i : secondValues) {
            second = second.push(i);
        }
        result = s.zip(first, second);
        while (!(first.empty() && second.empty())) {
            if (!first.empty()) {
                assertThat(result.top(), equalTo(first.top()));
                result = result.pop();
                first = first.pop();
            }
            if (!second.empty()) {
                assertThat(result.top(), equalTo(second.top()));
                result = result.pop();
                second = second.pop();
            }
        }
    }

    @Test
    public void testZipSecondLonger() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> first = new ImmutableStack<>();
        ImmutableStack<Integer> second = new ImmutableStack<>();
        Stack<Integer> result;
        int[] firstValues = new int[]{5, 9, -78, 42, 34};
        int[] secondValues = new int[]{-9, 58, 42, 58, 95, -9875, 36, 0, 0};
        for (int i : firstValues) {
            first = first.push(i);
        }
        for (int i : secondValues) {
            second = second.push(i);
        }
        result = s.zip(first, second);
        while (!(first.empty() && second.empty())) {
            if (!first.empty()) {
                assertThat(result.top(), equalTo(first.top()));
                result = result.pop();
                first = first.pop();
            }
            if (!second.empty()) {
                assertThat(result.top(), equalTo(second.top()));
                result = result.pop();
                second = second.pop();
            }
        }
    }

    @Test
    public void testZipEmpty() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> first = new ImmutableStack<>();
        ImmutableStack<Integer> second = new ImmutableStack<>();
        Stack<Integer> result;
        result = s.zip(first, second);
        assertThat(result.empty(), is(true));
    }

    @Test
    public void testUnzipN3() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        Stack<Integer>[] result;
        int[] values = new int[]{5, 9, -78, 42, 34, 128, 42, 34, 89, 57, -895};
        for (int i : values) {
            stack = stack.push(i);
        }
        int n = 3;
        result = s.unzip(stack, n);
        assertThat(result.length, equalTo(n));
        int i = values.length - 1;
        while (i >= 0) {
            for (int k = 0; k < n; k++) {
                if (!result[k].empty()) {
                    assertThat(result[k].top(), equalTo(values[i]));
                    result[k] = result[k].pop();
                    i--;
                }
            }
        }
    }
    
    @Test
    public void testUnzipN1() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        Stack<Integer>[] result;
        int[] values = new int[]{5, 9, -78, 42, 34, 128, 42, 34, 89, 57, -895};
        for (int i : values) {
            stack = stack.push(i);
        }
        int n = 1;
        result = s.unzip(stack, n);
        assertThat(result.length, equalTo(n));
        int i = values.length - 1;
        while (i >= 0) {
            for (int k = 0; k < n; k++) {
                if (!result[k].empty()) {
                    assertThat(result[k].top(), equalTo(values[i]));
                    result[k] = result[k].pop();
                    i--;
                }
            }
        }
    }

    @Test
    public void testUnzipEmpty() {
        Stacks s = Stacks.STACKS;
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        Stack<Integer>[] result;
        int n = 3;
        result = s.unzip(stack, n);
        assertThat(result.length, equalTo(n));
        for (int k = 0; k < n; k++) {
            assertThat(result[k].empty(), is(true));
        }
    }

}
