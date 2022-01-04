package io.github.mathieusoysal.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

public abstract class AbstractMFlixDao {

    protected final String CODINGAME_DATABASE;
    protected MongoDatabase db;
    protected MongoClient mongoClient;

    protected AbstractMFlixDao(MongoClient mongoClient, String databaseName) {
        this.mongoClient = mongoClient;
        CODINGAME_DATABASE = databaseName;
        this.db = this.mongoClient.getDatabase(CODINGAME_DATABASE);
    }

    public ObjectId generateObjectId() {
        return new ObjectId();
    }
}
