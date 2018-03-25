package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import java.util.Arrays;
import java.util.List;
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
 * Unit tests for LongestCommonSubsequenceDynamic class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class LongestCommonSubsequenceDynamicTest {

    private static final Logger LOG = LoggerFactory.getLogger(LongestCommonSubsequenceDynamicTest.class);

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
    public void testLongestSizeDifferentSequences() {
        Character[] seq1 = "ab1234567890".chars().mapToObj(x -> (char) x).toArray(Character[]::new);
        Character[] seq2 = "bcdefghijklmnopqrst0".chars().mapToObj(x -> (char) x).toArray(Character[]::new);
        LongestCommonSubsequenceDynamic<Character> longestCommon;
        longestCommon = new LongestCommonSubsequenceDynamic(seq1, seq2);
        List<Character> expected = Arrays.asList('b', '0');
        assertThat(longestCommon.longestSize(), equalTo(expected.size()));
        assertThat(longestCommon.longest(), equalTo(expected));
    }

    @Test
    public void testLongestSizeSameSequences() {
        Character[] seq1 = "ab90".chars().mapToObj(x -> (char) x).toArray(Character[]::new);
        LongestCommonSubsequenceDynamic<Character> longestCommon;
        longestCommon = new LongestCommonSubsequenceDynamic(seq1, seq1);
        List<Character> expected = Arrays.asList(seq1);
        assertThat(longestCommon.longestSize(), equalTo(expected.size()));
        assertThat(longestCommon.longest(), equalTo(expected));
    }

    @Test
    public void testLongestEmptySequences() {
        Character[] seq1 = new Character[0];
        Character[] seq2 = "bcdefg0".chars().mapToObj(x -> (char) x).toArray(Character[]::new);
        LongestCommonSubsequenceDynamic<Character> longestCommon;
        longestCommon = new LongestCommonSubsequenceDynamic(seq1, seq2);
        assertThat(longestCommon.longestSize(), equalTo(0));
        assertThat(longestCommon.longest(), empty());
    }

}
