package com.example.jpa.first_example_jpa.interfaces;

import com.example.jpa.first_example_jpa.entities.Book;

import java.util.List;

public interface BookDAO {
    void save(Book book);

    Book findById(int id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void update(int id, Book book);

    int delete(int id);

    int deleteAll();
}
