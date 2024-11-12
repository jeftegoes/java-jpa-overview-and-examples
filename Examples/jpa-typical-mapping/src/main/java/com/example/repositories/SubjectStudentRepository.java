package com.example.repositories;

import com.example.entities.Student;
import com.example.entities.Subject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectStudentRepository {
    private final EntityManager entityManager;

    @Autowired
    public SubjectStudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(int studentId, int subjectId) {
        Subject subject = entityManager.find(Subject.class, subjectId);
        Student student = entityManager.find(Student.class, studentId);

        student.addSubjects(subject);

        entityManager.persist(subject);
    }
}
