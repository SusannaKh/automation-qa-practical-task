package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@data-test='inventory-item-name' and normalize-space()='Sauce Labs Backpack']")
    private WebElement backpackName;

    @FindBy(css = ".cart_item .inventory_item_price")
    private WebElement backpackPrice;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getBackpackName() {
        return getText(backpackName);
    }

    public String getBackpackPrice() {
        return getText(backpackPrice);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}