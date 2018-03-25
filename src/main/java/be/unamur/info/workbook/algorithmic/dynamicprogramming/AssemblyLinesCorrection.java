package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import static com.google.common.base.Preconditions.*;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a set of M assembly lines, each with N machines. Each
 * machine has a coordinate M(i,j) where i is the index of the machine (between
 * [0;N[) and j is the index of the assembly line (between [0;M[. The time took
 * by a machine M(i,j) to complete its task is designated by t(i,j). The time
 * took to move a product at machine M(i,j) to the next machine but on another
 * line M(i+1,j') is designated by d(i,j,j').
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class AssemblyLinesCorrection {

    /**
     * The taskTime needed by each machine to complete its task. Inv: Assuming N
     * == the number of machines and M == the number of lines, taskTime[0..N-1,
     * 0..M-1] contains values >=0.
     */
    private final int[][] taskTime;

    /**
     * The taskTime needed to moveTime from machine M(i,j) to machine M(i+1, j2)
     * == moveTime[i][j][j2]. Inv: Assuming N == the number of machines and M ==
     * the number of lines, moveTime[0..N-2, 0..M-1, 0..M-1] contains values
     * >=0.
     */
    private final int[][][] moveTime;

    /**
     * INV:
     * <ul>
     * <li> M>0 and N>0 </li>
     * <li> taskTime.length == N and forall i in [0,N[: taskTime[i].length == M,
     * and
     * </li>
     * <li> moveTime.length == N-1 and forall i in [0,N-1[: moveTime[i].length
     * == M and forall j in [0,M[: moveTime[i][j].length == M, and </li>
     * <li>forall i j in [0,N[ [0,M[: taskTime[i][j] >= 0, and</li>
     * <li> forall i j k in [0,N-1[ [0,M[ [0,M[: moveTime[i][j][k] >= 0</li>
     * <li> forall i j k in [0,N-1[ [0,M[ [0,M[: moveTime[i][j][k] >= 0</li>
     * <li> forall i j in [0,N-1[ [0,M[: moveTime[i][j][j] = 0</li>
     * </ul>
     */
    protected boolean repOk() {
        int n = taskTime.length;
        boolean ok = n > 0;
        // Check taskTime INV
        ok = ok && taskTime[0].length > 0; // M > 0
        for (int i = 0; i < n && ok; i++) {
            ok = ok && taskTime[0].length == taskTime[i].length;
            for (int j = 0; j < taskTime[i].length && ok; j++) {
                ok = ok && taskTime[i][j] >= 0;
            }
        }
        // Check moveTime INV
        ok = ok && moveTime.length == (n - 1);
        for (int i = 0; i < (n - 1) && ok; i++) {
            ok = ok && moveTime[i].length == taskTime[0].length; // moveTime[i].length == M
            for (int j = 0; j < taskTime[0].length && ok; j++) {
                ok = ok && moveTime[i][j].length == taskTime[0].length; // moveTime[i][j].length == M
                ok = ok && moveTime[i][j][j] == 0; // moveTime[i][j][j] == 0
                for (int k = 0; k < taskTime[0].length && ok; k++) {
                    ok = ok && moveTime[i][j][k] >= 0;
                }
            }
        }
        return ok;
    }

    /**
     * Builds a new set of assembly lines with N == nbMachines and M == nbLines.
     *
     * @requires nbMachines > 0 and nbLines > 0
     * @modifies this.t and this.d
     * @effects this.N == nbMachines and this.M == nbLines and forall i j:
     * this.t(i,j) == 0 and forall i j k: d(i,j,k) == 0
     * @throws IllegalArgumentException if nbMachines &le; 0 or nbLines &le; 0
     */
    public AssemblyLinesCorrection(int nbMachines, int nbLines) {
        checkArgument(nbMachines > 0, "Argument 'nbMachines' must be > 0!");
        checkArgument(nbLines > 0, "Argument 'nbLines' must be > 0!");
        taskTime = new int[nbMachines][nbLines];
        for (int i = 0; i < taskTime.length; i++) {
            for (int j = 0; j < taskTime[i].length; j++) {
                taskTime[i][j] = 0;
            }
        }
        moveTime = new int[nbMachines - 1][nbLines][nbLines];
        for (int[][] sources : moveTime) {
            for (int[] destinations : sources) {
                for (int i = 0; i < destinations.length; i++) {
                    destinations[i] = 0;
                }
            }
        }
    }

    /**
     * Returns the number of machines in each assembly line (N).
     *
     * @effects returns == this.N
     */
    public int getMachinesCount() {
        return this.taskTime.length;
    }

    /**
     * Returns the number of assembly lines (M).
     *
     * @effects returns == this.M
     */
    public int getAssemblyLinesCount() {
        return this.taskTime[0].length;
    }

    /**
     * Returns the time took by machine at index machineIdx in assemble line
     * lineIdx to complete its task.
     *
     * @requires machineIdx in [0,N[ and lineIdx in [0,M[
     * @effects returns == t(machineIdx,lineIdx)
     * @throws IndexOutOfBoundsException If one of the given parameter is out of
     * the bounds
     */
    public int getTaskTime(int machineIdx, int lineIdx) {
        checkElementIndex(machineIdx, taskTime.length);
        checkElementIndex(lineIdx, taskTime[0].length);
        return taskTime[machineIdx][lineIdx];
    }

    /**
     * Sets the time t took by machine at index machineIdx in assemble line
     * lineIdx to complete its task.
     *
     * @requires machineIdx in [0,N[ and lineIdx in [0,M[ and t >= 0
     * @effects t(machineIdx,lineIdx) == t
     * @throws IndexOutOfBoundsException If one of the given parameter is out of
     * the bounds
     * @throws IllegalArgumentException If t &lt; 0
     */
    public void setTaskTime(int machineIdx, int lineIdx, int t) {
        checkElementIndex(machineIdx, taskTime.length);
        checkElementIndex(lineIdx, taskTime[0].length);
        checkArgument(t >= 0, "Argument 'time' must be >= 0!");
        taskTime[machineIdx][lineIdx] = t;
    }

    /**
     * Returns the time took to move a product from machine at index machineIdx
     * in assemble line lineIdx to machine at index machineIdx + 1 in assembly
     * line destLineIdx.
     *
     * @requires machineIdx in [0,N-1[ and lineIdx in [0,M[ and destLineIdx in
     * [0,M[
     * @effects returns == d(machineIdx,sourceLineIdx, destLineIdx)
     * @throws IndexOutOfBoundsException If one of the given parameter is out of
     * the bounds
     */
    public int getMoveTime(int machineIdx, int sourceLineIdx, int destLineIdx) {
        checkElementIndex(machineIdx, moveTime.length);
        checkElementIndex(sourceLineIdx, moveTime[machineIdx].length);
        checkElementIndex(destLineIdx, moveTime[machineIdx][destLineIdx].length);
        return moveTime[machineIdx][sourceLineIdx][destLineIdx];
    }

    /**
     * Sets the time t took to move a product from machine at index machineIdx
     * in assemble line lineIdx to machine at index machineIdx + 1 in assembly
     * line destLineIdx.
     *
     * @requires machineIdx in [0,N-1[ and lineIdx in [0,M[ and destLineIdx in
     * [0,M[ and t >= 0 and (sourceLineIdx == destLineIdx) implies (t==0)
     * @effects d(machineIdx,sourceLineIdx, destLineIdx) == t
     * @throws IndexOutOfBoundsException If one of the given parameter is out of
     * the bounds
     * @throws IllegalArgumentException If t &lt; 0 or (sourceLineIdx ==
     * destLineIdx) does not implies (t==0)
     */
    public void setMoveTime(int machineIdx, int sourceLineIdx, int destLineIdx, int t) {
        checkElementIndex(machineIdx, moveTime.length);
        checkElementIndex(sourceLineIdx, moveTime[machineIdx].length);
        checkElementIndex(destLineIdx, moveTime[machineIdx][destLineIdx].length);
        checkArgument(t >= 0 && (!(sourceLineIdx == destLineIdx) || (t == 0)), "Argument 'time' must be >= 0 and (sourceLineIdx == destLineIdx) implies (t==0)!");
        moveTime[machineIdx][sourceLineIdx][destLineIdx] = t;
    }

    /**
     * Returns the minimal time took by the assembly lines to perform the
     * nbTasks first taskes.
     *
     * @requires nbTasks in [1,N] and lineIdx in [0,M[
     * @effects returns == T*(nbTasks,lineIdx+1) == the minimal time to perform
     * nbTasks tasks and end up on assemble line lineIdx.
     * @throws IndexOutOfBoundsException If lineIdx is out of the bounds.
     * @throws IllegalArgumentException If nbTasks is not between 0 and N.
     */
    public int minimalTime(int nbTasks, int lineIdx) {
        checkArgument(nbTasks > 0 && nbTasks <= taskTime.length);
        checkElementIndex(lineIdx, taskTime[0].length);
        if (nbTasks == 1) {
            return taskTime[0][lineIdx];
        } else {
            int machineIdx = nbTasks - 1; // Transform to index in taskTime
            // Compute minimal previous time by iterating over different possibilities
            int min = minimalTime(nbTasks - 1, 0) + moveTime[machineIdx - 1][0][lineIdx];
            int k = 1;
            while (k < getAssemblyLinesCount()) {
                int min_tmp = minimalTime(nbTasks - 1, k) + moveTime[machineIdx - 1][k][lineIdx];
                if (min_tmp < min) {
                    min = min_tmp;
                }
                k++;
            }
            // Add time took by the last machine on lineIdx assembly line
            return min + taskTime[machineIdx][lineIdx];
        }
    }

    /**
     * Returns the optimal time took by the assembly line to build a complete
     * product.
     *
     * @effects returns == T*(N,k) such as there exists no l in [0,M[: T*(N,l)
     * &lt; T*(N,k)
     */
    public int optimalTime() {
        int min = minimalTime(getMachinesCount(), 0);
        for (int j = 1; j < getAssemblyLinesCount(); j++) {
            int tmp = minimalTime(getMachinesCount(), j);
            if (tmp < min) {
                min = tmp;
            }
        }
        return min;
    }

    /**
     * Returns the path of the minimal time took by the assembly lines to
     * perform the nbTasks first taskes.
     *
     * @requires nbTasks in [1,N] and lineIdx in [0,M[
     * @effects returns == ((j1,j2,...,j nbTasks); opt) such as (M(1,j1),
     * M(2,j2), ..., M(nbTasks,j nbTasks)) is the sequence of machines such as
     * the total time opt took to perform nbTasks tasks and end up on assemble
     * line lineIdx is minimal.
     * @throws IndexOutOfBoundsException If lineIdx is out of the bounds.
     * @throws IllegalArgumentException If nbTasks is not between 0 and N.
     */
    public Pair<List<Integer>, Integer> minimalPath(int nbTasks, int lineIdx) {
        checkArgument(nbTasks > 0 && nbTasks <= taskTime.length);
        checkElementIndex(lineIdx, taskTime[0].length);
        if (nbTasks == 1) {
            return new Pair(Lists.newArrayList(lineIdx), taskTime[0][lineIdx]);
        } else {
            int machineIdx = nbTasks - 1; // Transform to index in taskTime
            Pair<List<Integer>, Integer> optimal = new Pair(null, Integer.MAX_VALUE);
            Pair<List<Integer>, Integer> candidate;
            // Iterates over all possible assembly lines
            for (int k = 0; k < getAssemblyLinesCount(); k++) {
                candidate = minimalPath(nbTasks - 1, k);
                // Add move time to lineIdx for the current candidates
                candidate.setSecond(candidate.getSecond() + moveTime[machineIdx - 1][k][lineIdx]);
                // Check if the time value is lower
                if (candidate.getSecond() < optimal.getSecond()) {
                    // optimal path gets a copy of the list 
                    optimal.setFirst(Lists.newArrayList(candidate.getFirst()));
                    // add the current machine
                    optimal.getFirst().add(lineIdx);
                    // Sets optimal time value to candidate value + time of the current machine 
                    optimal.setSecond(candidate.getSecond() + taskTime[machineIdx][lineIdx]);
                }
            }
            // There will always be at least one iteration, thanks to the INV (N>0)
            return optimal;
        }

    }

    /**
     * Returns the optimal sequence of lines to use to build a complete product
     * in a minimal time.
     *
     * @effects returns == minimalPath(N,k) such as there exists no l in [0,M[
     * with a sequence of lines to use minimalPath(N,l) that takes less time to
     * build a complete product.
     */
    public Pair<List<Integer>, Integer> optimalPath() {
        Pair<List<Integer>, Integer> optimal = minimalPath(getMachinesCount(), 0);
        for (int j = 1; j < getAssemblyLinesCount(); j++) {
            Pair<List<Integer>, Integer> candidate = minimalPath(getMachinesCount(), j);
            if (candidate.getSecond() < optimal.getSecond()) {
                optimal = candidate;
            }
        }
        return optimal;
    }

    @Override
    public String toString() {
        return "AssemblyLines{" + "taskTime=" + Arrays.deepToString(taskTime)
                + ", moveTime=" + Arrays.deepToString(moveTime) + '}';
    }

}
