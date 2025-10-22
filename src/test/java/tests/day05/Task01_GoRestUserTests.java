package tests.day05;
import base_Urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utilities.ObjectMapperUtils.getJsonNode;

public class Task01_GoRestUserTests extends GoRestBaseUrl {

    private int userId;

    @Test(priority = 1)
    void getAllUsers() {

        Response response = given(spec).get("/users");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue());
    }

    @Test(priority = 2)
    void createUser() {

        JsonNode payload = getJsonNode("GoRestUser");
        System.out.println("payload = " + payload);

        Response response = given(spec).body(payload).post("/users");
        response.prettyPrint();

        response
                .then()
                .statusCode(201)
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "status", equalTo(payload.get("status").asText()),
                        "gender", equalTo(payload.get("gender").asText())
                        );

        // Save userId for next tests
        userId = response.jsonPath().getInt("id");
    }

    @Test(priority = 3)
    void getUserById() {
        Response response = given(spec).get("/users/" + userId);
        response.prettyPrint();

        JsonNode payload = getJsonNode("GoRestUser");

        response
                .then()
                .statusCode(200)
                .body("name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "status", equalTo(payload.get("status").asText()),
                        "gender", equalTo(payload.get("gender").asText())
                );
    }

    @Test(priority = 4)
    void updateUser() {

        JsonNode payload = getJsonNode("GoRestUserUpdate");

        Response response = given(spec).body(payload).put("/users/" + userId);
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "status", equalTo(payload.get("status").asText()),
                        "gender", equalTo(payload.get("gender").asText())
                );
    }

    @Test(priority = 5)
    void partialUpdateUser() {
        JsonNode patchPayload = getJsonNode("GoRestUserPartial");

        Response response = given(spec).body(patchPayload).patch("/users/" + userId);
        response.prettyPrint();


        JsonNode updatePayload = getJsonNode("GoRestUserUpdate");
        response
                .then()
                .statusCode(200)
                .body(
                        "name", equalTo(patchPayload.get("name").asText()),
                        "gender", equalTo(patchPayload.get("gender").asText()),
                        "email", equalTo(updatePayload.get("email").asText()),
                        "status", equalTo(updatePayload.get("status").asText())
                )
        ;
    }

    @Test(priority = 6)
    void deleteUser() {
        Response response = given(spec).delete("/users/" + userId);
        response.prettyPrint();

        response
                .then()
                .statusCode(204);
    }

    @Test(priority = 7)
    void getUserNegative() {
        Response response = given(spec).get("/users/" + userId);
        response.prettyPrint();

        response
                .then()
                .statusCode(404)
                .body(Matchers.containsString("Resource not found"));

    }
}
