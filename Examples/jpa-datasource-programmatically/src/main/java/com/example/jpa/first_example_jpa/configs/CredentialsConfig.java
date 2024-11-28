package com.example.jpa.first_example_jpa.configs;

import com.example.jpa.first_example_jpa.entities.Credential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CredentialsConfig {
    @Bean
    public Credential getCredential() {
        return new Credential("root", "Master@123", "jdbc:mysql://localhost:3306/library");
    }
}