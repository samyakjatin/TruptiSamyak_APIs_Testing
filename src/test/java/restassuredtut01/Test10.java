package restassuredtut01;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test10 {

    @Test
    public void  loginToSystem() {
        
        // Set the base URI
        RestAssured.baseURI = "http://localhost:8080/api/v1"; 
        RequestSpecification request = RestAssured.given();
        
        // Add Authorization header for Bearer Token Authentication
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJsYXN0TmFtZSI6IkRvZSIsInVzZXJJZCI6IjEwNWM4NDRiLTVjZWYtNDRiMi1hZTMxLTk0MWJmYjk0NzM3NiIsImZpcnN0TmFtZSI6IndhZ2giLCJyb2xlcyI6WyJCaWRkZXIiXSwiYWN0aXZlIjp0cnVlLCJzdWIiOiJwcmFzaGFudCIsImlhdCI6MTczMjI1NjU3OSwiZXhwIjoxNzMyMjYyNTc5fQ.qQ8QUfA14moHuEpLtGQweWnOtHsU-JOKmOkvCmRoCHM";
        request.header("Authorization", "Bearer " + token);
        
        // Optional: Set headers if required
        request.header("Content-Type", "application/json");
        
        // Define path parameters
        String auctionId = "ACFFE150-2857-4A32-A25C-B262BBDB9DA3";  // Replace with your auction ID
        request.pathParam("auctionId", auctionId);
        
        // Send the GET request with path parameter
        Response response = request.get("/bidders/pdf/{auctionId}");
        
        // Print the status line for debugging
        System.out.println("The status received: " + response.statusLine());
        
        // Save the binary response to a file
        if (response.getStatusCode() == 200) {
            byte[] fileData = response.asByteArray();  // Get the response as a byte array
            
            // Define the file path
            String filePath = "C:\\Auction_workspace\\bidders_report.pdf";  // File path with .pdf extension
            
            // Ensure the directory exists
            File directory = new File("C:\\Auction_workspace");
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                    return;
                }
            }
            
            // Write the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(fileData);
                System.out.println("PDF file successfully saved to: " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving the PDF file: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to download the PDF file. HTTP Status Code: " + response.getStatusCode());
        }

        // Assert that the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 OK, but got: " + response.getStatusCode());
    }
}
