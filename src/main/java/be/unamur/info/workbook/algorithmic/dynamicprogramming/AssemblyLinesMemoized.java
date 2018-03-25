package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import java.util.List;

/**
 * This class defines an AssemblyLines where the optimal time and optimal path
 * computations have been optimized using memoization. M is the number of
 * assembly lines and N is the number of machines.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class AssemblyLinesMemoized extends AssemblyLines {

    /**
     * Represents the optimal time for the M assembly lines. Inv:
     * optimalTimePerMachine.legngth == M or optimalTimePerMachine is null.
     * optimalTimePerMachine[i] is the optimal time to perform all the tasks and
     * end up in assembly line i.
     */
    private int[] optimalTimePerMachine;

    /**
     * Represents the optimal paths for the M assembly lines. Inv:
     * optimalPaths.legngth == M or optimalPaths is null. optimalPaths[i] is the
     * optimal path to perform all the tasks and end up in assembly line i.
     */
    private List<Integer>[] optimalPaths;

    /**
     * INV:
     * <ul>
     * <li> M>0 and N>0 </li>
     * <li> optimalTimePerMachine not null => optimalTimePerMachine.length ==
     * M</li>
     * <li> optimalPaths not null => optimalPaths.length == M</li>
     * <li> optimalPaths is null &lt;=&gt; optimalTimePerMachine is null</li>
     * </ul>
     */
    @Override
    protected boolean repOk() {
        boolean ok = super.repOk();
        ok = ok && (!(optimalTimePerMachine != null) || (optimalTimePerMachine.length == getAssemblyLinesCount()));
        ok = ok && (!(optimalPaths != null) || (optimalPaths.length == getAssemblyLinesCount()));
        ok = ok && ((optimalPaths == null && optimalTimePerMachine == null) || (optimalPaths != null && optimalTimePerMachine != null));
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
    public AssemblyLinesMemoized(int nbMachines, int nbLines) {
        super(nbMachines, nbLines);
    }

    /**
     * Sets the internal memory used to record time and path to the optimal
     * solutions giving this.
     *
     * @effects Internal memory has recorded optimal time and path solutions for
     * the M assembly lines.
     */
    private void memoize() {
        optimalTimePerMachine = new int[getAssemblyLinesCount()];
        optimalPaths = new List[getAssemblyLinesCount()];
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Resets the memory used to record optimal time and optimal path.
     *
     * @modifies this
     * @effects Internal memory used to record optimal time and optimal path is
     * reset.
     */
    private void resetMemoize() {
        optimalTimePerMachine = null;
        optimalPaths = null;
    }

    /**
     * Returns true if the internal state has been modified since last call to
     * memoize.
     *
     * @effects return = true if the internal state has been modified since last
     * call to memoize.
     */
    private boolean hasBeenModified() {
        return optimalTimePerMachine == null;
    }

    @Override
    public void setMoveTime(int machineIdx, int sourceLineIdx, int destLineIdx, int t) {
        super.setMoveTime(machineIdx, sourceLineIdx, destLineIdx, t);
        resetMemoize(); // Internal state has changed, memoization has to be recompute
    }

    @Override
    public void setTaskTime(int machineIdx, int lineIdx, int t) {
        super.setTaskTime(machineIdx, lineIdx, t);
        resetMemoize(); // Internal state has changed, memoization has to be recompute
    }

    @Override
    public int optimalTime() {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public Pair<List<Integer>, Integer> optimalPath() {
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
