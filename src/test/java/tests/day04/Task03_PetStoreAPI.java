package tests.day04;

import base_Urls.FakeStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static json_data.cartData.CART_BODY;
import static org.testng.Assert.assertEquals;

public class Task03_PetStoreAPI extends FakeStoreBaseUrl {
    /*
    Requirements:
    Reference the API documentation at https://fakestoreapi.com/docs
    Use JsonNode to create the request payload dynamically
    Create a cart with properties like:
    userId
    date
    products (array of product objects with productId and quantity)
    Modify the JsonNode to add additional fields as needed
    Send a POST request to the create cart endpoint
    Assert that the response status code indicates success
    Assert that the returned cart contains the expected data
     */

    @Test
    void C17_petStoreAPITest() {

        //Prepare the payload
        JsonNode payload = ObjectMapperUtils.getJsonNode(CART_BODY);
        System.out.println("payload = " + payload);

        ((ObjectNode) payload).put("date", LocalDate.now().toString());
        System.out.println("payload Update = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/carts");
        response.prettyPrint();

        //Do assertion
        JsonNode actualResponse = ObjectMapperUtils.getJsonNode(response.asString());

        assertEquals(response.statusCode(), 201);
        assertEquals(actualResponse.get("userId").intValue(), payload.get("userId").intValue());
        assertEquals(actualResponse.get("date").textValue(), payload.get("date").textValue());
        assertEquals(actualResponse.get("products").get(0).get("id").intValue(), payload.get("products").get(0).get("id").intValue());
        assertEquals(actualResponse.get("products").get(0).get("title").textValue(), payload.get("products").get(0).get("title").textValue());
        assertEquals(actualResponse.get("products").get(0).get("price").intValue(), payload.get("products").get(0).get("price").intValue());
        assertEquals(actualResponse.get("products").get(0).get("description").textValue(), payload.get("products").get(0).get("description").textValue());
        assertEquals(actualResponse.get("products").get(0).get("category").textValue(), payload.get("products").get(0).get("category").textValue());
        assertEquals(actualResponse.get("products").get(0).get("image").textValue(), payload.get("products").get(0).get("image").textValue());

    }
}
