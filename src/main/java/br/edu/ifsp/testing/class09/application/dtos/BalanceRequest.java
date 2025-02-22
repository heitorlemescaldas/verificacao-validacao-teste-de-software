package br.edu.ifsp.testing.class09.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record BalanceRequest(LocalDate begin, LocalDate end) { }
