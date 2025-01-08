package com.example.applications;

import com.example.models.Book;
import com.example.repositories.BookEntityManagerRepository;
import com.example.repositories.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BookApplication {
    private final BookRepository bookRepository;
    private final BookEntityManagerRepository bookEntityManagerRepository;

    public BookApplication(BookRepository bookRepository, BookEntityManagerRepository bookEntityManagerRepository) {
        this.bookRepository = bookRepository;
        this.bookEntityManagerRepository = bookEntityManagerRepository;
    }

    public void normalInsert() {
        this.bookRepository.deleteAll();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Book> books = readBooksFromFile("books.txt");

        for (Book book : books) {
            this.bookRepository.save(book);
        }
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    public void bulkInsert() {
        this.bookRepository.deleteAll();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Book> books = readBooksFromFile("books.txt");

        this.bookRepository.saveAll(books);

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    public void bulkInsert2() {
        this.bookRepository.deleteAll();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Book> books = readBooksFromFile("books.txt");

        List<Book> productDataTemp = new ArrayList<>();
        int counter = 0;
        for(Book product: books){
            productDataTemp.add(product);
            if((counter + 1) % 1000 == 0 || (counter + 1) == books.size()){
                this.bookRepository.saveAll(productDataTemp);
                productDataTemp.clear();
            }
            counter++;
        }

        this.bookRepository.saveAll(books);

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    public void bulkInsertByEntityManager() {
        this.bookRepository.deleteAll();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Book> books = readBooksFromFile("books.txt");

        this.bookEntityManagerRepository.bulkInsertBooks(books);

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }


    private List<Book> readBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    float price = Float.parseFloat(parts[2]);

                    books.add(new Book(new Random().nextInt(Integer.MAX_VALUE), title, author, price));
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
        return books;
    }
}
