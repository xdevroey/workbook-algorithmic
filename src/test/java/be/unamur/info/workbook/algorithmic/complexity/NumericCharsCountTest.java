package be.unamur.info.workbook.algorithmic.complexity;

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
 * Unit test of NumericCharsCount class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class NumericCharsCountTest {

    private static final Logger LOG = LoggerFactory.getLogger(NumericCharsCountTest.class);

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
    public void testNumericCharsCountIterativeNone() {
        String input = "";
        int result = NumericCharsCount.numericCharsCountIterative(input.toCharArray());
        assertThat(result, equalTo(0));
    }

    @Test
    public void testNumericCharsCountIterativeOnlyAlpha() {
        String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int result = NumericCharsCount.numericCharsCountIterative(input.toCharArray());
        assertThat(result, equalTo(0));
    }

    @Test
    public void testNumericCharsCountIterativeNotAlphanum() {
        String input = ",;:=+/.?&()!\\";
        int result = NumericCharsCount.numericCharsCountIterative(input.toCharArray());
        assertThat(result, equalTo(0));
    }
    
    @Test
    public void testNumericCharsCountIterativeOnlyNum() {
        String input = "0123456789";
        int result = NumericCharsCount.numericCharsCountIterative(input.toCharArray());
        assertThat(result, equalTo(input.length()));
    }    
    
    @Test
    public void testNumericCharsCountIterativeAlphaNum() {
        String input = "a0123b456c789defghi102365jklm5599641nopq66874592rstu5789413vwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int result = NumericCharsCount.numericCharsCountIterative(input.toCharArray());
        assertThat(result, equalTo(input.replaceAll("[^0-9]", "").length()));
    }

    @Test
    public void testNumericCharsCountRecursiveNone() {
        String input = "";
        int result = NumericCharsCount.numericCharsCountRecursive(input.toCharArray());
        assertThat(result, equalTo(0));
    }

    @Test
    public void testNumericCharsCountRecursiveOnlyAlpha() {
        String input = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int result = NumericCharsCount.numericCharsCountRecursive(input.toCharArray());
        assertThat(result, equalTo(0));
    }

    @Test
    public void testNumericCharsCountRecursiveNotAlphanum() {
        String input = ",;:=+/.?&()!\\";
        int result = NumericCharsCount.numericCharsCountRecursive(input.toCharArray());
        assertThat(result, equalTo(0));
    }
    
    @Test
    public void testNumericCharsCountRecursiveOnlyNum() {
        String input = "0123456789";
        int result = NumericCharsCount.numericCharsCountRecursive(input.toCharArray());
        assertThat(result, equalTo(input.length()));
    }    
    
    @Test
    public void testNumericCharsCountRecursiveAlphaNum() {
        String input = "a0123b456c789defghi102365jklm5599641nopq66874592rstu5789413vwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int result = NumericCharsCount.numericCharsCountRecursive(input.toCharArray());
        assertThat(result, equalTo(input.replaceAll("[^0-9]", "").length()));
    }

}