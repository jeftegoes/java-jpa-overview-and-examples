package com.example.jpa.first_example_jpa.dao;

import com.example.jpa.first_example_jpa.entities.Student;
import com.example.jpa.first_example_jpa.interfaces.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("select s FROM Student s ORDER BY s.firstName ASC", Student.class).getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("select s FROM Student s WHERE s.lastName LIKE :lastName", Student.class);

        query.setParameter("lastName", "%" + lastName + "%");

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, Student student) {
        Student studentInDb = entityManager.find(Student.class, id);

        studentInDb.setFirstName(student.getFirstName());
        studentInDb.setLastName(student.getLastName());
        studentInDb.setEmail(student.getEmail());

        entityManager.merge(studentInDb);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Query query = entityManager.createQuery("DELETE FROM Student WHERE id = :id");

        query.setParameter("id", id);

        query.executeUpdate();
    }
}
