package com.mastertheboss.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.mastertheboss.quarkus.model.Ticket;

@ApplicationScoped
public class TicketService {
	@Transactional
    public void createTicket(Ticket ticket) {
        ticket.persist();
        System.out.println("Ticket created");
    }

}
