
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriverWait wait;

    private final By backpackName =
            By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']");

    private final By backpackPrice =
            By.cssSelector(".cart_item .inventory_item_price");

    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getBackpackName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(backpackName)
        ).getText();
    }

    public String getBackpackPrice() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(backpackPrice)
        ).getText();
    }

    public void clickCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}
