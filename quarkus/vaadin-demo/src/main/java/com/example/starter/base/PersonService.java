package com.example.starter.base;

import java.util.List;


import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;


@Dependent
public class PersonService {
    @Inject
    EntityManager entityManager;
    
    public List<Person> getPersons(String country) {
       
        return (List<Person>) entityManager.createNamedQuery("Person.findAll")
        .setParameter("country", country)
        .getResultList();
 
    }

    
}
