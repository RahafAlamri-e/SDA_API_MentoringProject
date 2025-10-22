package tests.day05.Task02_BookstoreAPI;

import base_Urls.BookStoreBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class R03_GetAllBooks extends BookStoreBaseUrl {

    public static String firstBookIsbn;

    @Test
    void getAllBooksTest() {
        Response response = given(spec).get("/BookStore/v1/Books");
        response.prettyPrint();

        response
                .then()
                .statusCode(200);

        firstBookIsbn = response.jsonPath().getString("books[0].isbn");

    }
}
