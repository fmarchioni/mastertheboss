package com.mastertheboss.graphql.sample;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

 
import com.mastertheboss.graphql.model.*;

@GraphQLApi
public class GraphQLService {

	@Inject
	PersonService personService;
/*
	{
	    allCountries {
	        name
	    }
	}
	*/
	@Query("allCountries")
	@Description("Get all countries.")
	public List<Country> getAllCountries() {
		return personService.getAllCountries();
	}
/*
	query getCountry {
	    country(countryId: 0) {
	        name
	    }
	}
*/
	@Query
	@Description("Get a Country.")
	public Country getCountry(@Name("countryId") int id) {
		return personService.getCountry(id);
	}

	/*
	{
	    allPersons {
	        name
	    }
	}
*/
	@Query("allPersons")
	@Description("Get all persons.")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
/*
	query getPerson {
	    person(personId: 0) {
	        name
	    }
	}
	*/
	@Query
	@Description("Get a Person")
	public Person getPerson(@Name("personId") int id) {
		return personService.getPerson(id);
	}

	/*
	  query getCountry {
    country(countryId: 0) {
        name
        symbol
        persons {
            name
         }
    }
}
	 */
	public List<Person> persons(@Source Country country) {
		return personService.getPersonByCity(country);
	}
	@Mutation
	public Person createPerson(@Name("person") Person person) {
		personService.addPerson(person);
		return person;
	}
	/*
	mutation {
	    createPerson(person: {name: "Isaac Newton", country: {name: "England", symbol: "GB"}}) {
	        name
	        country {
	          name
	          symbol
	        }
	    }
	}
	*/

}