package org.example.tests;

import io.restassured.response.Response;
import org.example.api.AuthApi;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    @Test
    public void loginTest() {

        Response response = AuthApi.login();

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Login request should return 200"
        );

        String accessToken = response.jsonPath()
                .getString("accessToken");

        Assert.assertNotNull(
                accessToken,
                "Access token should not be null"
        );

        Assert.assertFalse(
                accessToken.isEmpty(),
                "Access token should not be empty"
        );
    }
}