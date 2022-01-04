package io.github.mathieusoysal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

import org.bson.Document;

public class PuzzlesDao extends AbstractMFlixDao {

    public static final String PUZZLES_TODAYS_COLLECTION = "puzzles-today's";

    private MongoCollection<Document> collection;

    public PuzzlesDao(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        collection = db.getCollection(PUZZLES_TODAYS_COLLECTION);
    }

    public BooleanSupplier saveAll(List<Puzzle> puzzles) {
        return null;
    }



}
