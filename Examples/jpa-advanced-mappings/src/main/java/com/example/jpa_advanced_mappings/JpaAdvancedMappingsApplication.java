package com.example.jpa_advanced_mappings;

import com.example.jpa_advanced_mappings.interfaces.AppDAO;
import com.example.jpa_advanced_mappings.models.Book;
import com.example.jpa_advanced_mappings.models.BookCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaAdvancedMappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedMappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            createBook(appDAO);
        };
    }

    private void createBook(AppDAO appDAO) {
        BookCategory bookCategory = new BookCategory("Religious");
        Book book = new Book("Bible", "God", 89.90f, bookCategory);

        appDAO.save(book);
    }
}