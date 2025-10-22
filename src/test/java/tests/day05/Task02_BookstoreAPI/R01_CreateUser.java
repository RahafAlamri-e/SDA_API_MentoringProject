package tests.day05.Task02_BookstoreAPI;

import base_Urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R01_CreateUser extends BookStoreBaseUrl {
    public static String userId;
    @Test(priority = 1)
    void createUserTest() {
        JsonNode payload = getJsonNode("bookstore_user"); // from JSON file

        Response response = given(spec)
                .body(payload)
                .post("/Account/v1/User");

        response.prettyPrint();
        response
                .then()
                .statusCode(201)
                .body("username", equalTo(payload.get("userName").asText()));

        userId = response.jsonPath().getString("userID");



    }
}
