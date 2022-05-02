package com.sample;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * JVM mode tests.
 */
@QuarkusTest
public class RestJsonTest {

    @Test
    public void persons() {

        given()
                .body("{\"name\": \"John\", \"surname\": \"Smith\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("/persons")
                .then()
                .statusCode(200);

        given()
                .when().get("/persons")
                .then()
                .statusCode(200)
                .body(
                        "$.size()", is(1));
    }

}
