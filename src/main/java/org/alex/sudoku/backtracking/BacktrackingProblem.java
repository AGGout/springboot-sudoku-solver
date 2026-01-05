package org.alex.sudoku.backtracking;

/**
 * Generic interface for backtracking problems.
 *
 * @param <State> The state representation (e.g., int[][] for Sudoku, boolean[][] for N-Queens)
 * @param <Position> The position/cell type (e.g., coordinates)
 * @param <Value> The value type to place at positions (e.g., Integer for Sudoku)
 */
public interface BacktrackingProblem<State, Position, Value> {

    /**
     * Check if the current state represents a complete solution.
     *
     * @param state the current state
     * @return true if the problem is solved
     */
    boolean isComplete(State state);

    /**
     * Find the next position to fill.
     *
     * @param state the current state
     * @return the next position, or null if no empty positions remain
     */
    Position findNextPosition(State state);

    /**
     * Get all candidate values to try at the given position.
     *
     * @param state the current state
     * @param position the position to fill
     * @return iterable of candidate values
     */
    Iterable<Value> getCandidates(State state, Position position);

    /**
     * Check if placing a value at a position is valid (satisfies constraints).
     *
     * @param state the current state
     * @param position the position
     * @param value the value to place
     * @return true if the placement is valid
     */
    boolean isValid(State state, Position position, Value value);

    /**
     * Place a value at a position (mutate state).
     *
     * @param state the current state
     * @param position the position
     * @param value the value to place
     */
    void place(State state, Position position, Value value);

    /**
     * Remove a value from a position (mutate state during backtracking).
     *
     * @param state the current state
     * @param position the position
     */
    void unplace(State state, Position position);

    /**
     * Optional: record the placement step for visualization/debugging.
     *
     * @param position the position
     * @param value the value placed
     */
    default void recordPlace(Position position, Value value) {
        // no-op by default
    }

    /**
     * Optional: record the backtrack step for visualization/debugging.
     *
     * @param position the position being backtracked
     */
    default void recordBacktrack(Position position) {
        // no-op by default
    }
}
