package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginTest {
 
	
	@Test(description = "Login Test")
		 public void loginTest() {
		
		RestAssured.baseURI="http://64.227.160.186:8080";
		Response res = RestAssured.given()
				.header("Content-Type", "application/json")
				.body("{\"username\":\"uday1234\",\"password\":\"uday12345\"}")
				.when()
				.post("/api/auth/login");
		System.out.println("Response Body: " + res.asPrettyString());
		    
		 Assert.assertEquals(res.getStatusCode(), 200);

		 }
	
}
