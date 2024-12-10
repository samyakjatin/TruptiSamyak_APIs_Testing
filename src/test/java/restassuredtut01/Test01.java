 package restassuredtut01;
 

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
 public class Test01{
	
	 @Test
	 
	public void test_1() {
		 
		 RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
		 RequestSpecification request = RestAssured.given();
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("userId", "TQ123"); 
		requestParams.put("isbn", "9781449325862"); 
		
		// Add a header stating the Request body is a JSON 
		//request.headerrequest.body(("Content-Type", "application/json"); // Add the Json to the body of the request 
		//requestParams.toString()); // Post the request and check the response
		Response response= RestAssured.get("https://fake-json-api.mock.beeceptor.com/users");
	

		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode=response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
	}

}
