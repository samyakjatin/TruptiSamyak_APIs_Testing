package restassuredtut01;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test36 {

    @Test
    public void loginToSystem() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add query parameters for GET request (username, password)
        request.queryParam("username", "dhaval.sharma");
        request.queryParam("password", "samyak@2024");
        
        // Add Authorization header for Bearer Token Authentication
        // Replace 'your_token_here' with the actual Bearer token you received
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhY3RpdmUiOnRydWUsInJvbGVzIjpbIkJpZGRlciJdLCJmaXJzdE5hbWUiOiJ3YWdoIiwidXNlcklkIjoiMTA1Yzg0NGItNWNlZi00NGIyLWFlMzEtOTQxYmZiOTQ3Mzc2IiwibGFzdE5hbWUiOiJEb2UiLCJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTczMjM1NjMxOSwiZXhwIjoxNzMyMzYyMzE5fQ.ErLWZDJ_lEBx7oIweQagqvQq0zMZnhUeJxAU9tzTNO8";  // Make sure to use the actual token
        request.header("Authorization", "Bearer " + token);  // Add Bearer token in Authorization header
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
       String userId="7828500F-5781-40D5-9E61-ADF2A09EB993";
        String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3"; 
       
         	
        // Add path parameters dynamically
        request.pathParam("userId", userId)
        .pathParam("auctionId", auctionId);
        
        // Send the GET request with query parameters
        Response response = request.get("/jewel/bids/{userId}/{auctionId}"); 
        
        if (response.getStatusCode() == 401) {
			System.out.println("Token expired. Please generate a new token.");
			Assert.fail("Request failed due to token expiration.");
		}
        
        // Print the response status and body for debugging
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response: " + response.getBody().asString());  // Added response logging
        System.out.println("---------------Response Details---------------");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        
        // Check if the status code is 200 (OK) or 403 (Forbidden)
        if (statusCode == 403) {
            System.out.println("Access denied: Invalid credentials or permissions.");
        } else if (statusCode == 200) {
            System.out.println("Request succeeded: Access granted.");
        }

        // Assert that the status code is 200 (OK), or change the expectation if 403 is valid
        Assert.assertEquals(statusCode, 200, "Expected 200 OK, but got: " + statusCode);
    }
}
