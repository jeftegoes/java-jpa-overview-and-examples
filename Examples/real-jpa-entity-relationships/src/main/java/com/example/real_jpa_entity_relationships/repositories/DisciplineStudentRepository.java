package com.example.real_jpa_entity_relationships.repositories;

import com.example.real_jpa_entity_relationships.models.Discipline;
import com.example.real_jpa_entity_relationships.models.Student;
import com.example.real_jpa_entity_relationships.models.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DisciplineStudentRepository {
    private final EntityManager entityManager;

    @Autowired
    public DisciplineStudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Discipline> getAll() {
        TypedQuery<Discipline> query = entityManager.createQuery("SELECT d FROM Discipline d", Discipline.class);

        return query.getResultList();
    }

    public Discipline findById(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Transactional
    public void save(int disciplineId, int studentId) {
        Discipline discipline = entityManager.find(Discipline.class, disciplineId);
        Student student = entityManager.find(Student.class, studentId);

//        student.addDiscipline(discipline);

        entityManager.persist(discipline);
    }

    @Transactional
    public void delete(int id) {
        Discipline discipline = entityManager.find(Discipline.class, id);

        entityManager.remove(discipline);
    }

    @Transactional
    public void update(int id, Discipline discipline) {
        Discipline disciplineInDb = entityManager.find(Discipline.class, id);
        Teacher teacher = entityManager.find(Teacher.class, discipline.getTeacher().getId());

        disciplineInDb.setName(discipline.getName());
        disciplineInDb.setHours(discipline.getHours());
        disciplineInDb.setTeacher(teacher);

        entityManager.merge(disciplineInDb);
    }
}
