package org.acme;
 
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MongoDBProducer {

    @Produces
    public MongoClient createMongo() {
        return  MongoClients.create("mongodb://root:example@172.24.0.3:27017/?maxPoolSize=20&w=majority");
    }

    @Produces
    public MongoDatabase createDB(MongoClient client) {
        return client.getDatabase("testdb");
    }

    public void close(@Disposes MongoClient toClose) {
        toClose.close();
    }
}