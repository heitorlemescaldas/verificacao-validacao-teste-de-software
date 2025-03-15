package br.edu.ifsp.testing.class10.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPageObject extends BasePageObject {

    private static final String PAGE_TITLE = "Login";

    public AuthenticationPageObject(WebDriver driver) {
        super(driver);
        if (!PAGE_TITLE.equals(pageTitle())) throw new IllegalStateException("Wrong page url: " + driver.getCurrentUrl());
    }

    public void authenticate(String username, String password){
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("psw")).sendKeys(password);
        driver.findElement(By.className("registerbtn")).click();
    }

    public RegistrationPageObject navigateToRegistrationPage(){
        driver.findElement(By.linkText("Crie uma conta")).click();
        return new RegistrationPageObject(driver);
    }

    public String pageErrorMessage(){
        return driver.findElement(By.id("msg-err")).getText();
    }

    public String username(){
        return driver.findElement(By.id("email")).getText();
    }

    public String emailErrorMessage(){
        return driver.findElement(By.id("email-err")).getText();
    }

    public String password(){
        return driver.findElement(By.id("psw")).getText();
    }

    public String passwordErrorMessage(){
        return driver.findElement(By.id("psw-err")).getText();
    }
}

