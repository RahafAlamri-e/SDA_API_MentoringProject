package tests.day02;

import base_Urls.BookersBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T01 extends BookersBaseUrl {

    /*
    Given
    https://restful-booker.herokuapp.com/booking/32
    When
    User sends GET request
    Then
    Status Code: 200
    And
    Content Type: application/json
    And
    firstname: "Josh"
    lastname: "Allen"
    totalprice: 111

    */

    @Test
    void test01(){


        //Set the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "John");
        payload.put("lastname", "Smith");
        payload.put("totalprice", 111);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).when().get("/booking/32");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo(payload.get("firstname")))
                .body("lastname", equalTo(payload.get("lastname")))
                .body("totalprice", equalTo(payload.get("totalprice")));

    }
}
