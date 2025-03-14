package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import static org.assertj.core.api.Assertions.assertThat;

public class BrowserTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Should navigate between pages")
    void shouldNavigateBetweenPages() {
        driver.get("https://www.google.com");
        driver.navigate().to("https://www.bing.com"); // same as the line before
        final WebElement queryBox = driver.findElement(By.id("sb_form_q"));
        queryBox.sendKeys("Which one is better, Google or Bing?");
        delay(5000); //waits to finish typing
        queryBox.sendKeys(Keys.RETURN);
        driver.navigate().back();  // back to previous page
        delay(4000); //waits the page to render
        final String title = driver.getTitle();
        assertThat(title).endsWith("Bing");
    }

    @Test
    @DisplayName("Should modify window")
    void shouldModifyWindow() {
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        delay(1000); //only to see the events. Do not use it in real testing code
        driver.manage().window().fullscreen();
        delay(1000);
        driver.manage().window().minimize(); //window object has other interesting methods
        delay(1000);
    }

    @Test
    void shouldSwitchBetweenTabs() throws InterruptedException {
        driver.get("https://www.google.com");
        final String originalWindow = driver.getWindowHandle(); //Get the ID of the original tab
        delay(1000);
        driver.switchTo().newWindow(WindowType.TAB); // Opens a new tab and switches to it
        delay(1000);
        driver.switchTo().window(originalWindow); //Switch to tab/window using its id
        delay(1000);
        driver.getWindowHandles().stream() //Get a set of all tab ids in the window
                .filter(windowId -> !windowId.equals(originalWindow))
                .findAny().ifPresentOrElse(driver.switchTo()::window, Assertions::fail);
        delay(1000);
    }
}

