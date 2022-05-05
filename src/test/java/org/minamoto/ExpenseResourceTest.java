package org.minamoto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ExpenseResourceTest {

    @Test
    public void testExpenseEndpoint() {
        given()
          .when().get("/expenses")
          .then()
             .statusCode(200);
    }

}