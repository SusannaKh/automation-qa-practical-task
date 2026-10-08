package org.example.tests;

import io.restassured.response.Response;
import org.example.api.AuthApi;
import org.example.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    @Test
    public void loginTest() {

        Response response = AuthApi.login();

        Assert.assertEquals(
                response.statusCode(),
                200,
                "Login request failed"
        );

        String accessToken = response.jsonPath().getString("accessToken");

        Assert.assertNotNull(
                accessToken,
                "Access token should not be null"
        );

        System.out.println("Access Token: " + accessToken);
    }

    @Test
    public void getCurrentUserTest() {

        Response loginResponse = AuthApi.login();

        Assert.assertEquals(
                loginResponse.statusCode(),
                200,
                "Login request failed"
        );

        String accessToken = loginResponse.jsonPath().getString("accessToken");

        Assert.assertNotNull(
                accessToken,
                "Access token should not be null"
        );

        Response userResponse = AuthApi.getCurrentUser(accessToken);

        Assert.assertEquals(
                userResponse.statusCode(),
                200,
                "Get current user request failed"
        );

        Assert.assertNotNull(
                userResponse.jsonPath().get("id"),
                "User id should not be null"
        );

        Assert.assertEquals(
                userResponse.jsonPath().getString("username"),
                ConfigReader.get("api.user.name"),
                "Username is incorrect"
        );

        Assert.assertNotNull(
                userResponse.jsonPath().getString("email"),
                "User email should not be null"
        );
    }

}
