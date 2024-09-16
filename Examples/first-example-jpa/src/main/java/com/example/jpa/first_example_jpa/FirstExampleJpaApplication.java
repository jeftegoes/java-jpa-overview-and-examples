package com.example.jpa.first_example_jpa;

import com.example.jpa.first_example_jpa.entities.Book;
import com.example.jpa.first_example_jpa.interfaces.BookDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FirstExampleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstExampleJpaApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(BookDAO bookDAO) {
        return runner -> {
//            createBook(bookDAO);
//            readBook(bookDAO);
//            readAllBook(bookDAO);
//            readBookByName(bookDAO);
//            updateBook(2, bookDAO);
//            deleteBook(2, bookDAO);
            deleteAllBook(bookDAO);
        };
    }

    private void deleteBook(int id, BookDAO bookDAO) {
        System.out.println("Deleting book data.");
        bookDAO.delete(id);
        System.out.println("Book deleted!");
    }

    private void deleteAllBook(BookDAO bookDAO) {
        bookDAO.deleteAll();
    }

    private void updateBook(int id, BookDAO bookDAO) {
        bookDAO.update(id, new Book("Bible", "Jesus", 159.90f));
    }

    private void readBookByName(BookDAO bookDAO) {
        List<Book> books = bookDAO.findByName("Bi");
        books.forEach(s -> {
            System.out.println(s);
        });
    }

    private void readAllBook(BookDAO bookDAO) {
        List<Book> books = bookDAO.findAll();
        books.forEach(s -> {
            System.out.println(s);
        });
    }

    private void readBook(BookDAO bookDAO) {
        Book book = bookDAO.findById(1);

        System.out.println("First name: " + book);
    }

    private void createBook(BookDAO bookDAO) {
        System.out.println("Creating new Book object...");
        Book book1 = new Book("Bible", "Jesus", 200);
        Book book2 = new Book("The Imitation of Christ", "Thomas", 50.90f);

        System.out.println("Saving Book...");
        bookDAO.save(book1);
        bookDAO.save(book2);

        System.out.println("Saved Book! Generated ID: " + book1.getId());
        System.out.println("Saved Book! Generated ID: " + book2.getId());
    }
}
