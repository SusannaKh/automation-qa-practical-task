package org.api.client;

import io.restassured.response.Response;
import org.api.models.LoginRequest;
import org.config.ConfigReader;

public class AuthApi extends BaseClient {

    public static Response login() {

        LoginRequest body = new LoginRequest();
        body.setUsername(ConfigReader.get("api.user.name"));
        body.setPassword(ConfigReader.get("api.user.password"));

        return post(body, "/auth/login");
    }

    public static Response getCurrentUser(String accessToken) {
        return get(accessToken, "/auth/me");
    }
}