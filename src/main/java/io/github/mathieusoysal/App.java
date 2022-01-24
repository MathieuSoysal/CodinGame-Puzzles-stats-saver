package io.github.mathieusoysal;

import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import io.github.mathieusoysal.dao.DatedPuzzlesDao;
import io.github.mathieusoysal.model.DatedPuzzle;
import io.github.mathieusoysal.resources.ApplicationProperties;
import io.github.mathieusoysal.utils.PuzzleDator;

public class App {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(new ConnectionString(
                ApplicationProperties.MONGODB_URI))) {
            DatedPuzzlesDao puzzlesDao = new DatedPuzzlesDao(mongoClient, ApplicationProperties.MONGODB_DATABASE);
            List<Puzzle> puzzles = new CodinGame().getPuzzles();
            List<DatedPuzzle> datedPuzzles = PuzzleDator.datePuzzles(puzzles);
            puzzlesDao.saveAll(datedPuzzles);
        }
    }

}
