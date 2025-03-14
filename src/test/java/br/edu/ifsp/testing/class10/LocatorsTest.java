package br.edu.ifsp.testing.class10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorsTest extends BaseSeleniumTest {

    private static final String AUTHENTICATION_PAGE_FILE = "basic-registration-form/authenticate.html";

    @Test
    @DisplayName("Should get elements using various strategies")
    void shouldGetElementsUsingVariousStrategies() {
        //The example page is placed in the test/resources folder
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path); // path to local page file
        assertThat(driver.findElement(By.id("email")).getText()).isEmpty();
        assertThat(driver.findElement(By.className("registerbtn")).getText()).isEqualTo("Entrar");
        assertThat(driver.findElement(By.name("psw")).getText()).isEmpty();
        assertThat(driver.findElement(By.tagName("h1")).getText()).isEqualTo("Login");
        assertThat(driver.findElement(By.linkText("Crie uma conta")).getText()).isEqualTo("Crie uma conta");
        assertThat(driver.findElement(By.partialLinkText("conta")).getText()).isEqualTo("Crie uma conta");
    }

    @Test
    @DisplayName("Should get elements in subset of DOM")
    void shouldGetElementsInSubsetOfDom() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path);
        driver.findElement(By.id("email")).sendKeys("selenium@ifsp.edu.br");
        driver.findElement(By.id("psw")).sendKeys("123456");
        driver.findElement(By.className("registerbtn")).click();
        final var container = driver.findElement(By.className("container"));
        final var errorDiv = container.findElement(By.tagName("DIV")); // subset of the DOM
        assertThat(errorDiv.getText()).isEqualTo("Usuário e/ou senha inválidos");
    }

    @Test
    @DisplayName("Should find element by css selector")
    void shouldFindElementByCssSelector() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path);
        driver.findElement(By.id("email")).sendKeys("selenium@ifsp.edu.br");
        driver.findElement(By.id("psw")).sendKeys("123456");
        driver.findElement(By.className("registerbtn")).click();
        final var errorDiv = driver.findElement(By.cssSelector(".container div"));
        assertThat(errorDiv.getText()).isEqualTo("Usuário e/ou senha inválidos");
    }

    @Test
    @DisplayName("Should find all input fields")
    void shouldFindAllInputFields() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path);
        final List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        assertThat(inputElements).allMatch(element -> element.getText().isEmpty());
    }

    @Test
    @DisplayName("Should find elements by relative criteria")
    void shouldFindElementsByRelativeCriteria() {
        String path = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(path);
        final By locator = RelativeLocator.with(By.tagName("a")) // locate something with tag <a>
                .below(By.className("registerbtn")) // that is bellow the button "Entrar"
                .toRightOf(By.id("psw-err"));// and is to the right of the password error message
        final WebElement link = driver.findElement(locator);
        assertThat(link.getText()).isEqualTo("Crie uma conta");
    }
}


