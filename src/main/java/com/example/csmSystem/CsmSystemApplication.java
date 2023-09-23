package com.example.csmSystem;

import com.example.csmSystem.model.auditing.SpringSecurityAuditorAware;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Simple \"Test\" class",
                version = "v1.0",
                contact = @Contact(
                        name = "Adam",
                        email = "adam@adam.com",
                        url = "https://www.urlAddress.com"
                ),
                license = @License(
                        name = "License"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "CSM-System Documentation",
                url = "https://www.urlAddress.com"
        )
)
public class CsmSystemApplication {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    public static void main(String[] args) {
        SpringApplication.run(CsmSystemApplication.class, args);
    }

}

