package br.edu.ifsp.testing.class09.application.dtos;

import java.util.UUID;

public record RegisterTransactionResponse (UUID transactionId, UUID userId){ }
