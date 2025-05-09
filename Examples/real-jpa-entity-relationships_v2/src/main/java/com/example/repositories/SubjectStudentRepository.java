package com.example.repositories;

import com.example.models.Subject;
import com.example.models.Student;
import com.example.models.SubjectStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public List<SubjectStudent> getAll() {
        TypedQuery<SubjectStudent> query = entityManager.createQuery("SELECT d FROM SubjectStudent d", SubjectStudent.class);

        return query.getResultList();
    }

    public SubjectStudent findById(int id) {
        return entityManager.find(SubjectStudent.class, id);
    }

    @Transactional
    public void save(SubjectStudent subjectStudent) {
        entityManager.persist(subjectStudent);
    }

    @Transactional
    public void delete(int id) {
        SubjectStudent subject = entityManager.find(SubjectStudent.class, id);

        entityManager.remove(subject);
    }

    @Transactional
    public void update(int id, SubjectStudent subjectStudent) {
        SubjectStudent subjectStudentInDb = entityManager.find(SubjectStudent.class, id);

        subjectStudentInDb.setStudentId(subjectStudent.getStudentId());
        subjectStudentInDb.setSubjectId(subjectStudent.getSubjectId());

        entityManager.merge(subjectStudentInDb);
    }
}
