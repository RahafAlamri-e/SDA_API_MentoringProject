package tests.day05.Task02_BookstoreAPI;

import base_Urls.BookStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tests.day05.Task02_BookstoreAPI.R01_CreateUser.userId;
import static tests.day05.Task02_BookstoreAPI.R02_GenerateToken.token;
import static tests.day05.Task02_BookstoreAPI.R03_GetAllBooks.firstBookIsbn;

public class R05_GetUserInfo extends BookStoreBaseUrl {

    @Test(priority = 5)
    void getUserInfoTest() {
        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .get("/Account/v1/User/" + userId);

        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .body("books[0].isbn",  equalTo(firstBookIsbn));

    }
}
