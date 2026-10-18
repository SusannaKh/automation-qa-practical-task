
package org.example.test.web;

import org.config.ConfigReader;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void verifyBackpackInCartTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("ui.user.name"),
                ConfigReader.get("ui.user.password")
        );

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
}
