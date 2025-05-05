package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class categories {
     public static String categoriesId; // shared between tests
		@Test(description = "get all categories")
	public void getAllCategories() {
		RestAssured.baseURI = "https://vegis-backend.onrender.com";
		
		Response ress = RestAssured.given()
			.header("Authorization", "Bearer " + LoginTest.accessToken) // add "Bearer " prefix
			.when()
			.get("/api/categories");
 
		System.out.println("Response Body: " + ress.asPrettyString());
		categoriesId = ress.jsonPath().getString("[0]._id");
		// Assertions
		Assert.assertEquals(ress.getStatusCode(), 200, "Expected status code 200");
	}
		@Test(description = "get all categories by id", dependsOnMethods = "getAllCategories")
		public void getProductBycategoriesId() {
			RestAssured.baseURI = "https://vegis-backend.onrender.com";
			
			Response ress = RestAssured.given()
				.header("Authorization", "Bearer " + LoginTest.accessToken) // add "Bearer " prefix
				.when()
				.get("/api/products/"+ categoriesId);
	 
			System.out.println("Response Body: " + ress.asPrettyString());
			// Assertions
			
			Assert.assertEquals(ress.getStatusCode(), 200, "Expected status code 200");
		}
	
}
