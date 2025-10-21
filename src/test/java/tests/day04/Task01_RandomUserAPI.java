package tests.day04;

import base_Urls.RandomUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.randomUser.RandomUserPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Task01_RandomUserAPI extends RandomUserBaseUrl {

/*
    Requirements:
        Send a GET request to https://randomuser.me/api
        The response will contain random user information in nested JSON structure
        Deserialize the response into a POJO class
        Assert that the following fields are NOT null:
        Email
        Username
        Password
        Medium picture URL
 */

    @Test
    void randomUserAPITest() {

        //Send the request
        Response response = given(spec).get("/api");
        response.prettyPrint();

        //Do assertion
        RandomUserPojo actualData = response.as(RandomUserPojo.class);
        assertEquals(response.statusCode(), 200);

        // Assert that the following fields are NOT null:
        assertNotNull(actualData.getResults().getFirst().getEmail());
        assertNotNull(actualData.getResults().getFirst().getLogin().getUsername());
        assertNotNull(actualData.getResults().getFirst().getLogin().getPassword());
        assertNotNull(actualData.getResults().getFirst().getPicture().getMedium());

    }
}
