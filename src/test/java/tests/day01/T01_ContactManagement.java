package tests.day01;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class T01_ContactManagement {

    /*
    Create a user
    Response response = RestAssured.get("https://api.example.com/login");
    response.then().assertThat().cookie("session_id", equalTo("12345"));

    java
    import io.restassured.RestAssured;
    import io.restassured.response.Response;
    import static org.hamcrest.Matchers.*;
    public class ApiTest {
    public static void main(String[] args) {
    // Send GET request
    Response response = RestAssured.get("https://api.example.com/users");
    // Get status code
    int statusCode = response.statusCode();
    System.out.println("Status Code: " + statusCode);
    // Print response
    response.prettyPrint();
    // Assertions
    response.then().statusCode(200);
    response.then().assertThat().header("Content-Type", containsString("application/json"));
    response.then().assertThat().body("data", notNullValue());
    }
    }

    Add a contact to the created user
    Delete the contact
    Delete the user
     */

        @Test
        void test01()    {
            Response loginResponse = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .body("{\"email\":\"precious.lakin5@yahoo.com\", \"password\":\"Rr@1234567\"}")
                    .post("https://thinking-tester-contact-list.herokuapp.com/users/login");


            loginResponse.then().statusCode(200);
            loginResponse.then().assertThat().header("Content-Type", containsString("application/json"));
            loginResponse.then().assertThat().body("token", notNullValue());
            loginResponse.then().assertThat().body("user._id", notNullValue());


            String token = loginResponse.jsonPath().getString("token");
            Response contactsResponse = RestAssured
                    .given()
                    .header("Authorization", "Bearer " + token)
                    .get("https://thinking-tester-contact-list.herokuapp.com/contacts");

            contactsResponse.then().statusCode(200);
            contactsResponse.then().assertThat().header("Content-Type", containsString("application/json"));
            contactsResponse.then().assertThat().body("", notNullValue());

        }
    }

