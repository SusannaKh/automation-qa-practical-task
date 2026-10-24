package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        this.wait.ignoring(
                StaleElementReferenceException.class
        );

        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        ).click();
    }

    protected void type(WebElement element, String text) {
        wait.until(
                ExpectedConditions.visibilityOf(element)
        ).sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(d -> {
            if (element.isDisplayed()) {
                return element.getText();
            }
            return null;
        });
    }
}