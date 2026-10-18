package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private final WebDriverWait wait;

    private final By backpackAddToCartButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By backpackName =
            By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']");

    private final By backpackPrice =
            By.xpath("//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']/ancestor::div[contains(@class,'inventory_item_description')]//div[@data-test='inventory-item-price']");

    private final By cartLink =
            By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
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

    public void addBackpackToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(backpackAddToCartButton)
        ).click();
    }


    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );
    }

}