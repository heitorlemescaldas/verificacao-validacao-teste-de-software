package br.edu.ifsp.testing.class09.persistence;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;
import br.edu.ifsp.testing.class09.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;
    private String description;
    private LocalDate date;
    private double amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // FK to User
    private User user;
}
