package io.github.mathieusoysal.dao;

import java.util.ArrayList;
import java.util.List;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;

import org.bson.Document;

public class PuzzlesDao extends AbstractCGSaverDao {

    public static final String PUZZLES_HISTORY_COLLECTION = "puzzles-history";

    private MongoCollection<Document> collection;

    public PuzzlesDao(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        collection = db.getCollection(PUZZLES_HISTORY_COLLECTION);
    }

    public boolean saveAll(List<Puzzle> puzzles) {
        List<WriteModel<Document>> bulkWrites = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        puzzles.parallelStream()
                .forEach(puzzle -> bulkWrites.add(new InsertOneModel<>(Document.parse(gson.toJson(puzzle)))));
        BulkWriteOptions bulkWriteOptions = new BulkWriteOptions().ordered(false);
        BulkWriteResult bulkWriteResult = collection.bulkWrite(bulkWrites, bulkWriteOptions);
        return bulkWriteResult.wasAcknowledged();
    }

}
