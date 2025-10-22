package base_Urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class GoRestBaseUrl {

    protected RequestSpecification spec;
    private static String baseUrl = "https://gorest.co.in/public/v2";
    private static String token = "b276c80a7791e5946d3e04a3aa27d76642b11cdb552535580e4505ee056aad69";

    @BeforeMethod//Before each test method, this will work and initialize the spec object.
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .build();
    }

//    String getToken() {
//
//        String credentials = """
//                {
//                    "username" : "admin",
//                    "password" : "password123"
//                }""";
//
//        return given()
//                .body(credentials)
//                .contentType(ContentType.JSON)
//                .post(baseUrl + "/auth")
//                .jsonPath()
//                .getString("token");
//    }

}
