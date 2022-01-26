package io.github.mathieusoysal.utils;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

import io.github.mathieusoysal.model.DatedPuzzle;

public class PuzzleDator {

    private PuzzleDator() {
        throw new IllegalStateException("Utility class");
    }

    public static List<DatedPuzzle> datePuzzles(List<Puzzle> puzzles) {
        Date now = Date.from(Instant.now());
        return puzzles.parallelStream()
                .map(puzzle -> new DatedPuzzle(now, puzzle))
                .toList();
    }
}
