package com.example.dao;

import com.example.interfaces.AppDAO;
import com.example.models.Course;
import com.example.models.Teacher;
import com.example.models.TeacherDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public Teacher findTeacherById(int id) {
        Teacher teacher = entityManager.find(Teacher.class, id);

        return teacher;
    }

    @Override
    public TeacherDetail findTeacherDetailById(int id) {
        return entityManager.find(TeacherDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteTeacherDetailById(int id) {
        TeacherDetail teacherDetail = entityManager.find(TeacherDetail.class, id);

        teacherDetail.getTeacher().setTeacherDetail(null);

        entityManager.remove(teacherDetail);
    }

    @Override
    public List<Course> findCoursesByTeacherId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE teacher.id = :data", Course.class);

        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Teacher findTeacherByIdJoinFetch(int id) {
        TypedQuery<Teacher> query = entityManager.createQuery(
                " SELECT t FROM Teacher t " +
                        " JOIN FETCH t.courses " +
                        " JOIN FETCH t.teacherDetail " +
                        " WHERE t.id = :data", Teacher.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteTeacherById(int id) {
        Teacher teacher = entityManager.find(Teacher.class, id);

        List<Course> courses = teacher.getCourses();

        for (Course course : courses) {
            course.setTeacher(null);
        }

        entityManager.remove(teacher);
    }
}