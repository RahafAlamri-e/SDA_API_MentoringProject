package tests.day02;

import base_Urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T02 extends JPHBaseUrl {
    /*
    Given
    https://jsonplaceholder.typicode.com/todos
    When
    I send GET request
    Then
    1) Status code = 200
    2) Print all ids > 190 (10 total)
    3) Print userIds with ids < 5 (4 total)
    4) Verify title “quis eius est sint explicabo”
    5) Find id where title = "quo adipisci enim quam ut ab"
     */

    @Test
    void test01(){


        Response response = given(spec).when().get("/todos");
//        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

//        1) Status code = 200
        response.then().statusCode(200);

//        2) Print all ids > 190 (10 total)
        System.out.println(jsonPath.getList("findAll{it.id>190}.id"));

//        3) Print userIds with ids < 5 (4 total)
        System.out.println(jsonPath.getList("findAll{it.id<5}.userId"));

//        4) Verify title “quis eius est sint explicabo”
        System.out.println(jsonPath.getBoolean("findAll{it.title=='quis eius est sint explicabo'}"));

//        5) Find id where title = "quo adipisci enim quam ut ab"
        System.out.println(jsonPath.getInt("find{it.title=='quo adipisci enim quam ut ab'}.id"));

    }
}
