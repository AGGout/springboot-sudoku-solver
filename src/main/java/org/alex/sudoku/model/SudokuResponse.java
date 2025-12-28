package org.alex.sudoku.model;

import java.util.List;

public class SudokuResponse {
    private int[][] solution;
    private boolean solved;
    private List<SudokuStep> steps;

    public SudokuResponse() {}

    public SudokuResponse(int[][] solution, boolean solved, List<SudokuStep> steps) {
        this.solution = solution;
        this.solved = solved;
        this.steps = steps;
    }

    public int[][] getSolution() { return solution; }
    public void setSolution(int[][] solution) { this.solution = solution; }

    public boolean isSolved() { return solved; }
    public void setSolved(boolean solved) { this.solved = solved; }

    public List<SudokuStep> getSteps() { return steps; }
    public void setSteps(List<SudokuStep> steps) { this.steps = steps; }
}
