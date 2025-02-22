package br.edu.ifsp.testing.class09.application.dtos;

import java.util.List;
import java.util.UUID;

public record FindTransactionsResponse(UUID userId, List<SimpleTransaction> transactions) { }
