package com.example.jpa.first_example_jpa;

import com.example.jpa.first_example_jpa.entities.Student;
import com.example.jpa.first_example_jpa.interfaces.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FirstExampleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstExampleJpaApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
//            readStudent(studentDAO);
//            readAllStudents(studentDAO);
//            readStudentsByLastName(studentDAO);
//            updateStudent(4, studentDAO);
            deleteStudent(4, studentDAO);
        };
    }

    private void deleteStudent(int id, StudentDAO studentDAO) {
        studentDAO.delete(id);
    }

    private void updateStudent(int id, StudentDAO studentDAO) {
        studentDAO.update(id, new Student("Sabrina", "Goes", "sabrina@microsoft.com"));
    }

    private void readStudentsByLastName(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findByLastName("Go");
        students.forEach(s -> {
            System.out.println(s);
        });
    }

    private void readAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        students.forEach(s -> {
            System.out.println(s);
        });
    }

    private void readStudent(StudentDAO studentDAO) {
        Student student = studentDAO.findById(1);

        System.out.println("First name: " + student);
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating new students object...");
        Student student1 = new Student("Jefté", "Goes", "jefte@microsoft.com");
        Student student2 = new Student("Brenno", "Goes", "brenno@microsoft.com");
        Student student3 = new Student("Bárbara", "Goes", "bárbara@microsoft.com");

        System.out.println("Saving students...");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);

        System.out.println("Saved students! Generated ID: " + student1.getId());
        System.out.println("Saved students! Generated ID: " + student2.getId());
        System.out.println("Saved students! Generated ID: " + student3.getId());
    }
}
