package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;

public class InteractionTest extends BaseSeleniumTest{

    private static final String AUTHENTICATION_PAGE_FILE = "basic-registration-form/authenticate.html";
    private static final String REGISTRATION_PAGE_FILE = "basic-registration-form/register.html";

    @Test
    @DisplayName("Should test simple interactions")
    void shouldTestSimpleInteractions() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path);
        driver.findElement(By.id("email")).sendKeys("selenium@email.com");
        driver.findElement(By.id("psw")).sendKeys("123123");
        delay(1500);
        driver.findElement(By.id("psw")).clear();
        delay(1500);
        driver.findElement(By.className("registerbtn")).click();
        delay(1500);
        final String pswErrorMessage = driver.findElement(By.id("psw-err")).getText();
        assertThat(pswErrorMessage).isEqualTo("Campo obrigatório");
    }

    @Test
    void shouldSelectElements()  {
        String path = "file://" + getClass().getClassLoader().getResource(REGISTRATION_PAGE_FILE).getPath();
        driver.get(path);
        final WebElement element = driver.findElement(By.id("birth"));
        final Select select = new Select(element); // create a select from a WebElement
        delay(1500);
        select.selectByIndex(1);
        delay(1500);
        select.selectByValue("2003");
        delay(1500);
        select.selectByVisibleText("2001");
        delay(1500);
        assertThat(select.getFirstSelectedOption().getText()).isEqualTo("2001");
    }
}
