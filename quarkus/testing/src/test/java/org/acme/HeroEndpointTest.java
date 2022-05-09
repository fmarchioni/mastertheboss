package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(HeroResource.class)

public class HeroEndpointTest {

    @Test
    public void testCustomerService() {

        RestAssured.given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "[0].id", is(1),
                        "[0].name", is("Batman"),
                        "[1].id", is(2),
                        "[1].name", is("Superman"),
                        "[2].id", is(3),
                        "[2].name", is("Wonder woman")
                );


        given()
                .contentType("application/json")
                .body(new Hero("Iron man"))
                .when()
                .post()
                .then()
                .statusCode(201);


         RestAssured.given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", is(4),
                        "[3].name", is("Iron man")
                );


    }
}