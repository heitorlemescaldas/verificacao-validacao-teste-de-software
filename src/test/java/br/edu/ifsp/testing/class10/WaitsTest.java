package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class WaitsTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    void shouldResultPageTitleStartWithTheTextBeingSearched() {
        driver.get("https://www.google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver"); // query box
        driver.findElement(By.className("gNO89b")).click(); // the search button class is "gNO89b"
        assertThat(driver.getTitle()).isNotEmpty();
    }

    @Test
    void shouldResultPageTitleStartWithTheTextBeingSearchedWithSleep() throws InterruptedException {
        driver.get("https://www.google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver");
        Thread.sleep(1000); //wait at least 1000ms
        driver.findElement(By.className("gNO89b")).click();
        Thread.sleep(1000); //wait at least 1000ms
        assertThat(driver.getTitle()).isNotEmpty();
    }

    @Test
    void shouldResultPageTitleStartWithTheTextBeingSearchedWithExplicitWait() {
        driver.get("https://www.google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver");
        final WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)) // 10s timeout
                .until(ExpectedConditions.elementToBeClickable(By.className("gNO89b"))); // define expected condition
        button.click();
        assertThat(driver.getTitle()).isNotEmpty();
    }

    @Test
    void shouldResultPageTitleStartWithTheTextBeingSearchedWithFluentWait() {
        driver.get("https://www.google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver");
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)) // max wait duration
                .pollingEvery(Duration.ofMillis(700)) // verification frequency
                .ignoring(NoSuchElementException.class) // ignore this exception
                .until(ExpectedConditions.elementToBeClickable(By.className("gNO89b")))
                .click();
        assertThat(driver.getTitle()).isNotEmpty();
    }
}


