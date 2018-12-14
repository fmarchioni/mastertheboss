package com.mastertheboss.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import com.mastertheboss.model.SimpleProperty;



@Stateless

public class  ServiceBean   {

	@PersistenceContext
	private EntityManager em;

	public void put(SimpleProperty p){

		em.persist(p);
	}

	public void delete(SimpleProperty p){

		Query query = em.createQuery("delete FROM SimpleProperty p where p.key='"+p.getKey()+"'");

		query.executeUpdate();

	}

	public List<SimpleProperty> findAll(){

		Query query = em.createQuery("FROM SimpleProperty");

		List <SimpleProperty> list = query.getResultList();
		return list;

	}

	public SimpleProperty findById(String id){

		SimpleProperty p = em.find(SimpleProperty.class, id);
		return p;

	}

}