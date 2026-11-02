package org.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartButton;

    @FindBy(xpath = "//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']")
    private WebElement backpackName;

    @FindBy(xpath = "//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']/ancestor::div[contains(@class,'inventory_item_description')]//div[@data-test='inventory-item-price']")
    private WebElement backpackPrice;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBackpackName() {
        return getText(backpackName);
    }

    public String getBackpackPrice() {
        return getText(backpackPrice);
    }

    public void addBackpackToCart() {
        click(backpackAddToCartButton);
    }

    public void openCart() {
        click(cartLink);

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );
    }
}