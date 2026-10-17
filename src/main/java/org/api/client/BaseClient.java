package org.api.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.api.models.LoginRequest;
import org.specifiaction.RequestSpec;

public class BaseClient {


    public static Response post(LoginRequest body, String endpoint) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response get(String accessToken, String endpoint) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(endpoint);
    }


    public static Response get(String endpoint) {
        return given()
                .spec(RequestSpec.getRequestSpec())
                .when()
                .get(endpoint);
    }

}
