package com.mastertheboss.batch.batchlet;

import com.mastertheboss.batch.model.BatchUser;
import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.StepContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Named
@Stateless
public class DatabaseBatchlet extends AbstractBatchlet {

	@Inject StepContext stepContext;

	@PersistenceContext
	EntityManager em;
	@Override
	public String process() {
		String user = stepContext.getProperties().getProperty("user");
		String password = stepContext.getProperties().getProperty("password");

		BatchUser u = new BatchUser();
		u.setUsername(user);
		u.setPassword(password);

		em.persist(u);
		System.out.println("User created!");
		return "COMPLETED";
	}

}
