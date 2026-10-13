
package org.example.api;

import io.restassured.response.Response;
import org.example.specifiaction.RequestSpec;

import static io.restassured.RestAssured.given;

public class ProductApi {

    public static Response getProducts() {

        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get("/products");
    }
}
