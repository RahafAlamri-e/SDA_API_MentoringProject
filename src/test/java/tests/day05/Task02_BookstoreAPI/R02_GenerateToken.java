package tests.day05.Task02_BookstoreAPI;

import base_Urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;


public class R02_GenerateToken extends BookStoreBaseUrl {
    public static String token;

    @Test(priority = 2)
    void generateTokenTest() {
        JsonNode payload = ObjectMapperUtils.getJsonNode("bookstore_user");

        Response response = given(spec).body(payload).post("/Account/v1/GenerateToken");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("token", Matchers.notNullValue());

        token = response.jsonPath().getString("token");

    }
}
