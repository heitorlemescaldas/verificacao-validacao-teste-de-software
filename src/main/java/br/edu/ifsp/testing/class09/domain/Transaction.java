package br.edu.ifsp.testing.class09.domain;

import br.edu.ifsp.testing.class09.security.user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final String description;
    private final LocalDate date;
    private final double amount;
    private final Category category;
    private final Type type;

    private final User transactionOwner;

    public Transaction(
            User transactionOwner,
            UUID id,
            String description,
            LocalDate date,
            double amount,
            Category category,
            Type type
    ) {
        this.transactionOwner = Objects.requireNonNull(transactionOwner);
        this.id = Objects.requireNonNull(id);
        this.description = isValidOrThrow(description);
        this.date = Objects.requireNonNull(date);
        this.amount = isPositiveOrThrow(amount) ;
        this.category = Objects.requireNonNull(category);
        this.type = Objects.requireNonNull(type);
    }

    private double isPositiveOrThrow(double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Value must be greater than zero: " + amount);
        return amount;
    }

    private String isValidOrThrow(String description) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank: " + description);
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + id +
               ", description='" + description + '\'' +
               ", amount=" + amount +
               ", category=" + category +
               ", type=" + type +
               '}';
    }

    public User getUser() {
        return transactionOwner;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getNetAmount() {
        return type == Type.INCOME? amount: -amount;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }
}
