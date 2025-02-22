package br.edu.ifsp.testing.class09.persistence;

import br.edu.ifsp.testing.class09.application.services.TransactionRepository;
import br.edu.ifsp.testing.class09.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private JpaTransactionRepository repository;

    @Override
    public void save(Transaction transaction) {
        repository.save(toEntity(transaction));
    }

    @Override
    public Optional<Transaction> findById(UUID transactionId) {
        return repository.findById(transactionId).map(this::fromEntity);
    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId) {
        return repository
                .findAllByUserId(userId.toString())
                .stream()
                .map(this::fromEntity)
                .toList();
    }

    public List<Transaction> findAllByUserIdAndDateRange(UUID userId, LocalDate begin, LocalDate end) {
        return repository
                .findByUserIdAndDateRange(userId.toString(), begin, end)
                .stream()
                .map(this::fromEntity)
                .toList();
    }

    @Override
    public void delete(UUID userId, UUID transactionId) {
        repository.deleteByUserIdAndId(userId, transactionId);
    }

    private TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .user(transaction.getUser())
                .description(transaction.getDescription())
                .date(transaction.getDate())
                .category(transaction.getCategory())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .build();
    }

    private Transaction fromEntity(TransactionEntity entity) {
        return new Transaction(
                entity.getUser(),
                entity.getId(),
                entity.getDescription(),
                entity.getDate(),
                entity.getAmount(),
                entity.getCategory(),
                entity.getType()
        );
    }
}
