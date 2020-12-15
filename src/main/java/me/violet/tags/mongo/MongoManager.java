package me.violet.tags.mongo;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.Getter;
import org.bson.Document;

import javax.print.Doc;
@Data
public class MongoManager {

    public MongoClient mongoClient;
    public MongoDatabase mongoDatabase;
    public MongoCollection<Document> profiles;
    public MongoCollection<Document> tags;

    public MongoManager() {
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
        mongoDatabase = mongoClient.getDatabase("Tags");
        profiles = mongoDatabase.getCollection("profiles");
        tags = mongoDatabase.getCollection("tags");
    }

}
