package br.edu.ifsp.testing.class09.application.dtos;

import java.util.UUID;

public record BalanceResponse (UUID userId, double balance, int transactions) { }
