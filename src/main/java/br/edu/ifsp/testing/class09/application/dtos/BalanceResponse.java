package br.edu.ifsp.testing.class09.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record BalanceResponse (
        @Schema(description = "User ID.", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @Schema(description = "Transactions balance.", example = "342.49")
        double balance,
        @Schema(description = "Number of transactions considered in the balance.", example = "5")
        int transactions
) { }
