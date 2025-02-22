package br.edu.ifsp.testing.class09.application.services;

import br.edu.ifsp.testing.class09.application.dtos.DeleteTransactionResponse;
import br.edu.ifsp.testing.class09.security.auth.AuthenticationInfoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteTransactionService {

    private final AuthenticationInfoService authInfoService;
    private final TransactionRepository transactionRepository;

    public DeleteTransactionResponse delete(UUID transactionId) {
        Objects.requireNonNull(transactionId, "Transaction must not be null.");

        transactionRepository
                .findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found: " + transactionId));

        final UUID userId = authInfoService.getAuthenticatedUserId();
        transactionRepository.delete(userId, transactionId);
        return new DeleteTransactionResponse(transactionId);
    }
}
