package io.github.mathieusoysal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.util.MongoDBMockTest;

public class PuzzleDaoTest extends MongoDBMockTest {

    private PuzzlesDao puzzleDao;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        puzzleDao = new PuzzlesDao(mongoClient, "CodinGame-stats");
    }

    @AfterEach
    public void tearDown() throws Exception {
        mongoClient.getDatabase("CodinGame-stats")
                .getCollection(PuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .deleteMany(new Document());
        super.tearDown();
    }

    @Test
    void testSaveAll_shouldReturnTrue_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        assertTrue(puzzleDao.saveAll(puzzles));
    }

    @Test
    void testSaveAll_shouldAugmentCollectionSize_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        puzzleDao.saveAll(puzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    @RepeatedTest(4)
    void testSaveAll_shouldAugmentCollectionSize_withAllPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles();
        puzzleDao.saveAll(puzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    private long countDocuments() {
        return mongoClient.getDatabase("CodinGame-stats")
                .getCollection(PuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .countDocuments();
    }

}
