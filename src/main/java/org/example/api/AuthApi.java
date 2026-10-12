package org.example.api;

import io.restassured.response.Response;
import org.example.config.ConfigReader;
import org.example.specifiaction.RequestSpec;

import static io.restassured.RestAssured.given;

public class AuthApi {

    public static Response login() {

        String loginBody = """
                {
                    "username": "%s",
                    "password": "%s"
                }
                """.formatted(
                ConfigReader.get("api.user.name"),
                ConfigReader.get("api.user.password")
        );

        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(loginBody)
                .when()
                .post("/auth/login");
    }

    public static Response getCurrentUser(String accessToken) {

        return given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/auth/me");
    }
}