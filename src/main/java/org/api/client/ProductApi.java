package org.api.client;

import io.restassured.response.Response;

public class ProductApi extends BaseClient {

    public static Response getProducts() {
        return get("/products");
    }
}