package org.alex.sudoku.model;

import java.util.Arrays;

public class SudokuRequest {

    private int[][] grid;

    public SudokuRequest() {
    }

    public SudokuRequest(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "SudokuRequest{" +
                "grid=" + Arrays.deepToString(grid) +
                '}';
    }
}

