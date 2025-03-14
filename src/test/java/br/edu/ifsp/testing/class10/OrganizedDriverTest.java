package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrganizedDriverTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("ShouldOpenAndCloseChromeBrowser")
    void shouldOpenAndCloseChromeBrowser() throws InterruptedException {
        driver.get("https://www.google.com");
        Thread.sleep(1000); // just to be able to see the automation happening
    }
}
