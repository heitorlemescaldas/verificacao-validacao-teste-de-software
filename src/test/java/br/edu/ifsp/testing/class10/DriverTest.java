package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverTest {

    @Test
    @DisplayName("ShouldOpenAndCloseChromeBrowser")
    void shouldOpenAndCloseChromeBrowser() throws InterruptedException {
        WebDriver driver = new ChromeDriver(); // Automatically manages the driver since version 4.6.0
        driver.get("https://www.google.com");
        Thread.sleep(1000); // just to be able to see the automation happening
        driver.quit();
    }

    @Test
    @DisplayName("ShouldOpenAndCloseFirefoxBrowser")
    void shouldOpenAndCloseFirefoxBrowser() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        Thread.sleep(1000);
        driver.quit();
    }
}
