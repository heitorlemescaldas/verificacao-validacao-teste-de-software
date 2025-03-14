package br.edu.ifsp.testing.class09.application.dtos;

import br.edu.ifsp.testing.class09.domain.Category;
import br.edu.ifsp.testing.class09.domain.Type;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record RegisterTransactionRequest(
        @Schema(description = "Description", example = "Sushi dinner")
        String description,
        @Schema(description = "Date", example = "2024-10-01")
        LocalDate date,
        @Schema(description = "Positive amount value", example = "123.25")
        double amount,
        @Schema(description = "Category", example = "FOOD", allowableValues = {
                "SALARY", "INVESTMENTS", "EDUCATION", "PETS", "FOOD",
                "TRANSPORTATION", "ENTERTAINMENT", "HEALTH"})
        Category category,
        @Schema(description = "Transation type", example = "EXPENSE", allowableValues = {"EXPENSE", "INCOME"})
        Type type
) { }
