package io.github.mathieusoysal;

import java.util.List;

import com.github.mathieusoysal.codingame_stats.CodinGame;
import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import io.github.mathieusoysal.dao.PuzzlesDao;
import io.github.mathieusoysal.resources.ApplicationProperties;

public class App {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(new ConnectionString(
                ApplicationProperties.MONGODB_URI))) {
            PuzzlesDao puzzlesDao = new PuzzlesDao(mongoClient, ApplicationProperties.MONGODB_DATABASE);
            List<Puzzle> puzzles = new CodinGame().getPuzzles();
            puzzlesDao.saveAll(puzzles);
        }
    }

}
