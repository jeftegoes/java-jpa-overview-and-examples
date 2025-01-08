package com.example.repositories;

import com.example.models.Book;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookEntityManagerRepository {
    private EntityManager entityManager;

    public BookEntityManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void bulkInsertBooks(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            entityManager.persist(books.get(i));
        }
    }
}
