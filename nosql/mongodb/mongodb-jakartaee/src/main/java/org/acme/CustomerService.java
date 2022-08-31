package org.acme;


import com.mongodb.client.MongoCollection;
 
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;
import org.bson.conversions.Bson;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@ApplicationScoped
public class CustomerService {

    @Inject
    MongoDatabase mongoDB;

    public List<Customer> list(){
        List<Customer> list = new ArrayList<>();

        try {
        	Bson filter =  Filters.empty();
        	getCollection().find(filter).
        	    forEach(doc -> list.add(new Customer(doc.getLong("id"), doc.getString("name"),doc.getString("surname"))));

        }
        	catch (Exception exc ) {
        		exc.printStackTrace();
        	}
        return list;
    }

    public void add(Customer customer){
        Document document = new Document()
                .append("name", customer.getName())
                .append("surname", customer.getSurname())
                .append("id", customer.getId());
        InsertOneResult result = getCollection().insertOne(document);
        System.out.println("Inserted a document with the following id: " 
        	    + result.getInsertedId().asObjectId().getValue());
    }

    public void update(Customer customer){
        // update one document

        Bson filter = eq("id", customer.getId());
        Bson updateOperation = set("name", customer.getName());
        getCollection().updateOne(filter, updateOperation);
    }

    public void delete(Customer customer){
        Bson filter = eq("id", customer.getId());
        getCollection().deleteOne(filter);
    }
    private MongoCollection<Document> getCollection(){
        return mongoDB.getCollection("customers");
    }
}