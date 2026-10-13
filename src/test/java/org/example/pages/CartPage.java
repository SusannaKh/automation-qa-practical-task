
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By backpackName =
            By.className("inventory_item_name");

    private final By backpackPrice =
            By.className("inventory_item_price");

    public CartPage(WebDriver driver) {
        this.driver = driver;
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
}
