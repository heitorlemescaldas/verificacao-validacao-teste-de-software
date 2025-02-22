package br.edu.ifsp.testing.class09.controller;

import br.edu.ifsp.testing.class09.application.dtos.*;
import br.edu.ifsp.testing.class09.application.services.BalanceService;
import br.edu.ifsp.testing.class09.application.services.FindTransactionsService;
import br.edu.ifsp.testing.class09.application.services.RegisterTransactionService;
import br.edu.ifsp.testing.class09.application.services.DeleteTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final RegisterTransactionService registerTransactionService;
    private final FindTransactionsService findTransactionsService;
    private final DeleteTransactionService deleteTransactionService;
    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<?> registerTransaction(@RequestBody RegisterTransactionRequest request) {
        final RegisterTransactionResponse response = registerTransactionService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findTransactions() {
        final FindTransactionsResponse response = findTransactionsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/filtered")
    public ResponseEntity<?> findTransactionsByDateRange(
            @RequestParam(required = false) LocalDate begin,
            @RequestParam(required = false) LocalDate end
    ) {
        final FindTransactionsResponse response = findTransactionsService.findByPeriod(begin, end);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTransaction(@PathVariable UUID id) {
        final DeleteTransactionResponse response = deleteTransactionService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> calculateBalance(
            @RequestParam(required = false) LocalDate begin,
            @RequestParam(required = false) LocalDate end
    ) {
        final BalanceResponse response = balanceService.calculate(begin, end);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
