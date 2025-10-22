package tests.day05.Task02_BookstoreAPI;

import base_Urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tests.day05.Task02_BookstoreAPI.R01_CreateUser.userId;
import static tests.day05.Task02_BookstoreAPI.R02_GenerateToken.token;
import static tests.day05.Task02_BookstoreAPI.R03_GetAllBooks.firstBookIsbn;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R04_AssignBooksToUser extends BookStoreBaseUrl {

    @Test(priority = 4)
    void assignBookToUserTest() {
        JsonNode payload = getJsonNode("bookstore_assignBook");

        ((ObjectNode) payload).put("userId", userId);
        ArrayNode booksArray = (ArrayNode) ((ObjectNode) payload).get("collectionOfIsbns");
        ((ObjectNode) booksArray.get(0)).put("isbn", firstBookIsbn);

        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .post("/BookStore/v1/Books");

        response.prettyPrint();
        response
                .then()
                .statusCode(201)
                .body("books[0].isbn", equalTo(firstBookIsbn));

    }
}
