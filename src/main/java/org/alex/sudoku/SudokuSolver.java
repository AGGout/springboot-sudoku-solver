package org.alex.sudoku;

import org.alex.sudoku.backtracking.BacktrackingSolver;
import org.alex.sudoku.backtracking.Position;
import org.alex.sudoku.backtracking.SudokuProblem;
import org.alex.sudoku.model.SudokuStep;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * High-level Sudoku solver service.
 * Uses the generic backtracking framework for solving.
 * keeps a record of moves for demonstration purposes
 */
@Service
public class SudokuSolver {

    private static final int SIZE = 9;
    private final BacktrackingSolver<int[][], Position, Integer> solver;

    public SudokuSolver() {
        this.solver = new BacktrackingSolver<>();
    }

    /**
     * Public API: solves a Sudoku.
     * Does NOT mutate the input grid.
     *
     * @param original original grid (0 = empty)
     * @param steps list to record backtracking steps (may be null)
     * @return solved grid copy, or null if unsolvable
     */
    public int[][] solve(int[][] original, List<SudokuStep> steps) {
        validate(original);
        int[][] grid = deepCopy(original);

        SudokuProblem problem = new SudokuProblem(steps);
        boolean solved = solver.solve(grid, problem);

        return solved ? grid : null;
    }

    /**
     * Convenience overload without step recording.
     */
    public int[][] solve(int[][] original) {
        return solve(original, null);
    }

    // === validation and utility methods ===

    private void validate(int[][] grid) {
        if (grid == null || grid.length != SIZE) {
            throw new IllegalArgumentException("Grid must be 9x9");
        }
        for (int r = 0; r < SIZE; r++) {
            if (grid[r] == null || grid[r].length != SIZE) {
                throw new IllegalArgumentException("Grid must be 9x9");
            }
            for (int c = 0; c < SIZE; c++) {
                int v = grid[r][c];
                if (v < 0 || v > 9) {
                    throw new IllegalArgumentException("Cell values must be 0..9");
                }
            }
        }
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }
}
