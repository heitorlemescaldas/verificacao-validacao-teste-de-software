package br.edu.ifsp.testing.class10.pos;

import org.openqa.selenium.WebDriver;

public class BasePageObject {
    protected final WebDriver driver;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    // Other useful common methods ...
}
