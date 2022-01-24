package io.github.mathieusoysal.model;

import java.util.Date;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

public record DatedPuzzle(Date date, Puzzle puzzle) {
}
