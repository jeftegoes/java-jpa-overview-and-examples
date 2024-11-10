package com.example.real_jpa_entity_relationships.controllers;

import com.example.real_jpa_entity_relationships.models.Student;
import com.example.real_jpa_entity_relationships.repositories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public List<Student> getAll() {
        List<Student> students = studentRepository.getAll();

        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        Student student = studentRepository.findById(id);

        return student;
    }

    @PostMapping("/")
    public void save(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Student student) {
        studentRepository.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentRepository.delete(id);
    }
}
