package br.edu.ifsp.testing.class09.controller;

import br.edu.ifsp.testing.class09.application.dtos.SimpleTransaction;
import br.edu.ifsp.testing.class09.persistence.JpaTransactionRepository;
import br.edu.ifsp.testing.class09.persistence.TransactionEntity;
import br.edu.ifsp.testing.class09.security.user.User;
import br.edu.ifsp.testing.class09.utils.BaseApiIntegrationTest;
import br.edu.ifsp.testing.class09.utils.EntityBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

class TransactionControllerTest extends BaseApiIntegrationTest {

    @Autowired private JpaTransactionRepository repository;

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Should find all transactions between date range")
    void shouldFindAllTransactionsBetweenDateRange() {
        final String plainTextPassword = "change123";
        final User user = registerUser(plainTextPassword);
        final String token = authenticate(user.getUsername(), plainTextPassword);

        final LocalDate begin = LocalDate.of(2025, 1, 1);
        final LocalDate end = LocalDate.of(2025, 1, 31);

        var transactionsToBeFound = EntityBuilder.createRandomTransactions(3, user, begin, end);
        repository.saveAll(transactionsToBeFound);

        final TransactionEntity outOrRangeTransaction = EntityBuilder.createRandomTransaction(user, begin.minusMonths(2), end.minusMonths(2));
        repository.save(outOrRangeTransaction);

        final List<SimpleTransaction> response =
            given()
                    .contentType("application/json")
                    .port(port)
                    .header("Authorization", "Bearer " + token)
                    .queryParam("begin", begin.toString())
                    .queryParam("end", end.toString()).
            when().get("/api/v1/transaction/filtered").
            then()
                    .log().ifValidationFails(LogDetail.BODY)
                    .statusCode(200)
                    .extract().jsonPath().getList("transactions", SimpleTransaction.class);

        final List<UUID> responseIds = response.stream().map(SimpleTransaction::id).toList();
        final List<UUID> expectedIds = transactionsToBeFound.stream().map(TransactionEntity::getId).toList();

        assertThat(responseIds).containsExactlyElementsOf(expectedIds);
    }
}