package io.github.mathieusoysal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.util.MongoDBMockTest;

public class PuzzleDaoTest extends MongoDBMockTest {

    private PuzzlesDao puzzleDao;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        puzzleDao = new PuzzlesDao(mongoClient, "CodinGame-stats");
    }

    @Test
    void testSaveAll_shouldReturnTrue_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        assertTrue(puzzleDao.saveAll(puzzles));
    }

    @Test
    void testSaveAll_shouldAugmentCollectionSize_withTwoPuzzles() {
        long sizeBeforeInsert = mongoClient.getDatabase("CodinGame-stats")
                .getCollection("puzzles-today's")
                .countDocuments();
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        puzzleDao.saveAll(puzzles);
        long exceptedSize = mongoClient.getDatabase("CodinGame-stats")
                .getCollection("puzzles-today's")
                .countDocuments();
        assertEquals(exceptedSize, sizeBeforeInsert + puzzles.size());
    }

    @Test
    void testSaveAll_shouldAugmentCollectionSize_withAllPuzzles() {
        long sizeBeforeInsert = mongoClient.getDatabase("CodinGame-stats")
                .getCollection("puzzles-today's")
                .countDocuments();
        List<Puzzle> puzzles = new CodinGame().getPuzzles();
        puzzleDao.saveAll(puzzles);
        long exceptedSize = mongoClient.getDatabase("CodinGame-stats")
                .getCollection("puzzles-today's")
                .countDocuments();
        assertEquals(exceptedSize, sizeBeforeInsert + puzzles.size());
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
