package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@QuarkusTest
@QuarkusTestResource(ConsulTestResource.class)
public class FrontendApiTest {

    @Test
    public void test() {
     
    	String str1 = RestAssured.get("/api").then().statusCode(200).extract().body().asString();  	
    	String str2 = RestAssured.get("/api").then().statusCode(200).extract().body().asString();
    	
    	Assertions.assertTrue((str1.indexOf("Hello Service 1") > -1) || (str2.indexOf("Hello Service 1") > -1)); 
    	Assertions.assertTrue((str1.indexOf("Hello Service 2") > -1) || (str2.indexOf("Hello Service 2") > -1)); 

    }

   
}
