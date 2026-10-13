
package org.example.tests;

import org.example.config.ConfigReader;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open(ConfigReader.get("ui.base.url"));

        loginPage.login(
                ConfigReader.get("ui.user.name"),
                ConfigReader.get("ui.user.password")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/inventory.html"),
                "User should be redirected to the inventory page after login"
        );
    }
}
