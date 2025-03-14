package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class FindersTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Should get the search bar from Google")
    void shouldGetTheSearchBarFromGoogle() {
        driver.get("https://www.google.com");
        final WebElement searchBar = driver.findElement(By.className("gLFyf")); //locates the element By criterion
        final String accessibleName = searchBar.getAccessibleName(); //gets the property
        System.out.println(accessibleName);
    }

    @Test
    @DisplayName("Should open Bing and search in the bar")
    void shouldOpenBingAndSearchInTheBar() {
        driver.get("https://bing.com");
        final WebElement searchBar = driver.findElement(By.className("sb_form_q"));
        searchBar.sendKeys("Selenium WebDriver"); //sends the String content to the DOM object
        delay(1000); // waits the text to be typed in the browser
        searchBar.sendKeys(Keys.ENTER); // press Enter
        delay(5000); // waits the next page to be rendered
    }

    @Test
    @DisplayName("Should page title starts with searched text")
    void shouldPageTitleStartsWithSearchedText() {
        driver.get("https://bing.com");
        final WebElement searchBar = driver.findElement(By.className("sb_form_q"));
        searchBar.sendKeys("Selenium WebDriver");
        delay(500); // waits the text to be typed in the browser
        searchBar.sendKeys(Keys.ENTER);
        delay(1000); // waits the next page to be rendered
        final String title = driver.getTitle(); // obtains the title of the page
        assertThat(title).startsWith("Selenium WebDriver");
    }

    @Test
    @DisplayName("Should page title starts with searched text using mouse")
    void shouldPageTitleStartsWithSearchedTextUsingMouse() {
        driver.get("https://bing.com");

        delay(4000); // waits the page to be rendered
        driver.findElement(By.className("bnp_btn_accept")).click(); // closes cookie banner

        driver.findElement(By.className("sb_form_q")).sendKeys("Selenium WebDriver");
        delay(5000); // waits the text to be typed in the browser

        driver.findElement(By.className("sb_search_btn")).click();
        delay(1000); // waits the next page to be rendered

        final String title = driver.getTitle(); // obtains the title of the page
        assertThat(title).startsWith("Selenium WebDriver");
    }
}


