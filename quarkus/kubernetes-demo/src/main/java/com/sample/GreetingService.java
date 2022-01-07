package com.sample;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;
@ApplicationScoped
public class GreetingService {
	
	private static final Logger LOGGER = Logger.getLogger(GreetingService.class.getName());
	
	@Inject
	EntityManager em;

	@Transactional
	public void saveGreeting() {
		Greeting g = new Greeting();
		g.setMessage("An user greeting was sent at" +new Date());
		em.persist(g);
		LOGGER.info("The greeting was saved on DB");
		
	}
}
