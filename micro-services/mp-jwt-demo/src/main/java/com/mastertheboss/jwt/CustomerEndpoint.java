package com.mastertheboss.jwt;


import org.eclipse.microprofile.jwt.Claim;


import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.*;
import org.eclipse.microprofile.jwt.ClaimValue;

import java.util.Set;


@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class CustomerEndpoint {


    @Inject
    @Claim("groups")
    private Set<String> groups;

    @Inject
    @Claim("sub")
    private String subject;
    /*
    @GET
    @Path("user")
    @RolesAllowed({"user"})
    public String echoUser() {

            return "You are logger with Role User";
    }
*/
    @GET
    @Path("user")
    @RolesAllowed({"admin"})
    public String echoRoleAndSubject() {
        return "You are logged with " +this.subject + ": " + this.groups.toString();

    }



}
