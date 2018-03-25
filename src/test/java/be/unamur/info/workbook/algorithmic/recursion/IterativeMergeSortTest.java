package be.unamur.info.workbook.algorithmic.recursion;

import java.util.Arrays;
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
 * Unit test of IterativeMergeSort class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class IterativeMergeSortTest {

    private static final Logger LOG = LoggerFactory.getLogger(IterativeMergeSortTest.class);

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
    public void testSortPositiveUniqueValues() {
        int[] tab = new int[]{4, 1, 5, 8, 0, 981, 34, 2089, 12};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }

    @Test
    public void testSortNegativeUniqueValues() {
        int[] tab = new int[]{-4, -1, -5, 0, -8, -981, -34, -2089, -12};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }

    @Test
    public void testSortMixUniqueValues() {
        int[] tab = new int[]{-4, 1, -5, -8, 34, 981, 0, -34, -2089, -12};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }

    @Test
    public void testSortPositiveValuesWithDoubles() {
        int[] tab = new int[]{4, 1, 5, 5, 8, 34, 34, 0, 981, 34, 2089, 4};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }

    @Test
    public void testSortNegativeValuesWithDoubles() {
        int[] tab = new int[]{-5, -1, -5, 0, -8, -34, -981, -34, -2089, 0};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }

    @Test
    public void testSortMixValuesWithDoubles() {
        int[] tab = new int[]{-4, 1, -5, 0, 34, 981, 0, 34, -2089, -4};
        int[] expected = Arrays.copyOf(tab, tab.length);
        Arrays.sort(expected);
        IterativeMergeSort.sort(tab);
        assertArrayEquals("Arrays are not equal!", expected, tab);
    }
    
    @Test
    public void testSortEmptyArray() {
        int[] tab = new int[]{};
        IterativeMergeSort.sort(tab);
        assertThat("Empty array should be empty after sort!", tab.length, equalTo(0));
    }
    

}
