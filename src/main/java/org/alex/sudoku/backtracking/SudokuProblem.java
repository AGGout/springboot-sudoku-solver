package org.alex.sudoku.backtracking;

import org.alex.sudoku.model.StepType;
import org.alex.sudoku.model.SudokuStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku-specific implementation of the backtracking problem interface.
 * Encapsulates all Sudoku rules and constraints.
 */
public class SudokuProblem implements BacktrackingProblem<int[][], Position, Integer> {

    private static final int SIZE = 9;
    private static final int BOX_SIZE = 3;
    private static final int EMPTY = 0;
    private final List<SudokuStep> steps;

    /**
     * Create a SudokuProblem with optional step recording.
     *
     * @param steps list to record steps (may be null)
     */
    public SudokuProblem(List<SudokuStep> steps) {
        this.steps = steps;
    }

    /**
     * Create a SudokuProblem without step recording.
     */
    public SudokuProblem() {
        this(null);
    }

    @Override
    public boolean isComplete(int[][] state) {
        // If we can't find any empty cell, the puzzle is complete
        return findNextPosition(state) == null;
    }

    @Override
    public Position findNextPosition(int[][] state) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (state[r][c] == EMPTY) {
                    return new Position(r, c);
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Integer> getCandidates(int[][] state, Position position) {
        // For Sudoku, candidates are always numbers 1-SIZE
        List<Integer> candidates = new ArrayList<>();
        for (int num = 1; num <= SIZE; num++) {
            candidates.add(num);
        }
        return candidates;
    }

    @Override
    public boolean isValid(int[][] state, Position position, Integer value) {
        int row = position.getRow();
        int col = position.getCol();

        return !usedInRow(state, row, value)
                && !usedInCol(state, col, value)
                && !usedInBox(state, row - row % BOX_SIZE, col - col % BOX_SIZE, value);
    }

    @Override
    public void place(int[][] state, Position position, Integer value) {
        state[position.getRow()][position.getCol()] = value;
    }

    @Override
    public void unplace(int[][] state, Position position) {
        state[position.getRow()][position.getCol()] = EMPTY;
    }

    @Override
    public void recordPlace(Position position, Integer value) {
        if (steps != null) {
            steps.add(new SudokuStep(position.getRow(), position.getCol(), value, StepType.PLACE));
        }
    }

    @Override
    public void recordBacktrack(Position position) {
        if (steps != null) {
            steps.add(new SudokuStep(position.getRow(), position.getCol(), EMPTY, StepType.BACKTRACK));
        }
    }

    // === Sudoku constraint checking helpers ===

    private boolean usedInRow(int[][] grid, int row, int num) {
        for (int c = 0; c < SIZE; c++) {
            if (grid[row][c] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInCol(int[][] grid, int col, int num) {
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInBox(int[][] grid, int boxRow, int boxCol, int num) {
        for (int r = 0; r < BOX_SIZE; r++) {
            for (int c = 0; c < BOX_SIZE; c++) {
                if (grid[boxRow + r][boxCol + c] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
