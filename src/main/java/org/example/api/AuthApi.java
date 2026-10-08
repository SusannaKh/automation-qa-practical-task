package org.example.api;


import io.restassured.response.Response;
import org.example.config.ConfigReader;
import org.example.specifiaction.RequestSpec;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi {
    public static Response login(){
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("username", ConfigReader.get("api.user.name"));
        loginBody.put("password", ConfigReader.get("api.user.password"));

        return given()
                .spec(RequestSpec.getRequestSpec())
                .body(loginBody)
                .when()
                .post("/auth/login");
    }
    public static Response getCurrentUser(String accessToken){
        return given()
                .spec(RequestSpec.getRequestSpec())
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/auth/me");
    }
}
