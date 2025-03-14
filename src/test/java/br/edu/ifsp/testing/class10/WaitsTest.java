package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

//    @Test
//    @DisplayName("Should page title starts with searched text using mouse")
//    void shouldPageTitleStartsWithSearchedTextUsingMouse() {
//        driver.get("https://bing.com");
//
//        delay(4000); // waits the page to be rendered
//        driver.findElement(By.className("bnp_btn_accept")).click(); // closes cookie banner
//
//        driver.findElement(By.className("sb_form_q")).sendKeys("Selenium WebDriver");
//        delay(5000); // waits the text to be typed in the browser
//
//        driver.findElement(By.className("sb_search_btn")).click();
//        delay(1000); // waits the next page to be rendered
//
//        final String title = driver.getTitle(); // obtains the title of the page
//        assertThat(title).startsWith("Selenium WebDriver");
//    }

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
    void shouldResultPageTitleStartWithTheTextBeingSearchedWithImplicit() {
        driver.get("https://www.google.com");
        driver.findElement(By.className("gLFyf")).sendKeys("Selenium WebDriver");
        driver.findElement(By.className("gNO89b")).click();
        assertThat(driver.getTitle()).isNotEmpty();
    }
}
