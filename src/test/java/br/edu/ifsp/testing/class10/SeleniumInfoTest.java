package br.edu.ifsp.testing.class10;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SeleniumInfoTest extends BaseSeleniumTest {

    private static final String AUTHENTICATION_PAGE_FILE = "basic-registration-form/authenticate.html";

    @Test
    @DisplayName("Should assert info from web element")
    void shouldAssertInfoFromWebElement() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path); // path to local page file
        final var email = driver.findElement(By.id("email"));
        email.sendKeys("student@univerty.com");
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(email.isEnabled()).as("Enabled").isTrue();
        softly.assertThat(email.isDisplayed()).as("Displayed").isTrue();
        softly.assertThat(email.getAttribute("value")).isEqualTo("student@univerty.com");
        softly.assertThat(email.getTagName()).as("Tag name").isEqualTo("input");
        softly.assertThat(email.getCssValue("padding")).as("Padding").isEqualTo("15px");
        softly.assertAll();
    }

    @Test
    @DisplayName("Should get page info")
    void shouldGetPageInfo() {
        driver.get("https://www.google.com");
        final var softly = new SoftAssertions();
        softly.assertThat(driver.getTitle()).isEqualTo("Google");
        softly.assertThat(driver.getCurrentUrl()).isEqualTo("https://www.google.com/");
        softly.assertThat(driver.getPageSource()).isNotEmpty();
        softly.assertAll();
    }
}


