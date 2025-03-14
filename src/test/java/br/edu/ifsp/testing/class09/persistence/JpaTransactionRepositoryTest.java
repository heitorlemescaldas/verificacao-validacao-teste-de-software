package br.edu.ifsp.testing.class09.persistence;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;
import br.edu.ifsp.testing.class09.security.user.User;
import br.edu.ifsp.testing.class09.security.user.JpaUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // autowire once instance per test class.
class JpaTransactionRepositoryTest {

    @Autowired JpaTransactionRepository sut;
    @Autowired JpaUserRepository userRepository;
    private static UUID userId;

    private TransactionEntity pizza;
    private TransactionEntity salary;
    private TransactionEntity party;

    @BeforeEach
    void setup() {
        userId = randomUUID();
        User user = User.builder().id(userId).name("John").lastname("Snow").email("email@email.com").password("42342").build();
        userRepository.save(user);

        pizza = new TransactionEntity(randomUUID(), "Pizza", LocalDate.of(2025, 1, 25), 79.99, Category.FOOD, Type.EXPENSE, user);
        salary = new TransactionEntity(randomUUID(), "Salary", LocalDate.of(2025, 1, 5), 3500.00, Category.SALARY, Type.INCOME, user);
        party = new TransactionEntity(randomUUID(), "Party", LocalDate.of(2025, 1, 27), 125.00, Category.ENTERTAINMENT, Type.EXPENSE, user);

        sut.saveAll(List.of(pizza, salary, party));
    }

    @AfterEach
    void tearDown() {
        sut.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find no transaction of a different user")
    void shouldFindNoTransactionOfADifferentUser() {
        final var result = sut.findByUserIdAndDateRange(randomUUID().toString(), LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should find all transactions inside the range")
    void shouldFindAllTransactionsInsideRange() {
        final var result = sut.findByUserIdAndDateRange(userId.toString(), LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        assertThat(result).contains(pizza, salary, party);
    }
}