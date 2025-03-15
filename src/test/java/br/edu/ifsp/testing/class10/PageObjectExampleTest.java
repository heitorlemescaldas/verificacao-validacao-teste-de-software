package br.edu.ifsp.testing.class10;

import br.edu.ifsp.testing.class10.pos.AuthenticationPageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PageObjectExampleTest extends BaseSeleniumTest {

    private static final String AUTHENTICATION_PAGE_FILE = "basic-registration-form/authenticate.html";

    @Override
    public void setInitialPage() {
        String page = "file://" + getClass().getClassLoader().getResource(AUTHENTICATION_PAGE_FILE).getPath();
        driver.get(page);
    }

    @Test
    @DisplayName("Should navigate to registration page by clicking the link")
    void shouldNavigateToRegistrationPageByClickingTheLink() {
        var authPage = new AuthenticationPageObject(driver);
        var registrationPage = authPage.navigateToRegistrationPage();
        assertThat(registrationPage.pageTitle()).isEqualTo("Cadastro");
    }

    @Test
    @DisplayName("Should get wrong user or password error message ")
    void shouldGetWrongUserOrPasswordErrorMessage () {
        var authPage = new AuthenticationPageObject(driver);
        authPage.authenticate("admin", "admin");
        assertThat(authPage.pageErrorMessage()).isEqualTo("Usuário e/ou senha inválidos");
    }
}
