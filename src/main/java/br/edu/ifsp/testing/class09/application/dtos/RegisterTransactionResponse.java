package br.edu.ifsp.testing.class09.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record RegisterTransactionResponse(
        @Schema(description = "ID of the registerd transaction.", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID transactionId,
        @Schema(description = "User ID.", example = "43058600-e29b-41d4-a716-446555344030")
        UUID userId
){ }
