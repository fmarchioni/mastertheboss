package com.mastertheboss.quarkus.main;

import javax.inject.Inject;

import com.mastertheboss.quarkus.model.Ticket;
import com.mastertheboss.quarkus.service.TicketService;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class TicketMain implements QuarkusApplication {

	@Inject
	TicketService service;

	@Override
	public int run(String... args) {

		if(args.length<2) {
			System.out.println("Usage: mvn quarkus:dev  -Dquarkus.args=\"<name> <seat>\"");
			return 1;
		}

		Ticket ticket = new Ticket();
		ticket.name=args[0];
		ticket.seat=args[1];
		service.createTicket(ticket);
		return 0;
	}


}
