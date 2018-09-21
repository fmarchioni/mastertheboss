package com.mastertheboss.opentracing;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("/restendpoint")
public class ExampleEndpoint {
    @POST
    @Path("/post")
    public String execute() {
        return "Action executed!";
    }
}
