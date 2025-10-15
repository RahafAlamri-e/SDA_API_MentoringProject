package tests.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T01_APIResponseValidation {

        /*
        Task 3: Rest Assured - API Response Validation
        API Endpoint: https://fakerestapi.azurewebsites.net/api/v1/Users

        Objective: Validate API response headers and status

        Test Requirements:
            Send GET request to the endpoint
            Validate response status code
            Validate response headers
            Verify server information

        Expected Assertions:
            Status code is 200
            Content-Type is "application/json"
            Server header contains "Kestrel"
            Transfer-Encoding is "chunked"
         */

        @Test
        void test01() {
            Response response = RestAssured.get("https://fakerestapi.azurewebsites.net/api/v1/Users");

            int statusCode = response.getStatusCode();
            System.out.println("Status Code: " + statusCode);
            Assert.assertEquals(statusCode, 200);

            String contentType = response.contentType();
            System.out.println("Content-Type: " + contentType);
            Assert.assertTrue(response.contentType().contains("application/json"));

            String serverHeader = response.header("Server");
            System.out.println("Server Header: " + serverHeader);
            Assert.assertTrue(response.header("Server").contains("Kestrel"));

            String transferEncoding = response.header("Transfer-Encoding");
            System.out.println("Transfer-Encoding: " + transferEncoding);
            Assert.assertEquals(response.header("Transfer-Encoding"), "chunked");

        }
}
