package com.example.controllers;

import com.example.mappers.StudentMapper;
import com.example.models.Student;
import com.example.repositories.StudentRepository;
import com.example.responses.StudentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentController(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/")
    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponse> studentResponse = students.stream()
                .map(studentMapper::toStudentResponse)
                .toList();
        
        return studentResponse;
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
