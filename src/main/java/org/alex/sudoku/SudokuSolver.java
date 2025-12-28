package org.alex.sudoku.service;

import org.alex.sudoku.model.StepType;
import org.alex.sudoku.model.SudokuStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SudokuSolver {

    private static final int SIZE = 9;

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
        boolean solved = solveInternal(grid, steps);
        return solved ? grid : null;
    }

    /**
     * Convenience overload without step recording.
     */
    public int[][] solve(int[][] original) {
        return solve(original, null);
    }

    // === internal backtracking ===

    private boolean solveInternal(int[][] grid, List<SudokuStep> steps) {
        int[] empty = findEmpty(grid);
        if (empty == null) {
            return true;
        }

        int row = empty[0];
        int col = empty[1];

        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (steps != null) {
                    steps.add(new SudokuStep(row, col, num, StepType.PLACE));
                }

                if (solveInternal(grid, steps)) {
                    return true;
                }

                grid[row][col] = 0;
                if (steps != null) {
                    steps.add(new SudokuStep(row, col, 0, StepType.BACKTRACK));
                }
            }
        }
        return false;
    }

    // === helpers ===

    private int[] findEmpty(int[][] grid) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (grid[r][c] == 0) return new int[]{r, c};
            }
        }
        return null;
    }

    private boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num)
                && !usedInCol(grid, col, num)
                && !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int[][] grid, int row, int num) {
        for (int c = 0; c < SIZE; c++) if (grid[row][c] == num) return true;
        return false;
    }

    private boolean usedInCol(int[][] grid, int col, int num) {
        for (int r = 0; r < SIZE; r++) if (grid[r][col] == num) return true;
        return false;
    }

    private boolean usedInBox(int[][] grid, int boxRow, int boxCol, int num) {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (grid[boxRow + r][boxCol + c] == num) return true;
        return false;
    }

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
