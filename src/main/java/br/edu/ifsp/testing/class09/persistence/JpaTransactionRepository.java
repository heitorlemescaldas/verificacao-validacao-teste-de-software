package br.edu.ifsp.testing.class09.persistence;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    @Query(value = "SELECT * FROM transactions WHERE user_id = :userId", nativeQuery = true)
    List<TransactionEntity> findAllByUserId(@Param("userId") String userId);

    @Transactional
    void deleteByUserIdAndId(UUID userId, UUID transactionId);

    @Query(value = """
    SELECT * FROM transactions
    WHERE user_id = :userId
    AND (:begin IS NULL OR transactions.date >= :begin)
    AND (:end IS NULL OR transactions.date <= :end)
    """, nativeQuery = true)
    List<TransactionEntity> findByUserIdAndDateRange(
            @Param("userId") String userId,
            @Param("begin") LocalDate begin,
            @Param("end") LocalDate end
    );
}
