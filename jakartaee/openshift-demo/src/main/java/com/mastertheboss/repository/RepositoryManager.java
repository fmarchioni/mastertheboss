package com.mastertheboss.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.mastertheboss.model.SimpleProperty;

 

public class RepositoryManager {
 
	@Inject
	private EntityManager em;

 
	 
 
	public List<SimpleProperty>  queryCache(){
		Query query = em.createQuery("FROM SimpleProperty");

		List <SimpleProperty> list =  query.getResultList();
		return list;	      
	}
}
