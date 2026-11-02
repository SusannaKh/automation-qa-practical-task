
package org.example.test.web;

import org.model.User;
import org.utils.DatabaseUtils;
import org.pages.CartPage;
import org.pages.InventoryPage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.config.ConfigReader;

public class CartTest extends BaseTest {

    @Test
    public void verifyBackpackInCartTest() {

        // Get user credentials from PostgreSQL
        User user = DatabaseUtils.getUserById(1);

        String username = user.getUsername();
        String password = user.getPassword();

        // Login to SauceDemo
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open(ConfigReader.get("ui.base.url"));
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);

        String inventoryName = inventoryPage.getBackpackName();
        String inventoryPrice = inventoryPage.getBackpackPrice();

        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart.html"),
                "Cart page should be opened"
        );

        Assert.assertEquals(
                cartPage.getBackpackName(),
                inventoryName,
                "Product name should match the inventory page"
        );

        Assert.assertEquals(
                cartPage.getBackpackPrice(),
                inventoryPrice,
                "Product price should match the inventory page"
        );
    }

    @Test
    public void getUserByInvalidIdTest() {

        RuntimeException exception = Assert.expectThrows(
                RuntimeException.class,
                () -> DatabaseUtils.getUserById(999)
        );

        Assert.assertEquals(
                exception.getMessage(),
                "User not found with id: 999",
                "Expected an error when the user does not exist"
        );
    }

}
