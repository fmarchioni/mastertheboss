package com.mastertheboss.quarkus.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.mastertheboss.quarkus.model.Ticket;
import com.mastertheboss.quarkus.service.TicketService;

@Path("/tickets")
public class TicketResource {
	@Inject
	TicketService service;
	
	@POST
	@Path("/buy1")
	public Response buyTicket1(Ticket ticket) {
		service.buyTicket(ticket);
		return Response.ok(ticket).status(201).build();
	}

	@POST
	@Path("/buy2")
	public Response buyTicket2(Ticket ticket) {
		ticket = service.buyTicketProgrammaticTransaction(ticket);
		System.out.println(ticket);
		if (ticket == null) {
			 throw new WebApplicationException("Ticket invalid", 500);	
		}
		 return Response.ok(ticket).status(201).build();
	}

	@GET
	public List<Ticket> ListTickets() {
		return service.listTickets();
	}
}
