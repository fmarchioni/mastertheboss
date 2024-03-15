package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CustomerResourceTest {
    @Test
    void testCustomerEndpoint() {
        given()
                .when().get("/customer")
                .then()
                .statusCode(200)
                .body(
                        containsString("Batman"),
                        containsString("Superman"),
                        containsString("Wonder woman"));

    }

}