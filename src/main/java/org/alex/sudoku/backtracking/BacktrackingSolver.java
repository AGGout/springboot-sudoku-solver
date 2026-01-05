package org.alex.sudoku.backtracking;

/**
 * Generic backtracking solver that can solve any problem implementing BacktrackingProblem.
 * This implements the classic backtracking algorithm pattern:
 * 1. If complete, return success
 * 2. Find next position to fill
 * 3. Try all candidate values
 * 4. For each valid candidate, place it and recurse
 * 5. If recursion succeeds, return success
 * 6. Otherwise, backtrack and try next candidate
 * 7. If no candidates work, return failure
 *
 * @param <State> The state representation
 * @param <Position> The position/cell type
 * @param <Value> The value type
 */
public class BacktrackingSolver<State, Position, Value> {

    /**
     * Solve the backtracking problem.
     *
     * @param state the initial state (will be mutated)
     * @param problem the problem definition
     * @return true if a solution was found, false otherwise
     */
    public boolean solve(State state, BacktrackingProblem<State, Position, Value> problem) {
        // Base case: check if we've found a complete solution
        if (problem.isComplete(state)) {
            return true;
        }

        // Find the next position to fill
        Position position = problem.findNextPosition(state);
        if (position == null) {
            // No more positions to fill
            return true;
        }

        // Try all candidate values for this position
        for (Value candidate : problem.getCandidates(state, position)) {
            // Check if this candidate is valid (satisfies constraints)
            if (problem.isValid(state, position, candidate)) {
                // Place the candidate
                problem.place(state, position, candidate);
                problem.recordPlace(position, candidate);

                // Recurse to solve the rest
                if (solve(state, problem)) {
                    return true; // Solution found!
                }

                // Backtrack: this candidate didn't lead to a solution
                problem.unplace(state, position);
                problem.recordBacktrack(position);
            }
        }

        // No candidate worked, need to backtrack further
        return false;
    }
}
