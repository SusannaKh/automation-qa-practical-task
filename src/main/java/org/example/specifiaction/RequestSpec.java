package org.example.specifiaction;


import  io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.config.ConfigReader;

public class RequestSpec {
    private static final RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.get("api.base.url"))
                    .setContentType("application/json")
                    .build();

    public static RequestSpecification getRequestSpec() {
        return requestSpecification;
    }
}
