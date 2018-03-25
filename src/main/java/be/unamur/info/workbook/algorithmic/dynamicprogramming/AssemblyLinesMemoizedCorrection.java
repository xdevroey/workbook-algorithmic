package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * This class defines an AssemblyLines where the optimal time and optimal path
 * computations have been optimized using memoization. M is the number of
 * assembly lines and N is the number of machines.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class AssemblyLinesMemoizedCorrection extends AssemblyLines {

    /**
     * Represents the optimal time for the M assembly lines. Inv:
     * optimalTimePerMachine.legngth == M or optimalTimePerMachine is null. 
     * optimalTimePerMachine[i] is the optimal time to perform all the tasks 
     * and end up in assembly line i.
     */
    private int[] optimalTimePerMachine;

    /**
     * Represents the optimal paths for the M assembly lines. Inv:
     * optimalPaths.legngth == M or optimalPaths is null.  
     * optimalPaths[i] is the optimal path to perform all the tasks 
     * and end up in assembly line i.
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
    public AssemblyLinesMemoizedCorrection(int nbMachines, int nbLines) {
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
        // Initialise the tables with the time to perfrom the first task
        for(int lineIdx = 0; lineIdx < optimalTimePerMachine.length ; lineIdx++){
            optimalTimePerMachine[lineIdx] = getTaskTime(0, lineIdx);
            optimalPaths[lineIdx] = Lists.newArrayList();
            optimalPaths[lineIdx].add(lineIdx);
        }
        
        for(int task = 1 ; task < getMachinesCount(); task++ ){
            int[] tmpOptimalTimePerMachine = new int[optimalTimePerMachine.length];
            List<Integer>[] tmpOptimalPaths = new List[optimalPaths.length];
            // Compute the new row of optimage values.
            for(int lineIdx = 0 ; lineIdx < getAssemblyLinesCount(); lineIdx++){
                // Select first assembly line a minimum candidate
                int min = optimalTimePerMachine[0] + getMoveTime(task - 1, 0, lineIdx);
                List<Integer> minPath = optimalPaths[0];
                for(int k = 1; k < getAssemblyLinesCount(); k++){
                    // Check next asembly lines if there is a better candidate
                    int candidateMin = optimalTimePerMachine[k] + getMoveTime(task - 1, k, lineIdx);
                    if(candidateMin < min){
                        min = candidateMin;
                        minPath = optimalPaths[k];
                    }
                }
                // Optimal time to perform previous tasks + this task is 
                // the minimal optimal time for previous machines + time took
                // by this machine.
                tmpOptimalTimePerMachine[lineIdx] = min + getTaskTime(task, lineIdx);
                // Save path of the optimal time
                tmpOptimalPaths[lineIdx] = Lists.newArrayList(minPath);
                tmpOptimalPaths[lineIdx].add(lineIdx);                
            }
            optimalTimePerMachine = tmpOptimalTimePerMachine;
            optimalPaths = tmpOptimalPaths;
        }
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
        // If the values of the assembly lines have been modifies, the memoization has to be performed again
        if(hasBeenModified()){
            memoize();
        }
        int optimal = optimalTimePerMachine[0];
        for(int i = 1 ; i < optimalTimePerMachine.length ; i++){
            if(optimalTimePerMachine[i] < optimal){
                optimal = optimalTimePerMachine[i];
            }
        }
        return optimal;
    }

    @Override
    public Pair<List<Integer>, Integer> optimalPath() {
        if(hasBeenModified()){
            memoize();
        }
        Pair<List<Integer>, Integer> optimal = new Pair(optimalPaths[0], optimalTimePerMachine[0]);
        for(int i = 1 ; i < optimalTimePerMachine.length ; i++){
            if(optimalTimePerMachine[i] < optimal.getSecond()){
                optimal = new Pair(optimalPaths[i], optimalTimePerMachine[i]);
            }
        }
        return optimal;
    }
    
}
