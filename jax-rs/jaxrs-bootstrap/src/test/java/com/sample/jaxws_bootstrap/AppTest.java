package com.sample.jaxws_bootstrap;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

 
public class AppTest 
{
	 @Test
	 public void checkSiteIsUp() {
		    RestAssured.baseURI = "http://localhost:8080";
		    RequestSpecification httpRequest = RestAssured.given();
		    
		    Response response = httpRequest.get("/greet");
		    
		    String body = response.getBody().asString();
		    assertEquals(body,"Hello World!");
		        
	      
	 }
}
