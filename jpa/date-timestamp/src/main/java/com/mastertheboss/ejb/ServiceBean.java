package com.mastertheboss.ejb;

import java.util.List;
import java.util.UUID;

import com.mastertheboss.model.Car;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;



@Stateless

public class  ServiceBean   {

	@PersistenceContext
	private EntityManager em;

	public void save(Car p){
		em.persist(p);
		System.out.println("reading from DB");
		System.out.println("================================");
		System.out.println(findAll().get(0));
	}
 
	public List<Car> findAll(){

		Query query = em.createQuery("select c FROM Car c");
		List <Car> list = query.getResultList();
		return list;

	}
 
	

	public Car findById(UUID id){

		Car p = em.find(Car.class, id);
		return p;

	}

}