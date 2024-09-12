package com.example.jpa.first_example_jpa.interfaces;

import com.example.jpa.first_example_jpa.entities.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(int id, Student student);

    void delete(int id);
}
