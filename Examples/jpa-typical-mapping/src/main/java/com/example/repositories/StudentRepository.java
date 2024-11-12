package com.example.repositories;

import com.example.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Student> getAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);

        return query.getResultList();
    }

    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Transactional
    public void delete(int id) {
        Student student = entityManager.find(Student.class, id);

        entityManager.remove(student);
    }

    @Transactional
    public void update(int id, Student student) {
        Student studentInDb = entityManager.find(Student.class, id);

        studentInDb.setName(student.getName());

        entityManager.merge(studentInDb);
    }
}
