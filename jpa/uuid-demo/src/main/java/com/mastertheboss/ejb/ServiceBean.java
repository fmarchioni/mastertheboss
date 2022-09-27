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
	}
 
	public List<Car> findAll(){

		Query query = em.createQuery("select c FROM Car c");
		List <Car> list = query.getResultList();
		return list;

	}
	
	
	public List<Car> findExtract(){

		Query query = em.createQuery("select id,model, price,  EXTRACT(YEAR FROM created) as y FROM Car c ");
		List <Car> list = query.getResultList();
		return list;

	}
	
	public List<Car> findLocalTime(){

		Query query = em.createQuery("select id,  LOCAL TIME as localTime, LOCAL DATETIME as localDateTime, LOCAL DATE as localDate FROM Car c ");
		List <Car> list = query.getResultList();
		return list;

	}
	public List<Car> testNumericFunctions(){

		Query query = em.createQuery("SELECT c.model,"
				+ "CEILING(c.price),"
				+ "FLOOR(c.price),"
				+ "ROUND(c.price, 1),"
				+ "EXP(c.price),"
				+ "LN(c.price),"
				+ "POWER(c.price,2),"
				+ "SIGN(c.price)"
				+ " FROM Car c");

		List <Car> list = query.getResultList();
		return list;

	}
	

	public Car findById(UUID id){

		Car p = em.find(Car.class, id);
		return p;

	}

}