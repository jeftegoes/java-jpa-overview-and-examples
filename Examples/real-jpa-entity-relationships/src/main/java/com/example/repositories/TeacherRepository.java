package com.example.repositories;

import com.example.models.Teacher;
import com.example.models.TeacherDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {
    private final EntityManager entityManager;

    @Autowired
    public TeacherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Teacher> getAll() {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class);

        return query.getResultList();
    }

    public Teacher findById(int id) {
        return entityManager.find(Teacher.class, id);
    }

    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Transactional
    public void delete(int id) {
        Teacher teacher = entityManager.find(Teacher.class, id);

        entityManager.remove(teacher);
    }

    @Transactional
    public void update(int id, Teacher teacher) {
        Teacher teacherInDb = entityManager.find(Teacher.class, id);

        teacherInDb.setFirstName(teacher.getFirstName());
        teacherInDb.setLastName(teacher.getLastName());
        teacherInDb.setEmail(teacher.getEmail());

        TeacherDetail teacherDetailInDb = teacherInDb.getTeacherDetail();
        teacherDetailInDb.setLinkedin(teacher.getTeacherDetail().getLinkedin());
        teacherDetailInDb.setHobby(teacher.getTeacherDetail().getHobby());

        entityManager.merge(teacherInDb);
    }
}
