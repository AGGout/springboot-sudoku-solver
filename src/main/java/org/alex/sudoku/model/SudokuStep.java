package org.alex.sudoku.model;

public class SudokuStep {
    private int row;
    private int col;
    private int value;     // for PLACE: 1..9, for BACKTRACK: 0
    private StepType type;

    public SudokuStep() {}

    public SudokuStep(int row, int col, int value, StepType type) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.type = type;
    }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public StepType getType() { return type; }
    public void setType(StepType type) { this.type = type; }
}
