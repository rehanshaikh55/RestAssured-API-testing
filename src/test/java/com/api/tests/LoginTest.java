package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginTest {

    public static String accessToken; // shared between tests
    public static String customerId; // shared between tests
    @Test(description = "Login Test")
    public void loginTest() {
        RestAssured.baseURI = "https://vegis-backend.onrender.com";
        
        Response res = RestAssured.given()
            .header("Content-Type", "application/json")
            .body("{\"phone\":\"9173488443\"}")
            .when()
            .post("/api/customer/login");

        System.out.println("Response Body: " + res.asPrettyString());

        // Store in class-level variable
        accessToken = res.jsonPath().getString("accessToken");
        System.out.println("Access Token: " + accessToken);
          
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(description = "Fetch User", dependsOnMethods = "loginTest")
    public void fetchUser() {
        RestAssured.baseURI = "https://vegis-backend.onrender.com";
        
        Response ress = RestAssured.given()
            .header("Authorization", "Bearer " + accessToken) // add "Bearer " prefix
            .when()
            .get("/api/user");

        System.out.println("Response Body: " + ress.asPrettyString());

        String fetchedPhone = ress.jsonPath().getString("user.phone");
        System.out.println("Fetched Phone: " + fetchedPhone);
         customerId = ress.jsonPath().getString("user._id");
        // Assertions
        Assert.assertEquals(ress.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(fetchedPhone, "9173488443", "Phone number mismatch");
    }
    @Test(description = "get all order of login user", dependsOnMethods = "fetchUser")
    public void getAllOrder() {
    			RestAssured.baseURI = "https://vegis-backend.onrender.com";
		
		Response ress = RestAssured.given()
			.header("Authorization", "Bearer " + accessToken) // add "Bearer " prefix
			.when()
			.get("/api/order?customerId="+ customerId );

		System.out.println("Response Body: " + ress.asPrettyString());

		// Assertions
		Assert.assertEquals(ress.getStatusCode(), 200, "Expected status code 200");
		
    }
         
}











