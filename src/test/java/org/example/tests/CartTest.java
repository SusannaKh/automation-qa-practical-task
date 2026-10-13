
package org.example.tests;

import org.example.config.ConfigReader;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void verifyBackpackInCartTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open(ConfigReader.get("ui.base.url"));
        loginPage.login(
                ConfigReader.get("ui.user.name"),
                ConfigReader.get("ui.user.password")
        );

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getBackpackName(),
                "Sauce Labs Backpack",
                "Cart should contain Sauce Labs Backpack"
        );

        Assert.assertEquals(
                cartPage.getBackpackPrice(),
                "$29.99",
                "Backpack price should be $29.99"
        );
    }
}
