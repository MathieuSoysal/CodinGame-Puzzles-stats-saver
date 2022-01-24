package io.github.mathieusoysal.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import io.github.mathieusoysal.model.DatedPuzzle;

public class PuzzleDatorTest {

    private static List<Puzzle> puzzles;
    private static MockedStatic<Instant> mockedStatic;

    @BeforeAll
    public static void setUp() {
        puzzles = new CodinGame().getPuzzles();
        String instantExpected = "2014-12-22T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        Instant instant = Instant.now(clock);
        mockedStatic = mockStatic(Instant.class);
        mockedStatic.when(Instant::now).thenReturn(instant);
    }

    @AfterAll
    public static void tearDown() {
        mockedStatic.close();
    }

    @Test
    void test_instantNow_shouldReturnCorrectInstant() {
        Instant now = Instant.now();
        assertEquals(1419243330, now.getEpochSecond());
    }

    @Test
    void testDatePuzzle_shouldReturnDatedPuzzlesWithGoodSize_withTwoPuzzles() {
        List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        assertEquals(puzzles.size(), datedPuzzles.size());
    }

    @Test
    void testDatePuzzle_shouldReturnDatedPuzzlesWithGoodSize_withAllPuzzles() {
        List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        assertEquals(puzzles.size(), datedPuzzles.size());
    }

    @Test
    void testDatePuzzle_shouldReturnDatedPuzzlesWithGoodDate_withAllPuzzles() {
        List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        for (DatedPuzzle datedPuzzle : datedPuzzles) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datedPuzzle.date());
            assertEquals(2014, calendar.get(Calendar.YEAR));
            assertEquals(Calendar.DECEMBER, calendar.get(Calendar.MONTH));
            assertEquals(22, calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
}
