package br.edu.ifsp.testing.class09.application.dtos;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;

import java.time.LocalDate;
import java.util.UUID;

public record SimpleTransaction(
        UUID id,
        String description,
        LocalDate date,
        double amount,
        Category category,
        Type type
) { }
