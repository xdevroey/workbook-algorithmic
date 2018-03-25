package be.unamur.info.workbook.algorithmic.dynamicprogramming;

import static com.google.common.base.Preconditions.*;
import java.util.List;

/**
 * This class allows to compute the most interesting path for a queen moving on
 * a board of size n*n. Each square is assigned with a positive integer,
 * representing the value of the square. When the queen is in the square, she
 * adds the value of the square to the total score. The queen starts in the
 * bottom left corner and may only move up, right, and diagonnally (up right),
 * this is represented by the Direction enumeration. This class computes the
 * maximal score (and the path to get this maximal score) that the queen may
 * obtain for a given board.
 *
 * For a board of size n*n, a square with coordonates (i,j), where 0 &le; i,j
 * &lt; n, (i + 1, j) is the square on the right, (i, * j + 1) is the upside
 * square, and (i + 1, j + 1) is the square in diagonal.
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class QueensBooty {

    /**
     * This enumeration represents directions took by the queen on the board.
     */
    public enum Direction {
        Right,
        Up,
        Diagonally;
    }

    /**
     * FA: For a square with coordonates (i,j), the value is given by
     * board[i][j]. INV: board.length = board[i].length, for all 0 &le; i &lt;
     * n, where n is the size of the board.
     */
    private final int[][] board;

    /**
     * INV:
     * <ul>
     * <li> N>0, and</li>
     * <li> board.length == N and forall i in [0,N[: board[i].length == N
     * </ul>
     */
    protected boolean repOk() {
        boolean ok = getN() > 0 && board.length == getN();
        for (int i = 0; i < board.length && ok; i++) {
            ok = ok && board[i].length == getN();
        }
        return ok;
    }

    /**
     * Build a new board of size values.length with the given values for the
     * squares.
     *
     * @requires values.length = values[i].length, for all 0 &le; i &lt; n,
     * where n = values.length and values.length > 0
     * @modifies this.n and this.board.
     * @effects this.n = values.length and this.board[i][j] = values[i][j] for
     * all 0 &le; i,j &lt; this.n
     * @throws IllegalArgumentException if values.length &le; 0 or values.length
     * != values[i].length, for all 0 &le; i &lt; n
     */
    public QueensBooty(int[][] values) throws IllegalArgumentException {
        checkArgument(values.length > 0, "Values must contain at least one element!");
        int n = values.length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            checkArgument(values[i].length == n, "Values[i=%s] is not of size n=%s", i, n);
            System.arraycopy(values[i], 0, board[i], 0, n);
        }
    }

    /**
     * Returns this.n.
     *
     * @effects return = this.n
     */
    public int getN() {
        return board.length;
    }

    /**
     * Returns the value of the square at position (i,j) in the board.
     *
     * @requires 0 &le; i,j &lt this.n
     * @effects return = this.board[i][j]
     * @throws IndexOutOfBoundsException if not 0 &le; i, j &lt; n
     */
    public int getSquareValue(int i, int j) {
        checkPositionIndex(i, this.board.length);
        checkPositionIndex(j, this.board[i].length);
        return this.board[i][j];
    }

    /**
     * Returns the number of paths to square (i,j) possibles for the queen in
     * this board.
     *
     * @requires 0 &le; i, j &lt; n
     * @effects return = number of paths to square (i,j) for the queen in
     * this.board.
     * @throws IndexOutOfBoundsException if not 0 &le; i, j &lt; n
     */
    public int pathsCount(int i, int j) {
        checkPositionIndex(i, this.board.length);
        checkPositionIndex(j, this.board[i].length);
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Returns the optimal path for the queen ending in square (i,j) in this
     * board.
     *
     * @requires 0 &le; i, j &lt; n
     * @effects return = optimal path to get return.getFirst() score in this
     * board.
     * @throws IndexOutOfBoundsException if not 0 &le; i, j &lt; n
     */
    public List<Direction> computeOptimalPath(int i, int j) {
        checkPositionIndex(i, this.board.length);
        checkPositionIndex(j, this.board[i].length);
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    /**
     * Returns the optimal score for the queen ending in square (i,j) in this
     * board.
     *
     * @requires 0 &le; i, j &lt; n
     * @effects return = optimal score for the queen in this board.
     * @throws IndexOutOfBoundsException if not 0 &le; i, j &lt; n
     */
    public int computeOptimalScore(int i, int j) {
        checkPositionIndex(i, this.board.length);
        checkPositionIndex(j, this.board[i].length);
        //TODO: Build the method corresponding to the given specification
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
