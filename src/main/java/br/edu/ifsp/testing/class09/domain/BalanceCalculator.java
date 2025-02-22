package br.edu.ifsp.testing.class09.domain;

import java.util.List;

public class BalanceCalculator {
    public double calculate(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getNetAmount).sum();
    }
}
