package com.example.jpa.first_example_jpa.configs;

import com.example.jpa.first_example_jpa.entities.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    private final Credential credential;

    @Autowired
    public DataSourceConfig(Credential credential) {
        this.credential = credential;
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(credential.getUrl());
        dataSourceBuilder.username(credential.getUsername());
        dataSourceBuilder.password(credential.getPassword());
        return dataSourceBuilder.build();
    }
}
