package com.mastertheboss.jaxrs.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import net.datafaker.providers.base.BaseFaker;
import net.datafaker.transformations.JsonTransformer;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mastertheboss.model.Customer;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import net.datafaker.service.RandomService;
import net.datafaker.transformations.JavaObjectTransformer;
import net.datafaker.transformations.JsonTransformer;
import net.datafaker.transformations.Schema;
import static net.datafaker.transformations.Field.field;

public class RestAssuredTest {

	@BeforeAll
	public static void start() throws Exception {

		RestAssured.baseURI = "http://localhost:8080/datafaker/rest";
	}

	 
	  
	@Test
	public void testWithQueryParam() throws Exception {
		Customer client = BaseFaker.populate(Customer.class);
		System.out.println("Client---------------->" + client);

		// Convert the Java object to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(client);

		// Configure RestAssured

		// Create and execute the POST request
		Response response = RestAssured
				.given()
				.contentType("application/json")
				.body(json)
				.post("/customers");

		// Verify the response status code
		response.then().statusCode(201);

		Response response2 = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/customers")
				.then()
				.extract().response();

		Assertions.assertEquals(200, response2.statusCode());
		System.out.println(response2.jsonPath().prettify());

		RestAssured.given()

				.when().get("/customers")
				.then()
				.statusCode(200);

	}

	/*
	 * 
	 * @Test
	 * public void testWithJSONPayload() {
	 * RestAssured.defaultParser = Parser.JSON;
	 * 
	 * String data = "{\"x\": \"5\",\"y\": \"10\"}";
	 * 
	 * RestAssured.given().urlEncodingEnabled(true)
	 * .contentType("application/json")
	 * .body(data)
	 * .when()
	 * .post("/json")
	 * .then()
	 * .statusCode(200)
	 * .body("result", Matchers.equalTo(15));
	 * 
	 * }
	 * 
	 * @Test
	 * public void testWithHeaderParam() {
	 * 
	 * RestAssured.given().urlEncodingEnabled(true)
	 * .headers(
	 * "User-Agent", "MyJavaApplication/1.0"
	 * )
	 * .when().get("/header")
	 * .then()
	 * .statusCode(200)
	 * .body(equalTo("Browser is MyJavaApplication/1.0"));
	 * 
	 * 
	 * }
	 * 
	 * @Test
	 * public void testWithCookie() {
	 * String id = UUID.randomUUID().toString();
	 * RestAssured.given().urlEncodingEnabled(true)
	 * 
	 * .cookie("JSESSIONID", id)
	 * .when().get("/session")
	 * .then()
	 * .statusCode(200)
	 * .body(equalTo("Session is "+id));
	 * 
	 * 
	 * }
	 */
}
