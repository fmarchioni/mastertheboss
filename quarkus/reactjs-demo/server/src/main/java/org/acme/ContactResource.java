package org.acme;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contacts")
public class ContactResource {
   @Inject
   ContactService service;

    @GET
    public List<Contact> findAll() {
        return service.getPersons();
    }


    @POST
    public Response add(Contact contact) {
        service.addPerson(contact);
        return Response.status(201).build();
    }
}