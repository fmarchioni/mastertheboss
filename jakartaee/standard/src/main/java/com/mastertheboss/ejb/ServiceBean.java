package com.mastertheboss.ejb;

import com.mastertheboss.model.Person;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class  ServiceBean   {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void add(String name, String surname){
        Person p = new Person();
        p.setName(name);
        p.setSurname(surname);
		em.persist(p);
	}


	public List<Person> findAll(){

		Query query = em.createQuery("SELECT p FROM Person p");

		List <Person> list = query.getResultList();
		return list;

	}

	public Person findById(String id){

		Person p = em.find(Person.class, id);
		return p;

	}

}