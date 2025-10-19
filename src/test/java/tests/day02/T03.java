package tests.day02;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class T03 {
    /*
    Given
    https://dummy.restapiexample.com/api/v1/employees
    When
    User sends GET request
    Then
    Status code is 200
    And
    There are 24 employees
    And
    "Tiger Nixon" and "Garrett Winters" are among them
    And
    Highest age = 66
    And
    Youngest = "Tatyana Fitzpatrick"
    And
    Total salary = 6,644,770

     */

    @Test
    void test01(){

        Response response = RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        // Status code is 200
        response.then().statusCode(200);

        // There are 24 employees
        List<Map<String, Object>> employees = jsonPath.getList("data");
        assertEquals(employees.size(), 24);

        // "Tiger Nixon" and "Garrett Winters" are among them
        assertTrue(jsonPath.getBoolean("data.any{it.employee_name.contains('Tiger Nixon')}"));
        assertTrue(jsonPath.getBoolean("data.any{it.employee_name.contains('Garrett Winters')}"));

        // Highest age = 66
        int highestAge = jsonPath.getInt("data.max{it.employee_age.toInteger()}.employee_age");
        System.out.println("Highest Age = " + highestAge);
        assertEquals(highestAge, 66);

        // Youngest = "Tatyana Fitzpatrick"
        String youngestEmployee = jsonPath.getString("data.min {it.employee_age.toInteger()}.employee_name");
        System.out.println("Youngest Employee = " + youngestEmployee);
        assertEquals(youngestEmployee, "Tatyana Fitzpatrick");

        // Total salary = 6,644,770
        int totalSalary = jsonPath.getInt("data.collect{ it.employee_salary.toInteger() }.sum()");
        System.out.println("Total Salary = " + totalSalary);
        assertEquals(totalSalary, 6644770);



    }
}
