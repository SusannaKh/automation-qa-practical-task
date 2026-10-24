package org.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments(
                    "--disable-features=PasswordLeakDetection"
            );

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));
        }

        return driver.get();
    }

    public static void quitDriver() {

        WebDriver currentDriver = driver.get();

        try {
            if (currentDriver != null) {
                currentDriver.quit();
            }
        } finally {
            driver.remove();
        }
    }
}