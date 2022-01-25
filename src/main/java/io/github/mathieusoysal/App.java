package io.github.mathieusoysal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.mathieusoysal.dao.DatedPuzzlesDao;
import io.github.mathieusoysal.model.DatedPuzzle;
import io.github.mathieusoysal.resources.ApplicationProperties;
import io.github.mathieusoysal.utils.PuzzleDator;

public class App {
    private static final Logger LOGGER = LogManager.getLogger("CodinGame-puzzles-stats-saver");

    public static void main(String[] args) throws InterruptedException {
        ArgumentManager.setArguments(args);
        do {
            synchronizePuzzlesToMongoDB();
            TimeUnit.HOURS.sleep(ArgumentManager.getPeriod());
        } while (ArgumentManager.getPeriod() < 1);
    }

    private static void synchronizePuzzlesToMongoDB() {
        try (MongoClient mongoClient = MongoClients.create(new ConnectionString(
                ApplicationProperties.MONGODB_URI))) {
            DatedPuzzlesDao puzzlesDao = new DatedPuzzlesDao(mongoClient, ApplicationProperties.MONGODB_DATABASE);
            List<Puzzle> puzzles = new CodinGame().getPuzzles();
            List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
            puzzlesDao.saveAll(datedPuzzles);
            LOGGER.info("Synchronized {} puzzles to MongoDB", datedPuzzles.size());
        }
    }
}