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
import be.unamur.info.workbook.algorithmic.dynamicprogramming.QueensBooty.Direction;
import java.util.List;

/**
 * Unit tests for QueensBooty class.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class QueensBootyTest {

    private static final Logger LOG = LoggerFactory.getLogger(QueensBootyTest.class);

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;

    };
     
    @Test(expected = IllegalArgumentException.class)
    public void testNewEmptyValues() {
        int[][] values = new int[0][0];
        new QueensBooty(values);
        fail("Should have rejected empty values table!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewNonSqaureValues() {
        int[][] values = new int[2][5];
        new QueensBooty(values);
        fail("Should have rejected non square values table!");
    }

    @Test
    public void testGetSquareValues() {
        int[][] values = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        QueensBooty queen = new QueensBooty(values);
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                assertThat(queen.getSquareValue(i, j), equalTo(values[i][j]));
            }
        }
    }

    @Test
    public void testGetN() {
        int[][] values = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        QueensBooty queen = new QueensBooty(values);
        assertThat(queen.getN(), equalTo(values.length));
        values = new int[10][10];
        queen = new QueensBooty(values);
        assertThat(queen.getN(), equalTo(values.length));
    }

    @Test
    public void testPathsCount() {
        int[][] values = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        QueensBooty queen = new QueensBooty(values);
        for (int i = 0; i < queen.getN(); i++) {
            assertThat(queen.pathsCount(i, 0), equalTo(1));
            assertThat(queen.pathsCount(0, i), equalTo(1));
        }
        assertThat(queen.pathsCount(1, 1), equalTo(3));
        assertThat(queen.pathsCount(1, 2), equalTo(5));
        assertThat(queen.pathsCount(2, 2), equalTo(13));
    }

    @Test
    public void testComputeOptimalScore() {
        int[][] values = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        QueensBooty queen = new QueensBooty(values);
        assertThat(queen.computeOptimalScore(0, 0), equalTo(1));
        assertThat(queen.computeOptimalScore(0, 1), equalTo(3));
        assertThat(queen.computeOptimalScore(0, 2), equalTo(6));

        assertThat(queen.computeOptimalScore(1, 0), equalTo(5));
        assertThat(queen.computeOptimalScore(2, 0), equalTo(12));

        assertThat(queen.computeOptimalScore(1, 1), equalTo(10));
        assertThat(queen.computeOptimalScore(1, 2), equalTo(16));
        assertThat(queen.computeOptimalScore(2, 1), equalTo(20));
        assertThat(queen.computeOptimalScore(2, 2), equalTo(29));
    }

    @Test
    public void testComputeOptimalScoreBis() {
        int[][] values = new int[][]{
            {0, 25, 18, 42, 2},
            {8, 3, 38, 5, 5},
            {1, 2, 8, 84, 8},
            {9, 5, 2, 34, 1},
            {4, 42, 1, 27, 54},};
        QueensBooty queen = new QueensBooty(values);
        int[][] scores = new int[][]{
            {0, 25, 43, 85, 87},
            {8, 28, 81, 90, 95},
            {9, 30, 89, 174, 182},
            {18, 35, 91, 208, 209},
            {22, 77, 92, 235, 289}
        };
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores[i].length; j++) {
                assertThat("Failed at i=" + i + " j=" + j, queen.computeOptimalScore(i, j), equalTo(scores[i][j]));
            }
        }
    }
    
    @Test
    public void testComputeOptimalPath() {
        int[][] values = new int[][]{
            {0, 25, 18, 42, 2},
            {8, 3, 38, 5, 5},
            {1, 2, 8, 84, 8},
            {9, 5, 2, 34, 1},
            {4, 42, 1, 27, 54},};
        QueensBooty queen = new QueensBooty(values);
        List<Direction> result;
        result = queen.computeOptimalPath(0, 0);
        assertThat(result, hasSize(0));
        result = queen.computeOptimalPath(0, 1);
        assertThat(result, contains(Direction.Up));
        result = queen.computeOptimalPath(1, 0);
        assertThat(result, contains(Direction.Right));
        result = queen.computeOptimalPath(1, 1);
        assertThat(result, contains(Direction.Up, Direction.Right));
        result = queen.computeOptimalPath(queen.getN() - 1, queen.getN() - 1);
        assertThat(result, contains(Direction.Up, Direction.Up, Direction.Up, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Up));
    }

}
