package com.example.jpa.first_example_jpa.dao;

import com.example.jpa.first_example_jpa.entities.Book;
import com.example.jpa.first_example_jpa.interfaces.BookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookImpl implements BookDAO {

    private EntityManager entityManager;

    @Autowired
    public BookImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select s FROM Book s ORDER BY s.name ASC", Book.class).getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery("select s FROM Book s WHERE s.name LIKE :name", Book.class);

        query.setParameter("name", "%" + name + "%");

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, Book book) {
        Book bookInDb = entityManager.find(Book.class, id);

        bookInDb.setName(book.getName());
        bookInDb.setAuthor(book.getAuthor());
        bookInDb.setPrice(book.getPrice());

        entityManager.merge(bookInDb);
    }

    @Override
    @Transactional
    public int delete(int id) {
        Query query = entityManager.createQuery("DELETE FROM Book WHERE id = :id");

        query.setParameter("id", id);

        return query.executeUpdate();
    }

    @Override
    @Transactional
    public int deleteAll() {
        Query query = entityManager.createQuery("DELETE FROM Book");

        return query.executeUpdate();
    }
}
