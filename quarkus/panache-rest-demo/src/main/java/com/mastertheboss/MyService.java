package com.mastertheboss;


import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface MyService  extends PanacheEntityResource<Ticket, Long> {
}