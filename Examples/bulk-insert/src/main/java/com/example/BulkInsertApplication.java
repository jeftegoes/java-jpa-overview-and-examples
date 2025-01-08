package com.example;

import com.example.applications.BookApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BulkInsertApplication {
    private final BookApplication bookApplication;

    public BulkInsertApplication(BookApplication bookApplication) {
        this.bookApplication = bookApplication;
    }

    public static void main(String[] args) {
        SpringApplication.run(BulkInsertApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner -> {
//            this.bookApplication.bulkInsert();
//            this.bookApplication.bulkInsert2();
            this.bookApplication.bulkInsertByEntityManager();
        };
    }

}
