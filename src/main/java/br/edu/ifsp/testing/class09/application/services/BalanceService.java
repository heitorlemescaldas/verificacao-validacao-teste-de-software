package br.edu.ifsp.testing.class09.application.services;

import br.edu.ifsp.testing.class09.application.dtos.BalanceResponse;
import br.edu.ifsp.testing.class09.domain.BalanceCalculator;
import br.edu.ifsp.testing.class09.domain.Transaction;
import br.edu.ifsp.testing.class09.security.auth.AuthenticationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AuthenticationInfoService authInfoService;
    private final TransactionRepository transactionRepository;

    public BalanceResponse calculate(LocalDate begin, LocalDate end) {
        final UUID userId = authInfoService.getAuthenticatedUserId();

        final List<Transaction> transactions = transactionRepository
                .findAllByUserIdAndDateRange(userId, begin, end)
                .stream()
                .toList();

        BalanceCalculator calculator = new BalanceCalculator();
        var balance = calculator.calculate(transactions);
        var transactionCount = transactions.size();

        return new BalanceResponse(userId, balance, transactionCount);
    }
}
