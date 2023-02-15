package com.mastertheboss.jaxrs.service;



import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


@Path("/logger")
public class SimpleRESTService {


	@GET
	@Log
	public Response hello() {
		NewCookie cookie = new NewCookie("cookieName", "cookieValue");
		return Response.ok("Example response")
				.cookie(cookie)
				.build();
	}


}
