package com.mastertheboss.jaxrs.test;


import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest  {

	@BeforeAll
	public static void start() throws Exception {

		RestAssured.baseURI = "http://localhost:8080/rest-parameters/rest/";
	}


	@Test
	public void testWithQueryParam() {

		RestAssured.given()
				.param("id", "2")
				.when().get("/query")
				.then()
				.statusCode(200)
				.body(equalTo("Id is 2"));


	}

	@Test
	public void testWithPathParam() {

		RestAssured.given()
				.pathParam("id", "2")
				.when().get("/path/{id}")
				.then()
				.statusCode(200)
				.body(equalTo("Id is 2"));


	}
	@Test
	public void testWithFormParam() {
        String email =  "user@site.com";
		String password ="Pas54321";
		RestAssured.given().urlEncodingEnabled(true)
				.param("email", email)
				.param("password", password)
				.when().post("/form")
				.then()
				.statusCode(200)
				.body(equalTo("Logged with "+email+"/"+password));


	}

	@Test
	public void testWithJSONPayload() {
	RestAssured.defaultParser = Parser.JSON;

	String data = "{\"x\": \"5\",\"y\": \"10\"}";
 
		RestAssured.given().urlEncodingEnabled(true)
				.contentType("application/json")
				.body(data)
				.when()
				.post("/json")
				.then()
				.statusCode(200)
				.body("result", Matchers.equalTo(15));

	}
	@Test
	public void testWithHeaderParam() {

		RestAssured.given().urlEncodingEnabled(true)
				.headers(
						"User-Agent", "MyJavaApplication/1.0"
				)
				.when().get("/header")
				.then()
				.statusCode(200)
				.body(equalTo("Browser is MyJavaApplication/1.0"));


	}

	@Test
	public void testWithCookie() {
        String id =  UUID.randomUUID().toString();
		RestAssured.given().urlEncodingEnabled(true)

                .cookie("JSESSIONID",  id)
				.when().get("/session")
				.then()
				.statusCode(200)
				.body(equalTo("Session is "+id));


	}

}


