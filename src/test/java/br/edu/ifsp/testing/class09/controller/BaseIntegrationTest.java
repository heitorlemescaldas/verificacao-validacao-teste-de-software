package br.edu.ifsp.testing.class09.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import static io.restassured.RestAssured.baseURI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseIntegrationTest {

    @Autowired private JdbcTemplate jdbcTemplate;
    @LocalServerPort protected int port = 8080;

    @BeforeAll
    public static void configRestAssured() {
        baseURI = "http://localhost:8080";
    }

    @AfterEach
    void setup(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "app_user", "transactions");
    }
}
