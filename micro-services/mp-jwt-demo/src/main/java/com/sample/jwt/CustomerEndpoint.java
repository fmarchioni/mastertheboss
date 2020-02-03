package com.sample.jwt;


import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;


import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.*;

import java.util.Set;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@DenyAll
public class CustomerEndpoint {


    @Inject
    @Claim(standard = Claims.groups)
    private Set<String> groups;

    @Inject
    @Claim(standard = Claims.sub)
    private String subject;

    @GET
    @Path("goadmin")
    @RolesAllowed("admin")
    public String adminMethod() {
        return "You are logged with " +this.subject + ": " + this.groups.toString();

    }


    @GET
    @Path("gouser")
    @RolesAllowed("user")
    public String userMethod() {
        return "You are logged with " +this.subject + ": " + this.groups.toString();

    }



}
