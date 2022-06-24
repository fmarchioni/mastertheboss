package com.mastertheboss.graphql.client;
import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.graphql.Name;

import com.mastertheboss.graphql.model.Country;

import com.mastertheboss.graphql.model.Person;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
@Path("/client")

public class ClientResource {

    @Inject
    PersonClientApi typesafeClient;

    @GET
    @Path("/persons")
    @Blocking
    public List<Person> getAllPersons() {
        return typesafeClient.getAllPersons();
    }

    @GET
    @Path("/person/{id}")
    @Blocking
    public Person getPerson(int id) {
		return typesafeClient.getPerson(id);
	}

    @GET
    @Path("/country/{id}")
    @Blocking
    public Country getCountry(int id) {
		return typesafeClient.getCountry(id);
	}


    @GET
    @Path("/countries")
    @Blocking
    public List<Country> getAllCountries() {
        return typesafeClient.getAllCountries();
    }

    @Inject
    @GraphQLClient("query-dynamic")
    DynamicGraphQLClient dynamicClient;

    @GET
    @Path("/dynamic")
    @Blocking
    public List<Person> getAllPersonsUsingDynamicClient() throws Exception {
    	  Document document = document(
    	            operation(field("allPersons",
    	                field("name"))));
    	        Response response = dynamicClient.executeSync(document);  

    	        JsonArray personsArray = response.getData().getJsonArray("allPersons");

    	        List<Person> persons = response.getList(Person.class, "allPersons");
    	        return persons;


    }
}
