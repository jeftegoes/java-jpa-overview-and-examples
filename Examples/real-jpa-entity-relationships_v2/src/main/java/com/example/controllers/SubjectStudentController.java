package com.example.controllers;

import com.example.models.SubjectStudent;
import com.example.repositories.SubjectStudentRepository;
import com.example.repositories.Test;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/subject_student")
public class SubjectStudentController {
    private final SubjectStudentRepository subjectStudentRepository;
    private final Test test;

    public SubjectStudentController(SubjectStudentRepository subjectStudentRepository, Test test) {
        this.subjectStudentRepository = subjectStudentRepository;
        this.test = test;
    }

    @GetMapping("/")
    public List<SubjectStudent> getAll() {
        List<SubjectStudent> subjectStudent = test.findAll();

        return subjectStudent;
    }

    @GetMapping("/{id}")
    public SubjectStudent getById(@PathVariable int id) {
        SubjectStudent subjectStudent = subjectStudentRepository.findById(id);

        return subjectStudent;
    }

    @PostMapping("/")
    public void save(@RequestBody SubjectStudent subjectStudent) {
        subjectStudentRepository.save(subjectStudent);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody SubjectStudent subjectStudent) {
        subjectStudentRepository.update(id, subjectStudent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        subjectStudentRepository.delete(id);
    }
}
