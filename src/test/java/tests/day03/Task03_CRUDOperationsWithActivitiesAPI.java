package tests.day03;

import base_Urls.CRUDBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.CRUDPojo.CRUDpojo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task03_CRUDOperationsWithActivitiesAPI extends CRUDBaseUrl {
    /*
    Task: Write code that performs all CRUD operations on "activities"
    using the Fake REST API at https://fakerestapi.azurewebsites.net
    Requirements:
    1. Use POJO classes for all operations
    2. Implement CREATE (POST) - Add new activity
    3. Implement READ (GET) - Retrieve activity details
    4. Implement UPDATE (PUT) - Modify existing activity
    5. Implement DELETE - Remove activity
    6. Add appropriate assertions for each operation
    */

    @Test
    public void testCRUDOperations() {
//        2. Implement CREATE (POST) - Add new activity
        String dueDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        CRUDpojo expectedActivity = new CRUDpojo(1, "Activity 1", dueDate, false);

        Response postResponse = RestAssured.given(spec).body(expectedActivity).post("/Activities");
        postResponse.prettyPrint();

        CRUDpojo createdActivity = postResponse.as(CRUDpojo.class);
        assertEquals(postResponse.statusCode(), 200);
        assertEquals(createdActivity.getId(), expectedActivity.getId());
        assertEquals(createdActivity.getTitle(), expectedActivity.getTitle());
        assertEquals(createdActivity.getDueDate(), expectedActivity.getDueDate());
        assertEquals(createdActivity.isCompleted(), expectedActivity.isCompleted());

//        3. Implement READ (GET) - Retrieve activity details
        Response getResponse = RestAssured.given(spec).get("/Activities/" + createdActivity.getId());
        getResponse.prettyPrint();

        CRUDpojo getActivity = getResponse.as(CRUDpojo.class);
        assertEquals(getResponse.statusCode(), 200);
        assertEquals(getActivity.getId(), createdActivity.getId());
        assertEquals(getActivity.getTitle(), createdActivity.getTitle());
        assertTrue(getActivity.getDueDate().startsWith(createdActivity.getDueDate().substring(0, 10)));
        assertEquals(getActivity.isCompleted(), createdActivity.isCompleted());

//        4. Implement UPDATE (PUT) - Modify existing activity
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        CRUDpojo updatedActivity = new CRUDpojo(1, "Updated Activity Title", currentDate, true);

        Response putResponse = RestAssured.given(spec).body(updatedActivity).put("/Activities/"+ updatedActivity.getId());
        putResponse.prettyPrint();

        CRUDpojo responseBody = putResponse.as(CRUDpojo.class);
        assertEquals(putResponse.statusCode(), 200);
        assertEquals(responseBody.getId(), updatedActivity.getId());
        assertEquals(responseBody.getTitle(), updatedActivity.getTitle());
        assertTrue(responseBody.getDueDate().startsWith(updatedActivity.getDueDate().substring(0, 10)));
        assertEquals(responseBody.isCompleted(), updatedActivity.isCompleted());


//        5. Implement DELETE - Remove activity
        Response deleteResponse =RestAssured.given(spec).delete("/Activities/" + updatedActivity.getId());
        deleteResponse.then().statusCode(200);


    }

}
