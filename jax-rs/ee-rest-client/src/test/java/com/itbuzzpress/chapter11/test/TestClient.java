package com.itbuzzpress.chapter11.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

//import org.junit.jupiter.api.Test;

import com.itbuzzpress.jaxrs.service.Data;
import com.itbuzzpress.jaxrs.service.DataRequestFilter;

public class TestClient {
	String BASE_URL = "https://jsonplaceholder.typicode.com";
	private Client client = ClientBuilder.newClient();

	@Test
	public void testSimple() {
		List<Data> result = client.target(BASE_URL).path("/posts").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Data>>() {
				});

		assertTrue(result.size() > 0);
		client.close();

	}

	@Test
	public void testPathParam() {
		Data result = client.target(BASE_URL).path("/posts/{id}")
				.resolveTemplate("id", "1")
				.request(MediaType.APPLICATION_JSON)				
				.get(Data.class);

		assertNotNull(result);
		client.close();

	}
	
	@Test
	public void testQueryParam() {
		List<HashMap> result = client.target(BASE_URL).path("/comments").queryParam("id", "1").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<HashMap>>() {
				});

		assertTrue(result.size() > 0);
		client.close();

	}
	
	@Test
	public void testPost() {
		Data data = new Data(1, 2, "title", "body");

		Response response = client.target(BASE_URL).path("/posts").request(MediaType.APPLICATION_JSON)
				.post(Entity.json(data));
		Data dataReturn = response.readEntity(Data.class);
		assertNotNull(dataReturn);

	}
	@Test
	public void testPostWithFilter() {
		Client client = ClientBuilder.newClient();
		Data data = new Data(1, 2, "title", null);

		Data dataReturn =
	                client.register(DataRequestFilter.class).target(BASE_URL).path("/posts").request()
	                        .post(Entity.entity(data, MediaType.APPLICATION_JSON), Data.class);
		   
	 
		assertNotNull(dataReturn);

	}
	@Test
	public void testCallBack() {
		 InvocationCallback<List<Data>> invocationCallback = new InvocationCallback<List<Data>>() {

	            @Override
	            public void completed(List<Data> list) {
	                // Completed the invocation with no contact
	            	assertTrue(list.size() > 0);
	            }

	            @Override
	            public void failed(Throwable throwable) {
	                // It should fail
	                Assert.fail(throwable.getMessage());

	            }
	        };
	        
	    	client.target(BASE_URL).path("/posts").request(MediaType.APPLICATION_JSON)
	    			.async().get(invocationCallback);

 
		client.close();

	}
}