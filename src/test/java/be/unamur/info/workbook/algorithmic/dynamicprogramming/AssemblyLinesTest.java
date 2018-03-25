package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test of AssemblyLines class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class AssemblyLinesTest extends AssemblyLines {

    private static final Logger LOG = LoggerFactory.getLogger(AssemblyLinesTest.class);

    protected static final int MAX_TASK_TIME = 99999;
    protected static final int NB_LINES = 5;
    protected static final int NB_MACHINES = 10;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;

    };
        
    protected AssemblyLines lines;

    public AssemblyLinesTest() {
        super(NB_MACHINES, NB_LINES);
    }

    @Before
    public void setUp() {
        lines = new AssemblyLines(NB_MACHINES, NB_LINES);
        // Populate with values
        for (int i = 0; i < NB_MACHINES; i++) {
            for (int j = 0; j < NB_LINES; j++) {
                lines.setTaskTime(i, j, MAX_TASK_TIME);
                if (i < NB_MACHINES - 1) {
                    for (int k = 0; k < NB_LINES; k++) {
                        if (k != j) {
                            lines.setMoveTime(i, j, k, MAX_TASK_TIME);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testInitialization() {
        // Check new instance is repOk
        assertThat(lines.getMachinesCount(), equalTo(NB_MACHINES));
        assertThat(lines.getAssemblyLinesCount(), equalTo(NB_LINES));
        assertThat(lines.repOk(), equalTo(true));
        // Check values
        for (int i = 0; i < NB_MACHINES; i++) {
            for (int j = 0; j < NB_LINES; j++) {
                assertThat(lines.getTaskTime(i, j), equalTo(MAX_TASK_TIME));
                if (i < NB_MACHINES - 1) {
                    for (int k = 0; k < NB_LINES; k++) {
                        if (k != j) {
                            assertThat(lines.getMoveTime(i, j, k), equalTo(MAX_TASK_TIME));
                        }
                    }
                }
            }
        }
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testNbMachines0() {
        new AssemblyLines(0, 5);
        fail("Should have raised an Exception!");
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testNbMachinesNegative() {
        new AssemblyLines(-5, 5);
        fail("Should have raised an Exception!");
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testNbLines0() {
        new AssemblyLines(10, 0);
        fail("Should have raised an Exception!");
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void testNbLinesNegative() {
        new AssemblyLines(10, -5);
        fail("Should have raised an Exception!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTaskTimeNegative() {
        lines.setTaskTime(0, 0, -5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetTaskTimeMachineIdxOutOfBounds() {
        lines.setTaskTime(NB_MACHINES, 0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetTaskTimeLineIdxOutOfBounds() {
        lines.setTaskTime(0, NB_LINES, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetMoveTimeNegative() {
        lines.setMoveTime(0, 0, 1, -5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetMoveTimeMachineIdxOutOfBounds() {
        lines.setMoveTime(NB_MACHINES - 1, 0, 1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetMoveSourceLineIdxOutOfBounds() {
        lines.setMoveTime(0, NB_LINES, 0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetMoveDestLineIdxOutOfBounds() {
        lines.setMoveTime(0, 0, NB_LINES, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTaskTimeMachineIdxOutOfBounds() {
        lines.getTaskTime(NB_MACHINES, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTaskTimeLineIdxOutOfBounds() {
        lines.getTaskTime(0, NB_LINES);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMoveTimeMachineIdxOutOfBounds() {
        lines.getMoveTime(NB_MACHINES - 1, 0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMoveSourceLineIdxOutOfBounds() {
        lines.getMoveTime(0, NB_LINES, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMoveDestLineIdxOutOfBounds() {
        lines.getMoveTime(0, 0, NB_LINES);
    }

    // Tests on minimal and optimal time
    @Test(expected = IndexOutOfBoundsException.class)
    public void testMinimalTimeLineIdxOutOfBounds() {
        lines.minimalTime(1, NB_LINES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinimalTimeNbTasks0() {
        lines.minimalTime(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinimalTimeNbTasksNeg() {
        lines.minimalTime(0, -2);
    }

    @Test
    public void testMinimalTimeOneTask() {
        int lineIdx = 1;
        int time = 2;
        lines.setTaskTime(0, lineIdx, time);
        LOG.debug("Computing minimal time for {}", lines);
        assertThat(lines.minimalTime(1, lineIdx), equalTo(time));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testMinimalTimeMoreTasksSameLine() {
        lines.setTaskTime(0, 0, 1);
        lines.setTaskTime(1, 0, 2);
        lines.setTaskTime(2, 0, 3);
        lines.setTaskTime(3, 0, 4);
        LOG.debug("Computing minimal time for {}", lines);
        assertThat(lines.minimalTime(4, 0), equalTo(10));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testMinimalTimeMoreTasksWithLineChanges() {
        lines.setTaskTime(0, 0, 1);
        lines.setMoveTime(0, 0, 1, 10);
        lines.setTaskTime(1, 1, 2);
        lines.setMoveTime(1, 1, 2, 12);
        lines.setTaskTime(2, 2, 3);
        lines.setMoveTime(2, 2, 3, 10);
        lines.setTaskTime(3, 3, 4);
        LOG.debug("Computing minimal time for {}", lines);
        assertThat(lines.minimalTime(4, 3), equalTo(42));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testOptimalTimeSameLine() {
        int expected = 0;
        for (int i = 0; i < lines.getMachinesCount(); i++) {
            int time = i * 2 + 1;
            lines.setTaskTime(i, 0, time);
            expected = expected + time;
        }
        LOG.debug("Computing optimal time for {}", lines);
        assertThat(lines.optimalTime(), equalTo(expected));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testOptimalTimeDifferentLines() {
        int expected = 0;
        int val;
        lines.setTaskTime(0, 0, val = 1);
        expected = expected + val;
        lines.setMoveTime(0, 0, 1, val = 10);
        expected = expected + val;
        lines.setTaskTime(1, 1, val = 2);
        expected = expected + val;
        lines.setMoveTime(1, 1, 2, val = 12);
        expected = expected + val;
        lines.setTaskTime(2, 2, val = 3);
        expected = expected + val;
        lines.setMoveTime(2, 2, 3, val = 10);
        expected = expected + val;
        lines.setTaskTime(3, 3, val = 4);
        expected = expected + val;
        lines.setMoveTime(3, 3, 4, val = 5);
        expected = expected + val; 
        lines.setTaskTime(4, 4, val = 5);
        expected = expected + val;
        lines.setMoveTime(4, 4, 3, val = 5);
        expected = expected + val;
        lines.setTaskTime(5, 3, val = 4);
        expected = expected + val;
        lines.setMoveTime(5, 3, 2, val = 5);
        expected = expected + val;
        lines.setTaskTime(6, 2, val = 4);
        expected = expected + val;
        lines.setMoveTime(6, 2, 1, val = 5);
        expected = expected + val;
        lines.setTaskTime(7, 1, val = 9);
        expected = expected + val;
        lines.setMoveTime(7, 1, 0, val = 3);
        expected = expected + val;
        lines.setTaskTime(8, 0, val = 9);
        expected = expected + val;
        lines.setMoveTime(8, 0, 1, val = 0);
        expected = expected + val;
        lines.setTaskTime(9, 1, val = 2);
        expected = expected + val;
        LOG.debug("Computing optimal time for {}", lines);
        assertThat(lines.optimalTime(), equalTo(expected));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    // Tests on minimal and optimal paths
    @Test(expected = IndexOutOfBoundsException.class)
    public void testMinimalPathLineIdxOutOfBounds() {
        lines.minimalPath(1, NB_LINES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinimalPathNbTasks0() {
        lines.minimalPath(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinimalPathNbTasksNeg() {
        lines.minimalPath(0, -2);
    }

    @Test
    public void testMinimalPathOneTask() {
        int lineIdx = 1;
        int time = 2;
        lines.setTaskTime(0, lineIdx, time);
        Pair<List<Integer>, Integer> result = lines.minimalPath(1, lineIdx);
        assertThat(result.getSecond(), equalTo(time));
        assertThat(result.getFirst(), contains(1));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testMinimalPathMoreTasksSameLine() {
        lines.setTaskTime(0, 0, 1);
        lines.setTaskTime(1, 0, 2);
        lines.setTaskTime(2, 0, 3);
        lines.setTaskTime(3, 0, 4);
        LOG.debug("Computing minimal path for {}", lines);
        Pair<List<Integer>, Integer> result = lines.minimalPath(4, 0);
        assertThat(result.getSecond(), equalTo(10));
        assertThat(result.getFirst(), contains(0, 0, 0, 0));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testMinimalPathMoreTasksWithLineChanges() {
        lines.setTaskTime(0, 0, 1);
        lines.setMoveTime(0, 0, 1, 10);
        lines.setTaskTime(1, 1, 2);
        lines.setMoveTime(1, 1, 2, 12);
        lines.setTaskTime(2, 2, 3);
        lines.setMoveTime(2, 2, 3, 10);
        lines.setTaskTime(3, 3, 4);
        LOG.debug("Computing minimal path for {}", lines);
        Pair<List<Integer>, Integer> result = lines.minimalPath(4, 3);
        assertThat(result.getSecond(), equalTo(42));
        assertThat(result.getFirst(), contains(0, 1, 2, 3));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }
    
    @Test
    public void testOptimalPathSameLine() {
        int expected = 0;
        List<Integer> expectedPath = Lists.newArrayList();
        for (int i = 0; i < lines.getMachinesCount(); i++) {
            int time = i * 2 + 1;
            lines.setTaskTime(i, 0, time);
            expected = expected + time;
            expectedPath.add(0);
        }
        LOG.debug("Computing optimal path for {}", lines);
        Pair<List<Integer>, Integer> result = lines.optimalPath();
        assertThat(result.getSecond(), equalTo(expected));
        assertThat(result.getFirst(), equalTo(expectedPath));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

    @Test
    public void testOptimalPathDifferentLines() {
        int expected = 0;
        int val;
        lines.setTaskTime(0, 0, val = 1);
        expected = expected + val;
        lines.setMoveTime(0, 0, 1, val = 10);
        expected = expected + val;
        lines.setTaskTime(1, 1, val = 2);
        expected = expected + val;
        lines.setMoveTime(1, 1, 2, val = 12);
        expected = expected + val;
        lines.setTaskTime(2, 2, val = 3);
        expected = expected + val;
        lines.setMoveTime(2, 2, 3, val = 10);
        expected = expected + val;
        lines.setTaskTime(3, 3, val = 4);
        expected = expected + val;
        lines.setMoveTime(3, 3, 4, val = 5);
        expected = expected + val; 
        lines.setTaskTime(4, 4, val = 5);
        expected = expected + val;
        lines.setMoveTime(4, 4, 3, val = 5);
        expected = expected + val;
        lines.setTaskTime(5, 3, val = 4);
        expected = expected + val;
        lines.setMoveTime(5, 3, 2, val = 5);
        expected = expected + val;
        lines.setTaskTime(6, 2, val = 4);
        expected = expected + val;
        lines.setMoveTime(6, 2, 1, val = 5);
        expected = expected + val;
        lines.setTaskTime(7, 1, val = 9);
        expected = expected + val;
        lines.setMoveTime(7, 1, 0, val = 3);
        expected = expected + val;
        lines.setTaskTime(8, 0, val = 9);
        expected = expected + val;
        lines.setMoveTime(8, 0, 1, val = 0);
        expected = expected + val;
        lines.setTaskTime(9, 1, val = 2);
        expected = expected + val;
        LOG.debug("Computing optimal path for {}", lines);
        Pair<List<Integer>, Integer> result = lines.optimalPath();
        assertThat(result.getSecond(), equalTo(expected));
        assertThat(result.getFirst(), contains(0, 1, 2, 3, 4, 3, 2, 1, 0, 1));
        // check repOk
        assertThat(lines.repOk(), equalTo(true));
    }

}
