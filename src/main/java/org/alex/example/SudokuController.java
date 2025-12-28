package org.alex.example;

import org.alex.example.model.SudokuRequest;
import org.alex.example.model.SudokuResponse;
import org.alex.example.SudokuSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin(origins = "http://localhost:5173") // Vue dev server
public class SudokuController {

    private static final Logger log = LoggerFactory.getLogger(SudokuController.class);

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
        log.info("Received Sudoku grid: {}", request);

        int[][] grid = request.getGrid();
        int[][] solution = solver.solveCopy(grid);

        boolean solved = solution != null;
        if (!solved) {
            log.warn("Sudoku could not be solved.");
        }

        return new SudokuResponse(solution, solved);
    }
}

