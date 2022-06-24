package com.mastertheboss.graphql.client;

import java.util.List;

import org.eclipse.microprofile.graphql.Name;

import com.mastertheboss.graphql.model.Country;
 
import com.mastertheboss.graphql.model.Person;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
 

@GraphQLClientApi(endpoint = "http://localhost:8080/graphql")
public interface PersonClientApi {

	public List<Country> getAllCountries(); 

	 
	public List<Person> getAllPersons(); 

	public Country getCountry(@Name("countryId") int id);
	
	public Person getPerson(@Name("personId") int id);


}