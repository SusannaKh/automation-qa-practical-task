package org.example.test.api;

import io.restassured.response.Response;
import org.api.client.AuthApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.api.client.ProductApi;
import java.util.List;
import java.util.Map;


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



    @Test
    public void getCurrentUserTest() {

        Response loginResponse = AuthApi.login();

        Assert.assertEquals(
                loginResponse.getStatusCode(),
                200,
                "Login request should return 200"
        );

        String accessToken = loginResponse.jsonPath()
                .getString("accessToken");

        Assert.assertNotNull(
                accessToken,
                "Access token should not be null"
        );

        Assert.assertFalse(
                accessToken.isBlank(),
                "Access token should not be empty"
        );

        Response response = AuthApi.getCurrentUser(accessToken);

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Current user request should return 200"
        );

        Integer id = response.jsonPath().getInt("id");
        String username = response.jsonPath().getString("username");
        String email = response.jsonPath().getString("email");

        Assert.assertNotNull(id, "User ID should not be null");
        Assert.assertTrue(id > 0, "User ID should be positive");

        Assert.assertEquals(
                username,
                "emilys",
                "Username should match the configured test user"
        );

        Assert.assertNotNull(email, "Email should not be null");
        Assert.assertFalse(email.isBlank(), "Email should not be empty");
    }

    @Test
    public void getProductsTest() {

        Response response = ProductApi.getProducts();

        // Verify status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Get products request should return 200"
        );

        // Extract products list
        List<Map<String, Object>> products =
                response.jsonPath().getList("products");

        // Verify products list is not empty
        Assert.assertNotNull(
                products,
                "Products list should not be null"
        );

        Assert.assertFalse(
                products.isEmpty(),
                "Products list should not be empty"
        );

        // Validate every product
        for (Map<String, Object> product : products) {

            Object id = product.get("id");
            Object price = product.get("price");

            Assert.assertNotNull(
                    id,
                    "Product ID should not be null"
            );

            Assert.assertTrue(
                    id instanceof Number
                            && ((Number) id).intValue() > 0,
                    "Product ID should be a positive number"
            );

            Assert.assertNotNull(
                    price,
                    "Product price should not be null"
            );

            Assert.assertTrue(
                    price instanceof Number
                            && ((Number) price).doubleValue() > 0,
                    "Product price should be a positive number"
            );
        }
    }

}