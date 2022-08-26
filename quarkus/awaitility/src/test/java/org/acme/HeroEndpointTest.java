package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
 

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
 
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

 
import static java.util.concurrent.TimeUnit.*;
import static org.awaitility.Awaitility.*;
 
import static io.restassured.RestAssured.get;

 

@QuarkusTest
@TestHTTPEndpoint(HeroResource.class)

public class HeroEndpointTest {

	private Callable<Boolean> herosAvailable() {
		int size = given().when().get().then().extract().jsonPath().getList("$").size();

		return () ->  size == 3;
	}



	@Test
	public void testCustomerService() {

		await().atMost(3, SECONDS).until(herosAvailable());

		await().atMost(5, SECONDS)
		.untilAsserted(() -> Assertions.assertEquals(3, get().as(Hero[].class).length));

		await().atMost(3, TimeUnit.SECONDS).untilAsserted(() -> {
			String content =null;
			try {
				Path filePath = Path.of("/home/jboss/wildfly-26.1.0.Final/standalone/log/server.log");
				content = Files.readString(filePath);


			} catch (IOException exc) { exc.printStackTrace(); }

			Assertions.assertTrue(content.indexOf("Admin console listening") > -1);


		});
		await().atLeast(50, TimeUnit.MILLISECONDS).and().atMost(3, SECONDS).until(herosAvailable());

		with().pollInterval(250, TimeUnit.MILLISECONDS).and().with().pollDelay(50, MILLISECONDS).await().until(herosAvailable());

		await().during(800, MILLISECONDS).atMost(1500, MILLISECONDS).untilAsserted(() -> Assertions.assertEquals(3, get().as(Hero[].class).length));





	}


}
