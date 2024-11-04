package com.example.interfaces;

import com.example.models.Course;
import com.example.models.Teacher;
import com.example.models.TeacherDetail;

import java.util.List;

public interface AppDAO {
    void saveTeacher(Teacher teacher);

    Teacher findTeacherById(int id);

    void deleteTeacherById(int id);

    TeacherDetail findTeacherDetailById(int id);

    void deleteTeacherDetailById(int id);

    List<Course> findCoursesByTeacherId(int id);

    Teacher findTeacherByIdJoinFetch(int id);

    void update(Teacher teacher);

    Course findCourseById(int id);

    void updateCourse(Course course);

    void deleteCourse(int id);

    void saveCourse(Course course);
    Course findCourseAndReviewsByCourseId(int id);
}
