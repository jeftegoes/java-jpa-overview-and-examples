package com.example;

import com.example.interfaces.AppDAO;
import com.example.models.Course;
import com.example.models.Review;
import com.example.models.Teacher;
import com.example.models.TeacherDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaAdvancedMappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedMappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
//            createTeacher(appDAO);
//            findTeacherById(appDAO);
//            removeTeacherById(appDAO);
//            findTeacherDetailById(appDAO);
//            removeTeacherDetailById(appDAO);
//            createTeacherWithCourses(appDAO);
//            findTeacherWithCourses(appDAO);
//            findTeacherForCourses(appDAO);
//            findTeacherWithCoursesjoinFetch(appDAO);
//            updateTeacher(appDAO);
//            updateCourse(appDAO);
//            deleteCourse(appDAO);
            createCourseAndReviews(appDAO);
        };
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Java");

        course.add(new Review("Nice course!"));
        course.add(new Review("Amazing course!"));

        appDAO.saveCourse(course);
    }

    private void deleteCourse(AppDAO appDAO) {
        int courseId = 2;

        appDAO.deleteCourse(courseId);
    }

    private void updateCourse(AppDAO appDAO) {
        int courseId = 1;

        Course course = appDAO.findCourseById(courseId);

        course.setDescription("JavaScript");

        appDAO.updateCourse(course);
    }

    private void updateTeacher(AppDAO appDAO) {
        int teacherId = 1;

        Teacher teacher = appDAO.findTeacherById(teacherId);

        teacher.setName("Brenno");

        appDAO.update(teacher);
    }

    private void findTeacherWithCoursesjoinFetch(AppDAO appDAO) {
        int teacherId = 2;

        Teacher teacher = appDAO.findTeacherByIdJoinFetch(teacherId);

        System.out.println(teacher);
        System.out.println(teacher.getCourses());
        System.out.println(teacher.getTeacherDetail());
    }

    private void findTeacherForCourses(AppDAO appDAO) {
        int teacherId = 2;

        Teacher teacher = appDAO.findTeacherById(teacherId);

        List<Course> courses = appDAO.findCoursesByTeacherId(teacherId);

        teacher.setCourses(courses);

        System.out.println(teacher);
        System.out.println(teacher.getCourses());
    }

    private void findTeacherWithCourses(AppDAO appDAO) {
        int teacherId = 2;

        Teacher teacher = appDAO.findTeacherById(teacherId);

        System.out.println(teacher);
        System.out.println(teacher.getCourses());
    }

    private void createTeacherWithCourses(AppDAO appDAO) {
        TeacherDetail teacherDetail = new TeacherDetail("https://www.linkedin.com/in/jefte-goes/", "Read Bible");
        Teacher teacher = new Teacher("Jefté", 33, null);
        //        teacher.setCourses();

        teacher.setTeacherDetail(teacherDetail);

        teacher.add(new Course("Java"));
        teacher.add(new Course("C#"));
        teacher.add(new Course("Python"));

        appDAO.saveTeacher(teacher);
    }

    private void removeTeacherDetailById(AppDAO appDAO) {
        int teacherDetailId = 2;

        appDAO.deleteTeacherDetailById(teacherDetailId);
    }

    private void findTeacherDetailById(AppDAO appDAO) {
        int teacherDetailId = 1;

        TeacherDetail teacherDetail = appDAO.findTeacherDetailById(teacherDetailId);

        System.out.println(teacherDetail);
        System.out.println(teacherDetail.getTeacher());
    }

    private void createTeacher(AppDAO appDAO) {
        TeacherDetail teacherDetail = new TeacherDetail("https://www.linkedin.com/in/jefte-goes/", "Read Bible");
        Teacher teacher = new Teacher("Jefté", 33, null);

        teacher.setTeacherDetail(teacherDetail);

        appDAO.saveTeacher(teacher);
    }

    private void findTeacherById(AppDAO appDAO) {
        int teacherId = 1;

        Teacher teacher = appDAO.findTeacherById(teacherId);

        System.out.println(teacher);
    }

    private void removeTeacherById(AppDAO appDAO) {
        int teacherId = 2;

        appDAO.deleteTeacherById(teacherId);
    }
}