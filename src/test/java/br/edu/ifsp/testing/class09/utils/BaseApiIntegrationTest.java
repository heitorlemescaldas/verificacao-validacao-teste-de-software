package br.edu.ifsp.testing.class09.utils;

import br.edu.ifsp.testing.class09.security.auth.AuthRequest;
import br.edu.ifsp.testing.class09.security.auth.AuthResponse;
import br.edu.ifsp.testing.class09.security.user.JpaUserRepository;
import br.edu.ifsp.testing.class09.security.user.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static io.restassured.RestAssured.baseURI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseApiIntegrationTest {

    @LocalServerPort protected int port = 8080;
    @Autowired private JpaUserRepository repository;

    @BeforeEach
    public void generalSetup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    protected User registerUser(String password) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = EntityBuilder.createRandomUser(encoder.encode(password));
        repository.save(user);
        return user;
    }

    protected String authenticate(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        AuthRequest authRequest = new AuthRequest(username, password);
        final String url = baseURI + "/api/v1/authenticate";
        final AuthResponse response = restTemplate.postForObject(url, authRequest, AuthResponse.class);
        return response.token();
    }
}
