package com.example.controllers;

import com.example.models.Student;
import com.example.repositories.StudentRepository;
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
        List<Student> students = studentRepository.findAll();

        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        return student;
    }

    @PostMapping("/")
    public void save(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Student student) {
        Student studentInDb = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        studentInDb.setName(student.getName());

        studentRepository.save(studentInDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        studentRepository.deleteById(id);
    }
}
