package io.github.mathieusoysal.dao;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;

import org.bson.Document;

import io.github.mathieusoysal.model.DatedPuzzle;

public class DatedPuzzlesDao extends AbstractCGSaverDao {

    private static final Gson DATED_PUZZLES_GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSXXX")
            .create();
    public static final String PUZZLES_HISTORY_COLLECTION = "puzzles-history";

    private MongoCollection<Document> collection;

    public DatedPuzzlesDao(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        collection = db.getCollection(PUZZLES_HISTORY_COLLECTION);
    }

    public boolean saveAll(List<DatedPuzzle> puzzles) {
        List<InsertOneModel<Document>> bulkWrites = puzzles.parallelStream()
                .map(puzzle -> new InsertOneModel<>(Document.parse(DATED_PUZZLES_GSON.toJson(puzzle))))
                .toList();
        BulkWriteOptions bulkWriteOptions = new BulkWriteOptions().ordered(false);
        BulkWriteResult bulkWriteResult = collection.bulkWrite(bulkWrites, bulkWriteOptions);
        return bulkWriteResult.wasAcknowledged();
    }

}
