package com.mastertheboss.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/triangle")

public class TriangleController {

    @GET
    @Path("area")
    public Response calculateArea(@QueryParam("base") double base, @QueryParam("height") double height) {
        double area = 0.5 * base * height;
        return Response.ok(area).build();
    }

}