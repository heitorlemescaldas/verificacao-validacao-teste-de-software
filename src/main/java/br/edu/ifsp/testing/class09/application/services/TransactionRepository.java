package br.edu.ifsp.testing.class09.application.services;

import br.edu.ifsp.testing.class09.domain.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    void save(Transaction transaction);
    Optional<Transaction> findById(UUID transactionId);
    List<Transaction> findAllByUserId(UUID userId);
    List<Transaction> findAllByUserIdAndDateRange(UUID userId, LocalDate begin, LocalDate end);
    void delete(UUID userId, UUID transactionId);
}
