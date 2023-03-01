package org.acme.security.keycloak.authorization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class PolicyEnforcerTest {

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    String keycloakURL;

    @Test
    public void testHRResource() throws Exception {

        String hrToken = getToken("alice","password");

        RestAssured.baseURI = "http://localhost:8081";
        RestAssured.given().auth().oauth2(hrToken)
                .when().get("/api/hr")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDevResource() throws Exception {

        String hrToken = getToken("joe","password");

        RestAssured.baseURI = "http://localhost:8081";
        RestAssured.given().auth().oauth2(hrToken)
                .when().get("/api/dev")
                .then()
                .statusCode(200);
    }



    String getToken(String username, String password) throws Exception {
       String secret ="secret";
       RestAssured.baseURI = keycloakURL;
       Response response = given().urlEncodingEnabled(true)
              .auth().preemptive().basic("quarkus-client", secret)
              .param("grant_type", "password")
              .param("client_id", "demorealm-client")
              .param("username", username)
              .param("password", password)
              .header("Accept", ContentType.JSON.getAcceptHeader())
              .post("/protocol/openid-connect/token ")
              .then().statusCode(200).extract()
              .response();

       ObjectMapper objectMapper = new ObjectMapper();
       JsonNode rootNode = objectMapper.readTree(response.getBody().asString());
       return rootNode.get("access_token").asText();
    }
}
