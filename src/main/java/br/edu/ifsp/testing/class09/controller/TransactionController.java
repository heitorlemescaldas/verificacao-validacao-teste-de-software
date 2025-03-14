package br.edu.ifsp.testing.class09.controller;

import br.edu.ifsp.testing.class09.application.dtos.*;
import br.edu.ifsp.testing.class09.application.services.BalanceService;
import br.edu.ifsp.testing.class09.application.services.FindTransactionsService;
import br.edu.ifsp.testing.class09.application.services.RegisterTransactionService;
import br.edu.ifsp.testing.class09.application.services.DeleteTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/transaction")
@AllArgsConstructor
@Tag(name = "Transaction API")
public class TransactionController {

    private final RegisterTransactionService registerTransactionService;
    private final FindTransactionsService findTransactionsService;
    private final DeleteTransactionService deleteTransactionService;
    private final BalanceService balanceService;

    @Operation(
            summary = "Register a new transaction for an authenticated user.",
            description = "Returns the user ID and the ID of the registered transaction."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Successful operation.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterTransactionResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated.",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @PostMapping
    public ResponseEntity<?> registerTransaction(@RequestBody RegisterTransactionRequest request) {
        final RegisterTransactionResponse response = registerTransactionService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all transactions of an authenticated user.",
            description = "Returns the id of an authenticated user along with all of its transactions."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Successful operation.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FindTransactionsResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated.",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @GetMapping
    public ResponseEntity<?> findTransactions() {
        final FindTransactionsResponse response = findTransactionsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Get the transactions of an authenticated user that match a date range filter.",
            description = "Returns the id of an authenticated user along with all of its transactions within a date range."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Successful operation.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FindTransactionsResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated.",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @GetMapping(value = "/filtered")
    public ResponseEntity<?> findTransactionsByDateRange(
            @Parameter(example = "2024-01-01")
            @RequestParam(required = false) LocalDate begin,
            @Parameter(example = "2024-01-31")
            @RequestParam(required = false) LocalDate end
    ) {
        final FindTransactionsResponse response = findTransactionsService.findByPeriod(begin, end);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a transaction by id.",
            description = "Delete a transaction of an authenticated user."

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Successful operation.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeleteTransactionResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated.",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transaction to be delete not found.",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTransaction(
            @Parameter(
                    description = "ID of the request to be deleted",
                    required = true,
                    example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id
    ) {
        final DeleteTransactionResponse response = deleteTransactionService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Calculate the balance of a set of transactions within a date range.",
            description = "Returns the balance of a set of transactions within a date range, " +
                          "the number of transactions used in the calculation, and the user ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Successful operation.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BalanceResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated.",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @GetMapping("/balance")
    public ResponseEntity<?> calculateBalance(
            @Parameter(example = "2024-01-01")
            @RequestParam(required = false) LocalDate begin,
            @Parameter(example = "2024-01-31")
            @RequestParam(required = false) LocalDate end
    ) {
        final BalanceResponse response = balanceService.calculate(begin, end);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
