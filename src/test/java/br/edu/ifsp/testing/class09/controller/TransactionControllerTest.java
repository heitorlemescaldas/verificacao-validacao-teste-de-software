package br.edu.ifsp.testing.class09.controller;

import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class TransactionControllerTest extends BaseIntegrationTest {

    @BeforeAll
    public static void configRestAssured() {
        baseURI = "http://localhost:8080";
    }

    @Test
    @DisplayName("Should register user and return 201 with id as payload")
    void shouldRegisterUserAndReturn201WithIdAsPayload() {
        given()
                .contentType("application/json")
                .port(port)
                .body(Payloads.user).
        when()
                .post("/api/v1/register").
        then()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(201)
                .body("id", notNullValue());
    }
}