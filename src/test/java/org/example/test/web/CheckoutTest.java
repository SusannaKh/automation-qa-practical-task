package org.example.test.web;

import org.config.ConfigReader;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {

        // 1. Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("ui.user.name"),
                ConfigReader.get("ui.user.password")
        );

        // 2. Add product to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        // 3. Open checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // 4. Fill customer information
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.fillCheckoutInformation(
                "Anna",
                "Smith",
                "0010"
        );

        // 5. Continue and finish order
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        // 6. Verify successful order
        Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "Checkout confirmation message is incorrect"
        );
    }
}