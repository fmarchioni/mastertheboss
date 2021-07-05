package com.mastertheboss;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body(
                        containsString("John"),
                        containsString("Fred"));

        given()
                .when()
                .body("{\"name\" : \"Frank\"},{\"surname\" : \"Frank\"}")
                .contentType("application/json")
                .post("/customers")
                .then()
                .statusCode(201);

        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body(
                        containsString("Frank"),
                        containsString("John"),
                        containsString("Fred"));
    }

}