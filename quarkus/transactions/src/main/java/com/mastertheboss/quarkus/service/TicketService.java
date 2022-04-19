package com.mastertheboss.quarkus.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import com.mastertheboss.quarkus.model.Ticket;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import io.quarkus.narayana.jta.RunOptions;
@RequestScoped
public class TicketService {

	@Inject
	EntityManager em;

	@Transactional
	@TransactionConfiguration(timeout = 1000)
	public void buyTicket(Ticket ticket) {
		em.persist(ticket);
		System.out.println("Ticket created");
	}


	public Ticket buyTicketProgrammaticTransaction(Ticket ticket) {
		QuarkusTransaction.begin();
		em.persist(ticket);

		if (!ticket.getName().equals("scam")) {
			QuarkusTransaction.commit();
			System.out.println("Commit!");
			return ticket;
		}
		else {
			QuarkusTransaction.rollback();
			System.out.println("Rollback!");
			return null;
		}
		 
	}
 
	public List<Ticket> listTickets() {
		return em.createNamedQuery("Ticket.findAll", Ticket.class)
				.getResultList();
	}
}
