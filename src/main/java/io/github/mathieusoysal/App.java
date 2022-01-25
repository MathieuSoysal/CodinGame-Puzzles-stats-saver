package io.github.mathieusoysal;

import java.util.List;
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
    private static Logger logger = LogManager.getLogger("CodinGame-puzzles-stats-saver");

    public static void main(String[] args) {
        ArgumentManager.setArguments(args);
        ScheduledExecutorService executor = executeWithPeriod(
                App::synchronizePuzzlesToMongoDB,
                ArgumentManager.getPeriod());
        if (ArgumentManager.getPeriod() < 1)
            executor.shutdown();
    }

    private static ScheduledExecutorService executeWithPeriod(Runnable runnable, int period) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(runnable, Math.max(1, period), TimeUnit.HOURS);
        return executor;
    }

    private static void synchronizePuzzlesToMongoDB() {
        try (MongoClient mongoClient = MongoClients.create(new ConnectionString(
                ApplicationProperties.MONGODB_URI))) {
            DatedPuzzlesDao puzzlesDao = new DatedPuzzlesDao(mongoClient, ApplicationProperties.MONGODB_DATABASE);
            List<Puzzle> puzzles = new CodinGame().getPuzzles();
            List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
            puzzlesDao.saveAll(datedPuzzles);
            logger.info("Synchronized {} puzzles to MongoDB", datedPuzzles.size());
        }
    }
}
