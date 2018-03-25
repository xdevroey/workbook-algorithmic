/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Unit test of Fibonacci class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class FibonacciTest {

    private static final Logger LOG = LoggerFactory.getLogger(FibonacciTest.class);
    private static final int[] ORACLE = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};

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
    public void testFibonacciDescendentRecursion() {
        for (int i = 1; i <= ORACLE.length; i++) {
            int result = Fibonacci.fibonacciDescendentRecursion(i);
            assertThat("Computing Fibonacci element n=" + i, result, equalTo(ORACLE[i - 1]));
        }
    }
    
    @Test
    public void testFibonacciMemoised() {
        for (int i = 1; i <= ORACLE.length; i++) {
            int result = Fibonacci.SEQUENCE.fibonacciMemoised(i);
            assertThat("Computing Fibonacci element n=" + i, result, equalTo(ORACLE[i - 1]));
        }
    }

    @Test
    public void testFibonacciAscendantIteration() {
        for (int i = 1; i <= ORACLE.length; i++) {
            int result = Fibonacci.fibonacciAscendantIteration(i);
            assertThat("Computing Fibonacci element n=" + i, result, equalTo(ORACLE[i - 1]));
        }
    }
    
    @Test
    public void testFibonacciAscendantRecursion() {
        for (int i = 1; i <= ORACLE.length; i++) {
            int result = Fibonacci.fibonacciAscendantIteration(i);
            assertThat("Computing Fibonacci element n=" + i, result, equalTo(ORACLE[i - 1]));
        }
    }
    
    
}
