package com.mastertheboss;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {
}