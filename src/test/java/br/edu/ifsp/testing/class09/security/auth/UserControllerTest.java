package br.edu.ifsp.testing.class09.security.auth;

import br.edu.ifsp.testing.class09.security.user.User;
import br.edu.ifsp.testing.class09.utils.BaseApiIntegrationTest;
import br.edu.ifsp.testing.class09.utils.EntityBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class UserControllerTest extends BaseApiIntegrationTest {

    @Test
    @DisplayName("Should register user and return 201 with id as payload")
    void shouldRegisterUserAndReturn201WithIdAsPayload() {
        final User user = EntityBuilder.createRandomUser("123password");
        given() // request specification
                .contentType("application/json")
                .port(port)
                .body(user).
        when() // invocation
                .post("/api/v1/register").
        then() // validable response
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should login with valid credentials")
    void shouldLoginWithValidCredentials() {
        final String plainTextPassword = "123password";
        final User user = registerUser(plainTextPassword);
        AuthRequest authRequest = new AuthRequest(user.getEmail(), plainTextPassword);
        given()
                .contentType("application/json")
                .port(port)
                .body(authRequest).
        when()
                .post("/api/v1/authenticate").
        then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(200)
                .body("token", notNullValue());
    }
}