package restassuredtut01;

	import io.restassured.RestAssured;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

	public class Test06{

	    public static void main(String[] args) {
	        // Set the base URI
	        RestAssured.baseURI = "http://localhost:8080/api/v1";

	        // Send GET request and validate the response
	        Response response = 
	            given()
	                .log().all() // Log request details
	            .when()
	                .get("/lots") // Endpoint
	            .then()
	                .log().all() // Log response details
	                .statusCode(200) // Assert the status code
	                .body("userId", equalTo(1)) // Validate specific field
	                .body("id", equalTo(1)) 
	                .extract()
	                .response(); // Extract the full response

	        // Print the response body
	        System.out.println("Response Body: " + response.asString());
	    }
	}



