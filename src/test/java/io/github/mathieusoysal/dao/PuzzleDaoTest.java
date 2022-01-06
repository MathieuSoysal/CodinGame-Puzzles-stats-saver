package io.github.mathieusoysal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.github.mathieusoysal.util.MongoDBMockTest;

@TestMethodOrder(OrderAnnotation.class)
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
                .getCollection("puzzles-today's").deleteMany(new Document());
        super.tearDown();
    }

    @Test
    @Order(1)
    void testSaveAll_shouldReturnTrue_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        assertTrue(puzzleDao.saveAll(puzzles));
    }

    @Test
    @Order(2)
    void testSaveAll_shouldAugmentCollectionSize_withTwoPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles().subList(0, 2);
        puzzleDao.saveAll(puzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    @Test
    @Order(3)
    void testSaveAll_shouldAugmentCollectionSize_withAllPuzzles() {
        List<Puzzle> puzzles = new CodinGame().getPuzzles();
        puzzleDao.saveAll(puzzles);
        assertEquals(puzzles.size(), countDocuments());
    }

    private long countDocuments() {
        return mongoClient.getDatabase("CodinGame-stats")
                .getCollection("puzzles-today's")
                .countDocuments();
    }

}
