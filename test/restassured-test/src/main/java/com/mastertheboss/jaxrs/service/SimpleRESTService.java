package com.mastertheboss.jaxrs.service;


import com.mastertheboss.jaxrs.model.Data;
import jakarta.ws.rs.*;


@Path("/")
public class SimpleRESTService {


	@Path("form")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String login(@FormParam("email") String email, @FormParam("password") String password) {
		return "Logged with "+email+"/"+password;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("json")
	public Data calculate(Data data) {
		data.setResult(data.getX()+data.getY());
		return data;
	}

	@GET
	@Path("path/{id}")
	public String loginPath(@PathParam("id") String id) {
		return "Id is " +id;
	}

	@GET
	@Path("query")
	public String loginQuery(@DefaultValue("11111")  @QueryParam("id") String id) {
		return "Id is " +id;
	}


	@GET
	@Path("header")
	public String checkBrowser(@HeaderParam("User-Agent") String whichBrowser) {
		System.out.println("Browser is "+whichBrowser);
         return "Browser is "+whichBrowser;
	}

	@GET
	@Path("session")
	public String checkSession(@CookieParam("JSESSIONID") String sessionid) {
		return "Session is "+sessionid;
	}


}
