package org.acme;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Customer customer = new Customer();
                customer.setSurname(document.getString("surname"));
                customer.setName(document.getString("name"));
                customer.setId(document.getLong("id"));
                list.add(customer);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(Customer customer){
        Document document = new Document()
                .append("name", customer.getName())
                .append("surname", customer.getSurname())
                .append("id", customer.getId());
        getCollection().insertOne(document);
    }

    public void update(Customer customer){
        // update one document

        Bson filter = eq("id", customer.getId());
        Bson updateOperation = set("name", customer.getName());
        getCollection().updateOne(filter, updateOperation);
    }

    public void delete(Customer customer){
        // delete one document

        Bson filter = eq("id", customer.getId());
        getCollection().deleteOne(filter);
    }
    private MongoCollection getCollection(){
        return mongoDB.getCollection("customers");
    }
}