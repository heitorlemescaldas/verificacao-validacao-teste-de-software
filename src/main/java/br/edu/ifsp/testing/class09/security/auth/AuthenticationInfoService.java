package br.edu.ifsp.testing.class09.security.auth;

import br.edu.ifsp.testing.class09.security.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationInfoService {
    public UUID getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            throw new IllegalStateException("Unauthorized user request.");
        var applicationUser = (User) authentication.getPrincipal();
        return applicationUser.getId();
    }
}