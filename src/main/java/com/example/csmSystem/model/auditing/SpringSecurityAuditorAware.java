package com.example.csmSystem.model.auditing;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.ofNullable("Adam").filter(s -> !s.isEmpty());
    }

}
