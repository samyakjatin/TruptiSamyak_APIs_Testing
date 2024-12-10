package login;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login {

	@Test
	public void loginToSystem() {
		
		RestAssured.baseURI = "http://localhost:8080/api/v1"; 
		RequestSpecification request = RestAssured.given();
 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("username", "dhaval.sharma"); 
		requestParams.put("password", "samyak@2024"); 		
	 
		request.header("Content-Type", "application/json"); // Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		
		Response response = request.post("/auth/authenticate"); 
		System.out.println("The status received: " + response.statusLine());

		System.out.println("---------------Response---------------");
		System.out.println();
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		System.out.println(response.getBody().asString());

		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void loginToSystem1() {
		
		RestAssured.baseURI = "http://localhost:8080/api/v1"; 
		RequestSpecification request = RestAssured.given();
 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("username", "dhaval.sharma"); 
		requestParams.put("password", "samyak@2024"); 		
	 
		request.header("Content-Type", "application/json"); // Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		
		Response response = request.post("/auth/authenticate"); 
		System.out.println("The status received: " + response.statusLine());

		System.out.println("---------------Response---------------");
		System.out.println();
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		System.out.println(response.getBody().asString());
		

		Assert.assertEquals(statusCode, 200);
	}	
}
