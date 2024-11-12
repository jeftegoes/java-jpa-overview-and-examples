package com.example.real_jpa_entity_relationships.repositories;

import com.example.real_jpa_entity_relationships.models.Subject;
import com.example.real_jpa_entity_relationships.models.Student;
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


    public List<Subject> getAll() {
        TypedQuery<Subject> query = entityManager.createQuery("SELECT d FROM Discipline d", Subject.class);

        return query.getResultList();
    }

    public Subject findById(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Transactional
    public void save(int disciplineId, int studentId) {
        Subject subject = entityManager.find(Subject.class, disciplineId);
        Student student = entityManager.find(Student.class, studentId);

//        student.addDiscipline(discipline);

        entityManager.persist(subject);
    }

    @Transactional
    public void delete(int id) {
        Subject subject = entityManager.find(Subject.class, id);

        entityManager.remove(subject);
    }

    @Transactional
    public void update(int id, Subject subject) {
        Subject subjectInDb = entityManager.find(Subject.class, id);
        Teacher teacher = entityManager.find(Teacher.class, subject.getTeacher().getId());

        subjectInDb.setName(subject.getName());
        subjectInDb.setHours(subject.getHours());
        subjectInDb.setTeacher(teacher);

        entityManager.merge(subjectInDb);
    }
}
