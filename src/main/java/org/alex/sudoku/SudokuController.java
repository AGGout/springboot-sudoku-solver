package org.alex.sudoku;

import org.alex.sudoku.model.SudokuRequest;
import org.alex.sudoku.model.SudokuResponse;
import org.alex.sudoku.model.SudokuStep;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin(origins = "http://localhost:5173")
public class SudokuController {

    private final SudokuSolver solver;

    public SudokuController(SudokuSolver solver) {
        this.solver = solver;
    }

    @PostMapping(
            path = "/solve",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SudokuResponse solve(@RequestBody SudokuRequest request) {
        List<SudokuStep> steps = new ArrayList<>();
        int[][] solution = solver.solve(request.getGrid(), steps);
        boolean solved = (solution != null);

        // If unsolved, still return steps so you can see how it explored
        return new SudokuResponse(solution, solved, steps);
    }
}
