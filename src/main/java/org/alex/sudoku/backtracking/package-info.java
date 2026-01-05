/**
 * Generic backtracking framework for solving constraint satisfaction problems.
 *
 * <h2>Architecture</h2>
 * <ul>
 *   <li>{@link org.alex.sudoku.backtracking.BacktrackingProblem} - Interface defining the problem contract</li>
 *   <li>{@link org.alex.sudoku.backtracking.BacktrackingSolver} - Generic solver implementing the backtracking algorithm</li>
 *   <li>{@link org.alex.sudoku.backtracking.SudokuProblem} - Sudoku-specific implementation</li>
 * </ul>
 *
 * <h2>Usage Example - N-Queens Problem</h2>
 * To solve other backtracking problems, implement {@code BacktrackingProblem}:
 *
 * <pre>{@code
 * public class NQueensProblem implements BacktrackingProblem<boolean[][], Integer, Integer> {
 *     private final int n;
 *
 *     public NQueensProblem(int n) { this.n = n; }
 *
 *     @Override
 *     public boolean isComplete(boolean[][] board) {
 *         // Check if all rows have a queen
 *         for (int row = 0; row < n; row++) {
 *             boolean hasQueen = false;
 *             for (int col = 0; col < n; col++) {
 *                 if (board[row][col]) hasQueen = true;
 *             }
 *             if (!hasQueen) return false;
 *         }
 *         return true;
 *     }
 *
 *     @Override
 *     public Integer findNextPosition(boolean[][] board) {
 *         // Return the next empty row (0-indexed)
 *         for (int row = 0; row < n; row++) {
 *             boolean isEmpty = true;
 *             for (int col = 0; col < n; col++) {
 *                 if (board[row][col]) isEmpty = false;
 *             }
 *             if (isEmpty) return row;
 *         }
 *         return null;
 *     }
 *
 *     @Override
 *     public Iterable<Integer> getCandidates(boolean[][] board, Integer row) {
 *         // Try each column in this row
 *         List<Integer> cols = new ArrayList<>();
 *         for (int col = 0; col < n; col++) cols.add(col);
 *         return cols;
 *     }
 *
 *     @Override
 *     public boolean isValid(boolean[][] board, Integer row, Integer col) {
 *         // Check no queens in same column or diagonals
 *         for (int r = 0; r < row; r++) {
 *             if (board[r][col]) return false;  // same column
 *             int colDiff = Math.abs(col - findQueenInRow(board, r));
 *             if (colDiff == row - r) return false;  // diagonal
 *         }
 *         return true;
 *     }
 *
 *     @Override
 *     public void place(boolean[][] board, Integer row, Integer col) {
 *         board[row][col] = true;
 *     }
 *
 *     @Override
 *     public void unplace(boolean[][] board, Integer row) {
 *         for (int col = 0; col < n; col++) board[row][col] = false;
 *     }
 *
 *     private int findQueenInRow(boolean[][] board, int row) {
 *         for (int col = 0; col < n; col++) {
 *             if (board[row][col]) return col;
 *         }
 *         return -1;
 *     }
 * }
 *
 * // Usage:
 * BacktrackingSolver<boolean[][], Integer, Integer> solver = new BacktrackingSolver<>();
 * boolean[][] board = new boolean[8][8];
 * NQueensProblem problem = new NQueensProblem(8);
 * solver.solve(board, problem);
 * }</pre>
 *
 * <h2>Other Applications</h2>
 * This framework can solve:
 * <ul>
 *   <li>Sudoku variants (Samurai Sudoku, Killer Sudoku, etc.)</li>
 *   <li>N-Queens problem</li>
 *   <li>Graph coloring</li>
 *   <li>Knight's tour</li>
 *   <li>Crossword puzzle generation</li>
 *   <li>Constraint satisfaction problems (CSP)</li>
 * </ul>
 */
package org.alex.sudoku.backtracking;
