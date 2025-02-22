package br.edu.ifsp.testing.class09.security.auth;

import br.edu.ifsp.testing.class09.exception.EntityAlreadyExistsException;
import br.edu.ifsp.testing.class09.security.config.JwtService;
import br.edu.ifsp.testing.class09.security.user.Role;
import br.edu.ifsp.testing.class09.security.user.User;
import br.edu.ifsp.testing.class09.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {

        userRepository.findByEmail(request.email()).ifPresent(unused -> {
            throw new EntityAlreadyExistsException("Email already registered: " + request.email());});

        String encryptedPassword = passwordEncoder.encode(request.password());

        final UUID id = UUID.randomUUID();
        final User user = User.builder()
                .id(id)
                .name(request.name())
                .lastname(request.lastname())
                .email(request.email())
                .password(encryptedPassword)
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return new RegisterResponse(id);
    }

    public AuthResponse authenticate(AuthRequest request) {
        final var authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(authentication);

        final User user = userRepository.findByEmail(request.username()).orElseThrow();
        final String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
