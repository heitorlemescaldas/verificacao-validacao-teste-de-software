package br.edu.ifsp.testing.class09.application.dtos;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Transaction")
public record SimpleTransaction(
        @Schema(description = "ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,
        @Schema(description = "Description", example = "Sushi dinner")
        String description,
        @Schema(description = "Date", example = "2024-10-01")
        LocalDate date,
        @Schema(description = "Positive amount value", example = "123.25")
        double amount,
        @Schema(description = "Category", example = "FOOD")
        Category category,
        @Schema(description = "Transation type", example = "EXPENSE", allowableValues = {"EXPENSE", "INCOME"})
        Type type
) { }
