package io.github.mathieusoysal.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.util.MongoDBMockTest;
import io.github.mathieusoysal.utils.PuzzleDator;

class DatedPuzzlesDaoTest extends MongoDBMockTest {
    private final String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}$";
    private DatedPuzzlesDao puzzleDao;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        puzzleDao = new DatedPuzzlesDao(mongoClient, "CodinGame-stats");
    }

    @AfterEach
    public void tearDown() throws Exception {
        mongoClient.getDatabase("CodinGame-stats")
                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .deleteMany(new Document());
        super.tearDown();
    }

    @Test
    void testSaveAll_shouldReturnTrue_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        var datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        assertTrue(puzzleDao.saveAll(datedPuzzles));
    }

    @Test
    void testSaveAll_shouldAugmentCollectionSize_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        var datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        puzzleDao.saveAll(datedPuzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    @RepeatedTest(10)
    void testSaveAll_shouldAugmentCollectionSize_withAllPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles();
        var datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        puzzleDao.saveAll(datedPuzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    @Test
    void testSaveAll_shouldAddGoodDate() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        var datedPuzzles = PuzzleDator.datePuzzles(puzzles);
        puzzleDao.saveAll(datedPuzzles);
        FindIterable<Document> document = mongoClient.getDatabase("CodinGame-stats")
                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .find().limit(1);
        String date = document.first().getString("date");
        assertTrue(date.matches(regex), date);
    }

    private long countDocuments() {
        return mongoClient.getDatabase("CodinGame-stats")
                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .countDocuments();
    }

}
