package br.edu.ifsp.testing.class09.application.services;

import br.edu.ifsp.testing.class09.application.dtos.FindTransactionsResponse;
import br.edu.ifsp.testing.class09.application.dtos.SimpleTransaction;
import br.edu.ifsp.testing.class09.domain.Transaction;
import br.edu.ifsp.testing.class09.security.auth.AuthenticationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindTransactionsService {

    private final AuthenticationInfoService authInfoService;
    private final TransactionRepository transactionRepository;

    public FindTransactionsResponse findAll() {
        final UUID userId = authInfoService.getAuthenticatedUserId();

        final List<SimpleTransaction> transactions = transactionRepository
                .findAllByUserId(userId)
                .stream()
                .map(this::toSimpleTransaction)
                .toList();

        return new FindTransactionsResponse(userId, transactions);
    }

    public FindTransactionsResponse findByPeriod(LocalDate begin, LocalDate end) {
        final UUID userId = authInfoService.getAuthenticatedUserId();

        final List<SimpleTransaction> transactions = transactionRepository
                .findAllByUserIdAndDateRange(userId, begin, end)
                .stream()
                .map(this::toSimpleTransaction)
                .toList();

        return new FindTransactionsResponse(userId, transactions);
    }

    private SimpleTransaction toSimpleTransaction(Transaction transaction){
        return new SimpleTransaction(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getType()
        );
    }
}
