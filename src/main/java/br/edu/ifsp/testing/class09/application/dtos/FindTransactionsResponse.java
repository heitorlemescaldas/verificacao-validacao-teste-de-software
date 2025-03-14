package br.edu.ifsp.testing.class09.application.dtos;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "User transactions")
public record FindTransactionsResponse(
        @Schema(description = "User ID", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID userId,
        @ArraySchema(schema = @Schema(implementation = SimpleTransaction.class))
        @Schema(description = "List of transactions")
        List<SimpleTransaction> transactions
) { }
