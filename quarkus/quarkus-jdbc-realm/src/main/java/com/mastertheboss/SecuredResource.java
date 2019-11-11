package com.mastertheboss;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SecuredResource {

    @GET
    @Path("/securedAdmin")
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloAdmin() {
        return "Hello from secured resource!";

    }

    @GET
    @Path("/securedGuest")
    @RolesAllowed("guest")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloGuest() {
        return "Hello from secured resource!";

    }
}