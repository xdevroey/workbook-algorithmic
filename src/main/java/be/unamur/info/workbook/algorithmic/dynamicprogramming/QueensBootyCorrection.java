package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import com.google.common.collect.Lists;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * QueensBooty exercice correction.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class QueensBootyCorrection extends QueensBooty {

    private static final Logger LOG = LoggerFactory.getLogger(QueensBootyCorrection.class);

    /**
     * The number of paths existing to access square (i,j).
     */
    private int[][] paths;

    /**
     * The opotimal score that the queen may obtain by moving on this board and
     * end in score[i][j].
     */
    private int[][] score;

    /**
     * directions[i][j] is the direction took by the queen to end in this square
     * in order to maximise the score (which values is in score[i][j]).
     */
    private Direction[][] directions;

    public QueensBootyCorrection(int[][] values) throws IllegalArgumentException {
        super(values);
    }

    private void memoizePathCount() {
        if (paths != null) {
            return;
        }
        paths = new int[getN()][getN()];
        for (int i = 0; i < paths.length; i++) {
            paths[i][0] = 1;
            paths[0][i] = 1;
        }
        // Initialise first line and first column
        for (int i = 1; i < paths.length; i++) {
            for (int j = 1; j < paths[i].length; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1] + paths[i - 1][j - 1];
            }
        }
    }

    @Override
    public int pathsCount(int i, int j) {
        memoizePathCount();
        return paths[i][j];
//        if (i == 0 || j == 0) {
//            return 1;
//        } else {
//            return pathsCount(i - 1, j) + pathsCount(i, j - 1) + pathsCount(i - 1, j - 1);
//        }
    }

    private void memoizeBestPaths() {
        if (score != null) {
            return;
        }
        score = new int[getN()][getN()];
        directions = new Direction[getN()][getN()];
        score[0][0] = getSquareValue(0, 0);
        // Initialise bottom row
        for (int i = 1; i < getN(); i++) {
            score[i][0] = score[i - 1][0] + getSquareValue(i, 0);
            directions[i][0] = Direction.Right;
        }
        // Initialise left column
        for (int j = 1; j < getN(); j++) {
            score[0][j] = score[0][j - 1] + getSquareValue(0, j);
            directions[0][j] = Direction.Up;
        }
        // For the other squares, take the best choice between left, down and diag.
        for (int i = 1; i < getN(); i++) {
            for (int j = 1; j < getN(); j++) {
                int left = score[i - 1][j] + getSquareValue(i, j);
                int bottom = score[i][j - 1] + getSquareValue(i, j);
                int diag = score[i - 1][j - 1] + getSquareValue(i, j);
                if (left >= bottom && left >= diag) {
                    score[i][j] = left;
                    directions[i][j] = Direction.Right;
                } else if (bottom >= left && bottom >= diag) {
                    score[i][j] = bottom;
                    directions[i][j] = Direction.Up;
                } else {
                    score[i][j] = diag;
                    directions[i][j] = Direction.Diagonally;
                }
            }
        }
    }

    @Override
    public List<Direction> computeOptimalPath(int i, int j) {
        memoizeBestPaths();
        List<Direction> path = Lists.newLinkedList();
        int k = i;
        int l = j;
        while (!(k == 0 && l == 0)) {
            switch (directions[k][l]) {
                case Diagonally:
                    path.add(0, Direction.Diagonally);
                    k--;
                    l--;
                    break;
                case Right:
                    path.add(0, Direction.Right);
                    k--;
                    break;
                case Up:
                    path.add(0, Direction.Up);
                    l--;
                    break;
                default:
                    LOG.error("Direction {} has not been taken into account!", directions[k][l]);
            }
        }
        return path;
    }

    @Override
    public int computeOptimalScore(int i, int j) {
        memoizeBestPaths();
        return score[i][j];
    }

}
