package br.edu.ifsp.testing.class09.utils;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Transaction;
import br.edu.ifsp.testing.class09.domain.Type;
import br.edu.ifsp.testing.class09.persistence.TransactionEntity;
import br.edu.ifsp.testing.class09.security.user.Role;
import br.edu.ifsp.testing.class09.security.user.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EntityBuilder {

    private static final Faker faker = Faker.instance();

    public static User createRandomUser(String password) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(faker.name().firstName())
                .lastname(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(password)
                .role(Role.USER)
                .build();
    }

    public static TransactionEntity createRandomTransaction(User user, LocalDate begin, LocalDate end) {
        Date beginDate = Date.from(begin.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date randomDateBetween = faker.date().between(beginDate, endDate);
        final LocalDate transactionDate = randomDateBetween.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new TransactionEntity(
                UUID.randomUUID(),
                faker.commerce().productName(),
                transactionDate,
                Math.abs(faker.random().nextDouble()),
                faker.options().option(Category.class),
                faker.options().option(Type.class),
                user
        );
    }

    public static List<TransactionEntity> createRandomTransactions(int numberOfTransactions, User user, LocalDate begin, LocalDate end) {
        List<TransactionEntity> transactions = new ArrayList<>();
        for (int i = 0; i < numberOfTransactions; i++) {
            transactions.add(createRandomTransaction(user, begin, end));
        }
        return transactions;
    }
}
