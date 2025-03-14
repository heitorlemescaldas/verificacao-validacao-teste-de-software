package br.edu.ifsp.testing.class09.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record DeleteTransactionResponse(
        @Schema(description = "ID of the removed transaction.", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID transactionId
) { }
