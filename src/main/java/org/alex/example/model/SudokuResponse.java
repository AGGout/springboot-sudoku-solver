package org.alex.example.model;

import java.util.Arrays;

public class SudokuResponse {

    private int[][] solution;
    private boolean solved;

    public SudokuResponse() {
    }

    public SudokuResponse(int[][] solution, boolean solved) {
        this.solution = solution;
        this.solved = solved;
    }

    public int[][] getSolution() {
        return solution;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public String toString() {
        return "SudokuResponse{" +
                "solution=" + Arrays.deepToString(solution) +
                ", solved=" + solved +
                '}';
    }
}

