package com.mastertheboss.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mastertheboss.model.SimpleProperty;

 
@Stateless
public class RepositoryManager {
 
	@Inject
	private EntityManager em;

 
	public void put(SimpleProperty p){
		 
	      em.persist(p);
	     
	}
	 
	public void delete(SimpleProperty p){

		Query query = em.createQuery("delete FROM SimpleProperty p where p.key='"+p.getKey()+"'");

		query.executeUpdate();
		 
		 	      
	}	 
 
	// This will trigger the Query Cache
	public List<SimpleProperty>  queryCache(){
		
		Query query = em.createNamedQuery("findAll");
				 
		List <SimpleProperty> list =  query.getResultList();
		return list;		      
	}
	// This will trigger the Entity Cache
	public List<SimpleProperty>  queryByValue(String value){

		 SimpleProperty  prop = em.find(SimpleProperty.class,  value);
         List <SimpleProperty> list = new ArrayList();
         list.add(prop);
         return list;

		 	      
	}
}
