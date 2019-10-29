package com.mastertheboss.keycloak;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/secured")
public class SecuredResource {

    @GET
    public String hello(@Context SecurityContext sc) {
        return "Welcome " + sc.getUserPrincipal().getName() + "!";
    }

}
