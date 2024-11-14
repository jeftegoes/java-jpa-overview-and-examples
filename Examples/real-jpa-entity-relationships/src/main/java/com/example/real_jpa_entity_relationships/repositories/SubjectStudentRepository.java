package com.example.real_jpa_entity_relationships.repositories;

import com.example.real_jpa_entity_relationships.models.Subject;
import com.example.real_jpa_entity_relationships.models.Student;
import com.example.real_jpa_entity_relationships.models.SubjectStudent;
import com.example.real_jpa_entity_relationships.models.Teacher;
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
        Subject subject = entityManager.find(Subject.class, subjectStudent.getSubject().getId());
        Student student = entityManager.find(Student.class, subjectStudent.getStudent().getId());

        subjectStudentInDb.setSubject(subject);
        subjectStudentInDb.setStudent(student);

        entityManager.merge(subjectStudentInDb);
    }
}
