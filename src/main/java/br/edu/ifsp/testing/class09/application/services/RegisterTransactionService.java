package br.edu.ifsp.testing.class09.application.services;

import br.edu.ifsp.testing.class09.application.dtos.RegisterTransactionRequest;
import br.edu.ifsp.testing.class09.application.dtos.RegisterTransactionResponse;
import br.edu.ifsp.testing.class09.domain.Transaction;
import br.edu.ifsp.testing.class09.security.auth.AuthenticationInfoService;
import br.edu.ifsp.testing.class09.security.user.User;
import br.edu.ifsp.testing.class09.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterTransactionService {

    private final AuthenticationInfoService authInfoService;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public RegisterTransactionResponse register(RegisterTransactionRequest request) {
        final UUID transactionId = UUID.randomUUID();
        final UUID userId = authInfoService.getAuthenticatedUserId();

        final User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        final Transaction transaction = new Transaction(
                user,
                transactionId,
                request.description(),
                request.date(),
                request.amount(),
                request.category(),
                request.type()
        );

        transactionRepository.save(transaction);
        return new RegisterTransactionResponse(transactionId, userId);
    }
}
