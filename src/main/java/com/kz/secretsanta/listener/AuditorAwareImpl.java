package com.kz.secretsanta.listener;

import com.kz.secretsanta.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditorAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        var user = (User) authentication.getPrincipal();
        return Optional.of(user.getId());
    }

}
