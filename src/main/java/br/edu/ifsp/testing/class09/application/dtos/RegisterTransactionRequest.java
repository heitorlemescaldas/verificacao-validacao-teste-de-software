package br.edu.ifsp.testing.class09.application.dtos;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;
import java.time.LocalDate;

public record RegisterTransactionRequest(
        String description,
        LocalDate date,
        double amount,
        Category category,
        Type type
) { }
