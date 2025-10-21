package tests.day04;

import base_Urls.FakeStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static json_data.cartData.CART_BODY;
import static org.testng.Assert.assertEquals;

public class Task03_CreateCartJsonNode extends FakeStoreBaseUrl {
    //Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
    //https://fakestoreapi.com/carts

    @Test
    void C17_CreateCartJsonNodeTest() throws IOException {

        //Prepare the payload
        JsonNode payload = new ObjectMapper().readTree(CART_BODY);
        System.out.println("payload = " + payload);

        ((ObjectNode) payload).put("date", LocalDate.now().toString());
        System.out.println("payload Update = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/carts");
        response.prettyPrint();

        //Do assertion
        JsonNode actualResponse = new ObjectMapper().readTree(response.asString());

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
