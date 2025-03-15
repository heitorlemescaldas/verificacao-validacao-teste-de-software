package br.edu.ifsp.testing.class10.pos;

import org.openqa.selenium.WebDriver;

public class RegistrationPageObject extends BasePageObject {
    private static final String PAGE_TITLE = "Cadastro";

    public RegistrationPageObject(WebDriver driver) {
        super(driver);
        if (!PAGE_TITLE.equals(pageTitle())) throw new IllegalStateException("Wrong page url: " + driver.getCurrentUrl());
    }

    // Complete the rest of the page object...
}
