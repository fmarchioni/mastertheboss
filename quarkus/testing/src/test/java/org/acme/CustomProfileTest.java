package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
@TestProfile(CustomProfile.class)
public class CustomProfileTest {

    @Test
    public void testCustomProfile() {

        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hi there!"));


    }
}
