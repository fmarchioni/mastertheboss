package com.mastertheboss.graphql.sample;
 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.mastertheboss.graphql.model.*;

@ApplicationScoped
public class PersonService {

	List<Person> persons = new ArrayList();
	List<Country> countries = new ArrayList();
 

	public PersonService() {
		Country c1 = new Country("United States","US");
		Country c2 = new Country("Italy","IT");
		
		Person p1 = new Person("Benjamin Franklin",c1);
		Person p2 = new Person("Leonardo da Vinci",c2);
		
		persons.add(p1);
		persons.add(p2);
		
		countries.add(c1);
		countries.add(c2);
		
	}

	public List<Country> getAllCountries() {
		return countries;
	}

	public Country getCountry(int id) {
		return countries.get(id);
	}

	public List<Person> getAllPersons() {
		return persons;
	}

	public Person getPerson(int id) {
		return persons.get(id);
	}

	public List<Person> getPersonByCity(Country country) {
		return persons.stream()
				.filter(person -> person.getCountry().equals(country))
				.collect(Collectors.toList());
	}

	public void addPerson(Person person) {
		persons.add(person);
		countries.add(person.getCountry());
	}

	public Person deletePerson(int id) {
		return persons.remove(id);
	}

	public List<Person> getPersonByName(String name) {
		return persons.stream()
				.filter(person -> person.getName().equals(name))
				.collect(Collectors.toList());
	}

}
