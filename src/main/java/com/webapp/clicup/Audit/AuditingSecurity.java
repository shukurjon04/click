package com.webapp.clicup.Audit;

import com.webapp.clicup.Entity.user;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditingSecurity implements AuditorAware<user> {
    @Override
    public Optional<user> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of((user) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
