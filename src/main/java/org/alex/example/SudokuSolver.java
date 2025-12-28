package org.alex.example;

import org.springframework.stereotype.Service;

@Service
public class SudokuSolver {

    private static final int SIZE = 9;

    /**
     * Public API: takes a grid, returns a solved copy or null if unsolvable.
     */
    public int[][] solveCopy(int[][] original) {
        if (original == null || original.length != SIZE) {
            throw new IllegalArgumentException("Grid must be 9x9");
        }
        int[][] grid = deepCopy(original);
        if (solve(grid)) {
            return grid;
        } else {
            return null;
        }
    }

    /**
     * Classic backtracking solver: mutates the grid in-place.
     * @return true if solution found.
     */
    public boolean solve(int[][] grid) {
        int[] emptyPos = findEmpty(grid);
        if (emptyPos == null) {
            // no empty cells -> solved
            return true;
        }
        int row = emptyPos[0];
        int col = emptyPos[1];

        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solve(grid)) {
                    return true;
                }

                // backtrack
                grid[row][col] = 0;
            }
        }

        // no valid number for this cell -> trigger backtracking
        return false;
    }

    private int[] findEmpty(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    return new int[]{row, col};
                }
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
        for (int col = 0; col < SIZE; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(int[][] grid, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[boxStartRow + row][boxStartCol + col] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }
}

