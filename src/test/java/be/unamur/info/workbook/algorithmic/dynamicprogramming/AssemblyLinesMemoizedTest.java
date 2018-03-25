package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test of AssemblyLInesMemoized class.
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class AssemblyLinesMemoizedTest extends AssemblyLinesTest{

    private static final Logger LOG = LoggerFactory.getLogger(AssemblyLinesMemoizedTest.class);

    @Before
    @Override
    public void setUp() {
        lines = new AssemblyLinesMemoized(NB_MACHINES, NB_LINES);
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


}