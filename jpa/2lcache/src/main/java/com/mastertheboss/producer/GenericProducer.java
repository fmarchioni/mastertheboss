package com.mastertheboss.producer;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class GenericProducer {
	@SuppressWarnings("unused")
	@Produces
	@PersistenceContext
	private EntityManager em;
}
