package br.edu.ifsp.testing.class09.security.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        final RegisterResponse response = authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        final AuthResponse response = authenticationService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
