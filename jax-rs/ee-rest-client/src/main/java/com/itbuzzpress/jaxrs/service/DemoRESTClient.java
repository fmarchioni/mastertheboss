package com.itbuzzpress.jaxrs.service;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class DemoRESTClient {

	String BASE_URL = "https://jsonplaceholder.typicode.com";
	private Client client = ClientBuilder.newClient();

	// curl http://localhost:8080/ee-rest-client/rest/posts
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Data> getdata() {

		List<Data> result = client.target(BASE_URL).path("/posts")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Data>>() {
				});

		client.close();
		return result;
	}
	// curl -X POST http://localhost:8080/ee-rest-client/rest/posts -H
	// 'Content-Type: application/json' -d
	// '{"userId":"1111","id":"1111","title":"mytitle","body":"hello"}'

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Data adddata(Data data) {

		Response response = client.target(BASE_URL).path("/posts")
				.request(MediaType.APPLICATION_JSON).post(Entity.json(data));
		Data dataReturn = response.readEntity(Data.class);

		client.close();
		return dataReturn;

	}
	@GET
	@Path("/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashMap> getMap(@QueryParam("id") int id) {

		List<HashMap> result = client.target(BASE_URL).path("/comments").queryParam("id", id).request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<HashMap>>() {
				});

		client.close();
		return result;
	}
}
