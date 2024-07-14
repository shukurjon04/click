package com.webapp.clicup.Audit;

import com.webapp.clicup.Entity.user;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<user> userAuditorAware(){
        return new AuditingSecurity();
    }
}
